package fr.pizzeria.console;

import java.util.List;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizza.dao.PizzaJdbcDao;
import fr.pizza.dao.PizzaJpaDao;
import fr.pizza.exception.StockageException;
import fr.pizza.model.Pizza;

public class PizzeriaAdminConsoleApp {
	

	static boolean enCours = true;
	//static PizzaMemDao dao;
	static IPizzaDao dao;
	static MenuServiceFactory choix = new MenuServiceFactory();
	
	/**
	 * 
	 */
	static void afficherMenu() {
		System.out.println("*****Pizzeria Administration*****\n"
				+ "1. Lister les pizzas \n"
				+ "2. Ajouter une nouvelle pizza \n"
				+ "3. Mettre à jour une pizza \n"
				+ "4. Supprimer une pizza \n"
				+ "99. Sortir \n");
	}
	
	
	
	static void sortir() {
		System.out.println("Aurevoir :( \n");
		enCours = false;
	}
	
	static void choisir(int i, List<Pizza> tab, Scanner sc) throws StockageException {

		switch(i) {
			case 1:
			case 2:
				choix.menuService(i);
				choix.menu.executeUC(i, tab, sc, dao);
				afficherMenu();
				break;
			case 3:
			case 4:
				choix.menuService(1);
				choix.menu.executeUC(1, tab, sc, dao);
				choix.menuService(i);
				choix.menu.executeUC(i, tab, sc, dao);
				afficherMenu();
				break;
			case 99: sortir();
				break;
		}
	}

	public static void main(String[] args) throws StockageException {
		
		//Mon super projet Pizza !!!
		
		// TODO Auto-generated method stub
		
		
		//dao = new PizzaMemDao();
		//dao = new PizzaJdbcDao();
		dao = new PizzaJpaDao();
		
		Scanner saisie = new Scanner(System.in);
		int choix;
		afficherMenu();
		
		while(enCours) {
			choix = saisie.nextInt();
			
			//Pour débugger un problème de nextInt et nextLine
			saisie.nextLine();
			
			choisir(choix,dao.findAllPizzas(),saisie);
			
		}
		
		dao.close();
		saisie.close();
	}

}
