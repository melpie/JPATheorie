package be.vdab.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.htm")
public class JPAFilter implements Filter {

	private static EntityManagerFactory entityManagerFactory;
	private static ThreadLocal<EntityManager> entityManagers;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("vdab1");
		entityManagers = new ThreadLocal<EntityManager>();
	}

	public static EntityManager getEntityManager() {
		return entityManagers.get();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		entityManagers.set(entityManagerFactory.createEntityManager());
		try {
			chain.doFilter(request, response);
		} finally {
			EntityManager entityManager = entityManagers.get();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			entityManager.close();
			entityManagers.remove();
		}
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}

}
