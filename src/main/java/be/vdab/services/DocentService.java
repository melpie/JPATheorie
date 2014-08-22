package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.dao.DocentDAO;
import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.exceptions.EmailAdresAlInGebruikException;
import be.vdab.util.VoornaamInfo;

public class DocentService {

	private final DocentDAO docentDAO = new DocentDAO();

	public Docent read(long docentNr) {
		return docentDAO.read(docentNr);
	}

	public void create(Docent docent) {
		if (docentDAO.findByEmailAdres(docent.getEmailAdres()) != null) {
			throw new EmailAdresAlInGebruikException();
		}
		docentDAO.beginTransaction();
		docentDAO.create(docent);
		docentDAO.commit();
	}

	public void delete(long docentNr) {
		docentDAO.beginTransaction();
		docentDAO.delete(docentNr);
		docentDAO.commit();
	}

	public void opslag(long docentNr, BigDecimal percentage) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		docent.opslag(percentage);
		docentDAO.commit();
	}

	public Iterable<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot,
			int vanafRij, int aantalRijen) {
		return docentDAO.findByWeddeBetween(van, tot, vanafRij, aantalRijen);
	}

	public Iterable<VoornaamInfo> findVoornamen() {
		return docentDAO.findVoornamen();
	}

	public BigDecimal findMaxWedde() {
		return docentDAO.findMaxWedde();
	}

	public int algemeneOpslag(BigDecimal percentage, BigDecimal totEnMetWedde) {
		BigDecimal factor = BigDecimal.ONE.add(percentage
				.divide(new BigDecimal(100)));
		docentDAO.beginTransaction();
		int aantalDocentenAangepast = docentDAO.algemeneOpslag(factor,
				totEnMetWedde);
		docentDAO.commit();
		return aantalDocentenAangepast;
	}

	public void bijnaamToevoegen(long docentNr, String bijnaam) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		docent.addBijnaam(bijnaam);
		docentDAO.commit();
	}

	public void bijnamenVerwijderen(long docentNr, String[] bijnamen) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		for (String bijnaam : bijnamen) {
			docent.removeBijnaam(bijnaam);
		}
		docentDAO.commit();
	}

}