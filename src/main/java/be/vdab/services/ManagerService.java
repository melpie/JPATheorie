package be.vdab.services;

import be.vdab.dao.ManagerDAO;
import be.vdab.entities.Manager;

public class ManagerService {
	private final ManagerDAO managerDAO = new ManagerDAO();

	public Manager read(long managerNr) {
		return managerDAO.read(managerNr);
	}

	public Iterable<Manager> findAll() {
		return managerDAO.findAll();
	}
}