package be.vdab.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zelfstudiecursussen")
public class ZelfstudieCursus extends Cursus {
	private static final long serialVersionUID = 1L;
	private int duurtijd;

	protected ZelfstudieCursus() {
	}

	public int getDuurtijd() {
		return duurtijd;
	}

	public void setDuurtijd(int duurtijd) {
		this.duurtijd = duurtijd;
	}
	
}