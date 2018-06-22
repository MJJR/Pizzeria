package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.DeletePizzaException;
import fr.pizza.model.Pizza;

public class SupprimerPizzaService extends MenuService {
	
	public void executeUC(int i, List<Pizza> tab, Scanner sc, IPizzaDao dao) throws DeletePizzaException {
		
		String code;
		
		//System.out.println("Suppression d'une pizza \n");
		//listerPizza(tab);
		System.out.println("Veuillez saisir le code de la pizza à supprimer : \n");
		
		code = sc.nextLine();
		
		if(dao.pizzaExists(code)) {

			dao.deletePizza(code);
			
		}
		else{
			throw new DeletePizzaException("Nous n'avons pas trouvés votre pizza.");
		}
		
	}

}
