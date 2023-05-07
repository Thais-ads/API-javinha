package br.com.fiap.progamer.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.fiap.progamer.model.LoginModel;


@Named
public class LoginDao {
	@PersistenceContext
	private EntityManager entityManager;

	public LoginDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Transactional
	public void save(LoginModel LoginModel) {
		this.entityManager.merge(LoginModel);
	}

	public List<LoginModel> findAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<LoginModel> query = (TypedQuery<LoginModel>) entityManager.createQuery(
				"SELECT e FROM LoginModel e");

		return query.getResultList();
	}
	
	public List<LoginModel> findAll2() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LoginModel> cq = cb.createQuery(LoginModel.class);
		Root<LoginModel> rootEntry = cq.from(LoginModel.class);
		CriteriaQuery<LoginModel> all = cq.select(rootEntry);
		TypedQuery<LoginModel> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}
	

}
