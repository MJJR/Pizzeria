package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.UpdatePizzaException;
import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

public class ModifierPizzaService extends MenuService{
	
	public void executeUC(int i, ArrayList<Pizza> tab, Scanner sc, PizzaMemDao dao) throws UpdatePizzaException {
		
		String code,newCode,lib,categorie;
		double prix;
		
		//System.out.println("Mise à jour d'une pizza \n");
		//listerPizza(tab);
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
			
			System.out.println("Veuillez saisir la catégorie de la Pizza :");
			
			//On vide la ligne pour éviter des problèmes avec nextLine
			sc.nextLine();
			
			categorie = sc.nextLine();
			
			//System.out.println("DEBUG : CategoriePizza.searchCategoryByName(categorie) = "+CategoriePizza.searchCategoryByName(categorie));
			dao.updatePizza(code, new Pizza(newCode,lib,prix,CategoriePizza.searchCategoryByName(categorie)));
						
		}
		else throw new UpdatePizzaException("Nous n'avons pas trouvés votre pizza.");
		
	}

}
