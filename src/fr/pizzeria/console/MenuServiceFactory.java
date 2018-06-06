package fr.pizzeria.console;

public class MenuServiceFactory {
	
	MenuService menu;
	
	public void menuService(int choix) {
		
		switch(choix) {
		
			case 1: menu = new ListerPizzaService();
			break;
			case 2: menu = new AjouterPizzaService();
			break;
			case 3: menu = new ModifierPizzaService();
			break;
			case 4: menu = new SupprimerPizzaService();
			break;
		
		}
		
	}
	
	
}
