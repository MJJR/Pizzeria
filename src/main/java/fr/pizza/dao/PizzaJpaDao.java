package fr.pizza.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

/**
 * PizzaJpaDao.java
 * @author matth
 */
public class PizzaJpaDao implements IPizzaDao {
	
	/** 
	* entityManagerFactory : EntityManagerFactory 
	*/
	private EntityManagerFactory entityManagerFactory;
	/** 
	* em : EntityManager 
	*/
	private  EntityManager em;
	
	/**
	 * Constructor of PizzaJpaDao.java
	 */
	public PizzaJpaDao(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
		this.em = entityManagerFactory.createEntityManager();
		
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		getEm().persist(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		getEm().persist(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		getEm().persist(new Pizza("REIN", "La reine", 11.50, CategoriePizza.VIANDE));
		getEm().persist(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		getEm().persist(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		getEm().persist(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		getEm().persist(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		getEm().persist(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		
		et.commit();
		
		
		
	}
	
	/** 
	 * Fonction :  
	 */
	public void close(){
		getEm().close();
		getEntityManagerFactory().close();
	}

	@Override
	public List findAllPizzas() {
		// TODO Auto-generated method stub
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		Query query = getEm().createQuery(" SELECT Pizza FROM Pizza ");
		List<Pizza> res = query.getResultList();
		
		et.commit();
		return res;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		getEm().persist(pizza);
		
		et.commit();
	}

	@Override
	public void updatePizza(String code, Pizza pizza) {
		// TODO Auto-generated method stub
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		Pizza vieuxPizza = findPizzaByCode(code);
		
		vieuxPizza.categorie = pizza.categorie;
		vieuxPizza.libelle = pizza.libelle;
		vieuxPizza.id = pizza.id;
		vieuxPizza.prix = pizza.prix;
		vieuxPizza.code = pizza.code;
		
		et.commit();
	}

	@Override
	public void deletePizza(String code) {
		// TODO Auto-generated method stub
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		Pizza vieuxPizza = findPizzaByCode(code);
		getEm().remove(vieuxPizza);
		
		et.commit();
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub
		Pizza res = null;
		if(pizzaExists(code)){
			EntityTransaction et = getEm().getTransaction();
			et.begin();
			
			Query query = getEm().createQuery(" SELECT p FROM Pizza p WHERE p.code = '"+code+"'");
			res = (Pizza)query.getResultList().get(0);
			
			et.commit();
		}
		
		return res;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		boolean res = false;
		EntityTransaction et = getEm().getTransaction();
		et.begin();
		
		Query query = getEm().createQuery(" SELECT p FROM Pizza p WHERE p.code = '"+codePizza+"'");
		res = !query.getResultList().isEmpty();
		
		et.commit();
		return res;
	}

	/**
	 * @return the entityManagerFactory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * @param entityManagerFactory the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
