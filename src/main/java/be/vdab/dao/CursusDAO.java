package be.vdab.dao;

import javax.persistence.TypedQuery;
import be.vdab.entities.Cursus;

public class CursusDAO extends AbstractDAO {
	public Iterable<Cursus> findByNaamContains(String woord) {
		TypedQuery<Cursus> query = getEntityManager().createNamedQuery(
				"Cursus.findByNaamContains", Cursus.class);
		query.setParameter("zoals", '%' + woord + '%');
		return query.getResultList();
	}
}