package be.vdab.util;

public class VoornaamInfo {
	private final String voornaam;
	private final long aantal;

	public VoornaamInfo(String voornaam, long aantal) {
		this.voornaam = voornaam;
		this.aantal = aantal;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public long getAantal() {
		return aantal;
	}

	@Override
	public String toString() {
		return voornaam + ":" + aantal;
	}
}