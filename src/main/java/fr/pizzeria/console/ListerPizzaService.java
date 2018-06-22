package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.StockageException;
import fr.pizza.model.Pizza;

public class ListerPizzaService extends MenuService {

	public void executeUC(int i,List<Pizza> tab, Scanner sc, IPizzaDao dao){
		System.out.println("Liste des pizzas : \n");
		for (Object object: tab) {
			Pizza pizza = (Pizza)object;
			System.out.println(pizza.toString());
		}
		System.out.println("\n");
	}
	
}
