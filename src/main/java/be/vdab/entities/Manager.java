package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long managerNr;
	private String voornaam;
	private String familienaam;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "manager")
	private Campus campus;

	public void setCampus(Campus campus) {
		if (campus != this.campus) {
			Campus vorigeCampus = this.campus;
			this.campus = campus;
			if (vorigeCampus != null && vorigeCampus.getManager() == this) {
				vorigeCampus.setManager(null);
			}
			if (campus != null && campus.getManager() != this) {
				campus.setManager(this);
			}
		}
	}

	public Manager(String voornaam, String familienaam) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
	}

	protected Manager() { // default constructor voor JPA
	}
	
	public long getManagerNr() {
		return managerNr;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	@Override
	public String toString() {
		return managerNr + ":" + voornaam + " " + familienaam;
	}
}