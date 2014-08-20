package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cursussen")
public abstract class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long cursusNr;
	private String naam;

	protected Cursus() { // default constructor voor JPA
	}
	
	public long getCursusNr() {
		return cursusNr;
	}

	public void setCursusNr(long cursusNr) {
		this.cursusNr = cursusNr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public String toString() {
		return cursusNr + ":" + naam;
	}
}
