package fr.pizza.dao;

import java.awt.List;
import fr.pizza.model.Pizza;

public interface IPizzaDao {
	
	List findAllPizzas();
	void saveNewPizza(Pizza pizza);
	void updatePizza(String code,Pizza pizza);
	void deletePizza(String code);
	Pizza findPizzaByCode(String code);
	boolean pizzaExists(String codePizza);
	
}
