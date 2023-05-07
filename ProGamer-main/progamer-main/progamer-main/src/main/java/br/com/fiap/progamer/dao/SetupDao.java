package br.com.fiap.progamer.dao;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.fiap.progamer.model.SetupModel;

@Named
@ViewScoped
public class SetupDao {

	@PersistenceContext
	private EntityManager entityManager;

	public SetupDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void save(SetupModel setupModel) {
		this.entityManager.merge(setupModel);
	}
	
	
	public SetupModel buscarPorId(Long id) {
		return entityManager.find(SetupModel.class, id);
	}
	

	public List<SetupModel> findAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<SetupModel> query = (TypedQuery<SetupModel>) entityManager.createQuery(
				"SELECT e FROM SetupModel e");

		return query.getResultList();
	}
	
	public List<SetupModel> findAll2() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SetupModel> cq = cb.createQuery(SetupModel.class);
		Root<SetupModel> rootEntry = cq.from(SetupModel.class);
		CriteriaQuery<SetupModel> all = cq.select(rootEntry);
		TypedQuery<SetupModel> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}
	
	
	@Transactional
	public void deletar(SetupModel setup) {
		entityManager.remove(setup);
	}
	
	
	
}
