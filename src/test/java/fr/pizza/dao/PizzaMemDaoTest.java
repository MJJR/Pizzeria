package fr.pizza.dao;

import org.junit.Assert;
import org.junit.Test;

import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

public class PizzaMemDaoTest {

	@Test
	public void testSaveNewPizza() {

		PizzaMemDao test = new PizzaMemDao();

		Pizza pizza = new Pizza("LOL", "C'est trop drôle", 42.00, CategoriePizza.SANS_VIANDE);
		
		int lenght = test.getTableau().size();
		
		test.saveNewPizza(pizza);
		
		Assert.assertEquals("Le Premier test (1/3) a échoué", lenght+1, test.getTableau().size());

		Assert.assertEquals("Le Premier test (2/3) a échoué", pizza, test.getTableau().get(lenght));
		
		Assert.assertEquals("Le Premier test (3/3) a échoué",true,test.getTableau().contains(pizza));
	}

	@Test
	public void testUpdatePizza() {

		PizzaMemDao test = new PizzaMemDao();

		Pizza pizza = new Pizza("LOL", "C'est trop drôle", 42.00, CategoriePizza.SANS_VIANDE);

		test.updatePizza("FRO", pizza);
		
		//La pizza "FRO" est l'identifiant 3 dans PizzaMemDao
		Assert.assertEquals("Le Deuxième test a échoué", pizza, test.getTableau().get(3));

	}

	@Test
	public void testDeletePizza() {

		PizzaMemDao test = new PizzaMemDao();
		
		Pizza pizza = new Pizza("CAN","La cannibale",12.50,CategoriePizza.VIANDE);

		test.deletePizza("CAN");
		
		Assert.assertNotEquals("Le Troisième test a échoué",true, test.getTableau().contains(pizza));

	}

	@Test
	public void testFindPizzaByCode() {
		PizzaMemDao test = new PizzaMemDao();

		Pizza pizza = new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE);

		Assert.assertEquals("Le Quatrième test a échoué", pizza, test.findPizzaByCode("ORI"));

		Assert.assertEquals("Le Cinquième test a échoué", null, test.findPizzaByCode("LOL"));

	}

	@Test
	public void testPizzaExists() {
		PizzaMemDao test = new PizzaMemDao();

		Assert.assertEquals("Le Quatrième test a échoué", true, test.pizzaExists("ORI"));

		Assert.assertEquals("Le Cinquième test a échoué", false, test.pizzaExists("LOL"));
	}

}
