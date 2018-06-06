package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.StockageException;
import fr.pizza.model.Pizza;

public class PizzeriaAdminConsoleApp {
	
	static boolean enCours = true;
	static PizzaMemDao dao;
	//static ListerPizzaService listerPizza = new ListerPizzaService();
	//static AjouterPizzaService ajouterPizza = new AjouterPizzaService();
	//static ModifierPizzaService modifierPizza = new ModifierPizzaService();
	//static SupprimerPizzaService supprimerPizza = new SupprimerPizzaService();
	static MenuServiceFactory choix = new MenuServiceFactory();
	
	static void afficherMenu() {
		System.out.println("*****Pizzeria Administration*****\n"
				+ "1. Lister les pizzas \n"
				+ "2. Ajouter une nouvelle pizza \n"
				+ "3. Mettre à jour une pizza \n"
				+ "4. Supprimer une pizza \n"
				+ "99. Sortir \n");
	}
	
	/*
	static void listerPizza(Pizza tab[]) {
		System.out.println("Liste des pizzas : \n");
		for(int i=0;i<tab.length;i++) {
			System.out.println(tab[i].code + " -> " + tab[i].libelle + "(" + tab[i].prix + " €)");
		}
		System.out.println("\n");
		
	}
	
	static void ajouterPizza(Pizza tab[],Scanner sc) {
		
		String code,lib;
		double prix;
		
		System.out.println("Ajout d'une nouvelle pizza \n");
		System.out.println("Veuillez saisir le code : \n");
		code = sc.next();
		System.out.println("Veuillez saisir le nom : \n");
		
		//On vide la ligne pour éviter des problèmes avec nextLine
		sc.nextLine();
		
		lib = sc.nextLine();
		
		System.out.println("Veuillez saisir le prix : \n");
		prix = sc.nextDouble();
		
		dao.saveNewPizza(new Pizza(code,lib,prix));
		

	}
	
	static void majPizza(Pizza tab[],Scanner sc) {
		
		String code,newCode,lib;
		double prix;
		
		System.out.println("Mise à jour d'une pizza \n");
		listerPizza(tab);
		System.out.println("Veuillez saisir le code de la pizza à modifier : \n");
		
		
		
		code = sc.nextLine();
		
		
		if(dao.pizzaExists(code)) {
			System.out.println("Veuillez saisir le nouveau code :");
			newCode = sc.next();
			System.out.println("Veuillez saisir le nouveau nom :");
			
			//On vide la ligne pour éviter des problèmes avec nextLine
			sc.nextLine();
			
			lib = sc.nextLine();
			System.out.println("Veuillez saisir le nouveau prix :");
			prix = sc.nextDouble();
			
			dao.updatePizza(code, new Pizza(newCode,lib,prix));
						
		}
		else System.out.println("Nous n'avons pas trouvés votre pizza.");
		
	}
	
	static void supprimerPizza(Pizza tab[],Scanner sc) {
		
		String code;
		
		System.out.println("Suppression d'une pizza \n");
		listerPizza(tab);
		System.out.println("Veuillez saisir le code de la pizza à supprimer : \n");
		
		code = sc.nextLine();
		
		if(dao.pizzaExists(code)) {

			dao.deletePizza(code);
			
		}
		else System.out.println("Nous n'avons pas trouvés votre pizza.");
		
	}
	*/
	
	static void sortir() {
		System.out.println("Aurevoir :( \n");
		enCours = false;
	}
	
	static void choisir(int i, ArrayList<Pizza> tab, Scanner sc) throws StockageException {

		switch(i) {
		/*
			case 1: listerPizza.executeUC(i,tab,sc,dao);
					afficherMenu();
			break;
			case 2: ajouterPizza.executeUC(i,tab,sc,dao);
					afficherMenu();
			break;
			case 3: System.out.println("Mise à jour d'une pizza \n");
					listerPizza.executeUC(i,tab,sc,dao);
					modifierPizza.executeUC(i, tab, sc, dao);
					afficherMenu();
			break;
			case 4: System.out.println("Suppression d'une pizza \n");
					listerPizza.executeUC(i,tab,sc,dao);
					supprimerPizza.executeUC(i, tab, sc, dao);
					afficherMenu();
			break;
		*/
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
		
		dao = new PizzaMemDao();
		
		
		Scanner saisie = new Scanner(System.in);
		int choix;
		afficherMenu();
		
		while(enCours) {
			choix = saisie.nextInt();
			
			//Pour débugger un problème de nextInt et nextLine
			saisie.nextLine();
			
			choisir(choix,dao.getTableau(),saisie);
			
		}
		
		saisie.close();
	}

}
