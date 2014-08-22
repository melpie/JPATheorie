package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table(name = "campussen")
public class Campus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long campusNr;
	private String naam;
	@Embedded
	private Adres adres;
	@ElementCollection
	@CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusNr"))
	@OrderBy("fax")
	private Set<TelefoonNr> telefoonNrs;
	@OneToMany(mappedBy = "campus")
	@OrderBy("voornaam, familienaam")
	private Set<Docent> docenten;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ManagerNr")
	private Manager manager;

	public void setManager(Manager manager) {
		if (manager != this.manager) {
			Manager vorigeManager = this.manager;
			this.manager = manager;
			if (vorigeManager != null && vorigeManager.getCampus() == this) {
				vorigeManager.setCampus(null);
			}
			if (manager != null && manager.getCampus() != this) {
				this.manager.setCampus(this);
			}
		}
	}

	public Manager getManager() {
		return manager;
	}

	public Campus(String naam, Adres adres) {
		setNaam(naam);
		setAdres(adres);
		telefoonNrs = new LinkedHashSet<>();
		docenten = new LinkedHashSet<Docent>();
	}

	protected Campus() {
	}

	public long getCampusNr() {
		return campusNr;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Set<TelefoonNr> getTelefoonNrs() {
		return Collections.unmodifiableSet(telefoonNrs);
	}

	public void addTelefoonNr(TelefoonNr telefoonNr) {
		telefoonNrs.add(telefoonNr);
	}

	public void removeTelefoonNr(TelefoonNr telefoonNr) {
		telefoonNrs.remove(telefoonNr);
	}

	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}

	public void addDocent(Docent docent) {
		docenten.add(docent);
		if (docent.getCampus() != this) {
			docent.setCampus(this);
		}
	}

	public void removeDocent(Docent docent) {
		docenten.remove(docent);
		if (docent.getCampus() == this) {
			docent.setCampus(null);
		}
	}

	@Override
	public String toString() {
		return campusNr + ":" + naam;
	}
}