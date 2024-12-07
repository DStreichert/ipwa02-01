package iu.ipwh02.ghostnetfishing;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public abstract class AbstractDAO<T extends IDatabaseEntity> implements AutoCloseable {

	Class<T> typeParameterClass;
	EntityManager entityManager;

	public AbstractDAO(Class<T> typeParameterClass) {
		try {
			this.typeParameterClass = typeParameterClass;
			entityManager = App.entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EntityTransaction getAndBeginTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}

	@Override
	public void close() {
		try {
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<T> findAll() {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<T> cq = criteriaBuilder.createQuery(typeParameterClass);
		return entityManager
				.createQuery("select a from " + typeParameterClass.toString().substring(6) + " a", typeParameterClass)
				.getResultList();
	}

	public void persist(T entity) {
		EntityTransaction transaction = getAndBeginTransaction();
		if (entity.getId() > 0) {
			entityManager.merge(entity);
		} else {
			entityManager.persist(entity);
		}
		transaction.commit();
	}

	public T getAtIndex(int pos) {
		return entityManager
				.createQuery("select a from " + typeParameterClass.toString().substring(6) + " a", typeParameterClass)
				.setMaxResults(1).setFirstResult(pos).getSingleResult();
	}

	public void persistAll(List<T> entityList) {
		for (T entity : entityList) {
			this.persist(entity);
		}
	}
}
