package fr.pizzeria.console;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
//IMPORT STATIQUE POUR emptyStandardInputStream()
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.SavePizzaException;
import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;



public class AjouterPizzaServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testExecuteUC() throws SavePizzaException {
		
		PizzaMemDao dao = new PizzaMemDao();
		Scanner scn = new Scanner(System.in);
		Pizza pizza = new Pizza("LOL", "C'est trop drôle", 42.0, CategoriePizza.SANS_VIANDE);

		// J'alimente le mock avec les valeurs ""pour un debug "LOL","C'est trop drôle","42", ""pour encore un debug ,"sans viande"
		systemInMock.provideLines("LOL","C'est trop drôle","42,0","Sans Viande");
		AjouterPizzaService serv = new AjouterPizzaService();
		serv.executeUC(0, dao.getTableau(), scn, dao);
		assertEquals("Premier Test a échoué", pizza , dao.getTableau().get(  dao.getTableau().size()-1  ));
		
		scn.close();
	}

}
