package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.SavePizzaException;
import fr.pizza.exception.StockageException;
import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

public class AjouterPizzaService extends MenuService {
	
	public void executeUC(int i, List<Pizza> tab, Scanner sc, IPizzaDao dao) throws SavePizzaException{
		
		String code,lib,categorie;
		double prix;
		
		
		System.out.println("Ajout d'une nouvelle pizza \n");
		System.out.println("Veuillez saisir le code : \n");
		code = sc.next();
		if(code.length()>4) throw new SavePizzaException("Nom de code trop long !!!");
		else {
			System.out.println("Veuillez saisir le nom : \n");
		
			//On vide la ligne pour �viter des probl�mes avec nextLine
			sc.nextLine();
		
			lib = sc.nextLine();
		
			System.out.println("Veuillez saisir le prix : \n");
			prix = sc.nextDouble();
			
			System.out.println("Veuillez saisir la cat�gorie de la Pizza :");
			
			//On vide la ligne pour �viter des probl�mes avec nextLine
			sc.nextLine();
			
			categorie = sc.nextLine();
			
			//System.out.println("DEBUG : CategoriePizza.searchCategoryByName(categorie) = "+CategoriePizza.searchCategoryByName(categorie));
			dao.saveNewPizza(new Pizza(code,lib,prix,CategoriePizza.searchCategoryByName(categorie)));
		}
		
	}
}
