package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import be.vdab.enums.Geslacht;


@Entity
@Table(name = "docenten")
//@NamedQuery(name = "Docent.findByWeddeBetween",query = "select d from Docent d where d.wedde between :van and :tot order by d.wedde,d.docentNr")
public class Docent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long docentNr;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private Geslacht geslacht;
	@ElementCollection
	@CollectionTable(name = "docentenbijnamen",	joinColumns = @JoinColumn(name = "docentNr"))
	@Column(name = "Bijnaam")
	private Set<String> bijnamen;
	
	protected Docent() {
	}
	
	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		bijnamen = new HashSet<>();
	}
	
	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(
		percentage.divide(new BigDecimal(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}
	
	public long getDocentNr() {
		return docentNr;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public void setWedde(BigDecimal wedde) {
		this.wedde = wedde;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}
	
	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen);
	}
	
	public void addBijnaam(String bijnaam) {
		bijnamen.add(bijnaam);
	}
	
	public void removeBijnaam(String bijnaam) {
		bijnamen.remove(bijnaam);
	}
	
	@Override
	public String toString() {
		return docentNr + ":" + voornaam + " " + familienaam;
	}
	
}