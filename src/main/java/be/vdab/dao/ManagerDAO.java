package be.vdab.dao;

import javax.persistence.TypedQuery;

import be.vdab.entities.Manager;

public class ManagerDAO extends AbstractDAO {
	public Manager read(long managerNr) {
		return getEntityManager().find(Manager.class, managerNr);
	}

	public Iterable<Manager> findAll() {
		TypedQuery<Manager> query = getEntityManager().createNamedQuery(
				"Manager.findAll", Manager.class);
		return query.getResultList();
	}
}