package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.pizza.dao.PizzaMemDao;
import fr.pizza.model.Pizza;

public class ListerPizzaService extends MenuService {

	public void executeUC(int i,ArrayList<Pizza> tab, Scanner sc, PizzaMemDao dao){
		System.out.println("Liste des pizzas : \n");
		/*
		for(int j=0;j<tab.length;j++) {
			System.out.println(tab[j].code + " -> " + tab[j].libelle + "(" + tab[j].prix + " €)");
		}
		*/
		for (Object object: tab) {
			Pizza pizza = (Pizza)object;
			System.out.println(pizza.toString());
		}
		System.out.println("\n");
	}
	
}
