package fr.pizza.dao;

import java.util.List;
import fr.pizza.model.Pizza;

/**
 * @author matth
 *
 */
public interface IPizzaDao { 
	List<Pizza> findAllPizzas();
	void saveNewPizza(Pizza pizza);
	void updatePizza(String code,Pizza pizza);
	void deletePizza(String code);
	Pizza findPizzaByCode(String code);
	boolean pizzaExists(String codePizza);
	void close();
	
}
