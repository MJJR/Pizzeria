package fr.pizza.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

/**
 * @author matth
 *
 */
public class PizzaMemDao implements IPizzaDao {

	/**
	 * tableau : ArrayList<Pizza>
	 */
	private ArrayList<Pizza> tableau = new ArrayList<Pizza>();

	
	/**
	 * Constructor of PizzaMemDao.java
	 */
	public PizzaMemDao() {

		tableau.add(new Pizza("PEP", "P�p�roni", 12.50, CategoriePizza.VIANDE));
		tableau.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		tableau.add(new Pizza("REIN", "La reine", 11.50, CategoriePizza.VIANDE));
		tableau.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		tableau.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		tableau.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		tableau.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		tableau.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

	}

	/**
	 * Fonction : Fermer le dao
	 */
	public void close() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#findAllPizzas()
	 */
	public List findAllPizzas() {
		// TODO Auto-generated method stub
		return getTableau();
	}

	/**
	 * Fonction :
	 * @return
	 */
	public ArrayList<Pizza> getTableau() {
		return this.tableau;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#saveNewPizza(fr.pizza.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		getTableau().add(pizza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#updatePizza(java.lang.String,
	 * fr.pizza.model.Pizza)
	 */
	@Override
	public void updatePizza(String code, Pizza pizza) {
		// TODO Auto-generated method stub

		this.findPizzaByCode(code).id = pizza.id;
		this.findPizzaByCode(code).libelle = pizza.libelle;
		this.findPizzaByCode(code).prix = pizza.prix;
		this.findPizzaByCode(code).categorie = pizza.categorie;

		// On modifie le code � la toute fin sinon bug !!!
		this.findPizzaByCode(code).code = pizza.code;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String code) {
		// TODO Auto-generated method stub

		getTableau().remove(findPizzaByCode(code));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#findPizzaByCode(java.lang.String)
	 */
	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub

		boolean trouve = false;
		Pizza aux = null;

		Iterator<Pizza> iterator = getTableau().iterator();
		while (iterator.hasNext() && !trouve) {
			aux = (Pizza) iterator.next();
			if (aux.code.equals(code))
				trouve = true;
		}
		return aux;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizza.dao.IPizzaDao#pizzaExists(java.lang.String)
	 */
	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub

		boolean trouve = false;
		Pizza aux;

		Iterator<Pizza> iterator = getTableau().iterator();
		while (iterator.hasNext() && !trouve) {
			aux = (Pizza) iterator.next();
			if (aux.code.equals(codePizza))
				trouve = true;
		}
		return trouve;

	}

}
