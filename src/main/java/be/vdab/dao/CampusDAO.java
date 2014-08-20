package be.vdab.dao;

import javax.persistence.TypedQuery;
import be.vdab.entities.Campus;

public class CampusDAO extends AbstractDAO {
	public Iterable<Campus> findByGemeente(String gemeente) {
		TypedQuery<Campus> query = getEntityManager().createNamedQuery(
				"Campus.findByGemeente", Campus.class);
		query.setParameter("gemeente", gemeente);
		return query.getResultList();
	}
}