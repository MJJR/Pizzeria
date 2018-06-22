package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizza.dao.PizzaMemDao;
import fr.pizza.exception.StockageException;
import fr.pizza.model.Pizza;

public abstract class MenuService {
	public abstract void executeUC(int i, List<Pizza> tab, Scanner sc, IPizzaDao dao) throws StockageException;
}
