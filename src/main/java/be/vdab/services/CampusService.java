package be.vdab.services;

import be.vdab.dao.CampusDAO;
import be.vdab.entities.Campus;

public class CampusService {
	private final CampusDAO campusDAO = new CampusDAO();

	public Iterable<Campus> findByGemeente(String gemeente) {
		return campusDAO.findByGemeente(gemeente);
	}
}