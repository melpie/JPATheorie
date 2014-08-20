package be.vdab.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "klassikalecursussen")
public class KlassikaleCursus extends Cursus {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date van;
	@Temporal(TemporalType.DATE)
	private Date tot;

	protected KlassikaleCursus() {
	}

	public Date getVan() {
		return van;
	}

	public void setVan(Date van) {
		this.van = van;
	}

	public Date getTot() {
		return tot;
	}

	public void setTot(Date tot) {
		this.tot = tot;
	}
	
}