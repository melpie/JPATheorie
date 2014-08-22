package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailAdres implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String REG_EXPR = "^.+@.+\\.[a-z]+$";
	@Column(name = "EmailAdres")
	private final String adres;

	public EmailAdres(String adres) {
		if (!adres.matches(REG_EXPR)) {
			throw new IllegalArgumentException("Verkeerd e-mailadres");
		}
		this.adres = adres;
	}

	protected EmailAdres() { // default constructor voor JPA
		this.adres = null;
	}

	public String getAdres() {
		return adres;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EmailAdres)) {
			return false;
		}
		return ((EmailAdres) obj).adres.equalsIgnoreCase(this.adres);
	}

	@Override
	public int hashCode() {
		return adres.toUpperCase().hashCode();
	}

	@Override
	public String toString() {
		return adres;
	}
}