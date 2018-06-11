package fr.pizza.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

public class PizzaMemDao implements IPizzaDao {
	
	//private Pizza tableau[];
	private List listeCode = new List();
	private ArrayList<Pizza> tableau = new ArrayList<Pizza>();
	

	public PizzaMemDao(){
		
		
		tableau.add( new Pizza("PEP","Pépéroni",12.50,CategoriePizza.VIANDE) );
		tableau.add( new Pizza("MAR","Margherita",14.00,CategoriePizza.SANS_VIANDE) );
		tableau.add( new Pizza("REIN","La reine",11.50,CategoriePizza.VIANDE) );
		tableau.add( new Pizza("FRO","La 4 fromages",12.00,CategoriePizza.SANS_VIANDE) );
		tableau.add( new Pizza("CAN","La cannibale",12.50,CategoriePizza.VIANDE) );
		tableau.add( new Pizza("SAV","La savoyarde",13.00,CategoriePizza.VIANDE) );
		tableau.add( new Pizza("ORI","L'orientale",13.50,CategoriePizza.VIANDE) );
		tableau.add( new Pizza("IND","L'indienne",14.00,CategoriePizza.VIANDE) );
		
		//Pizza aux[] = {pep,mar,rein,fro,can,sav,ori,ind};
		//this.tableau = aux;
		
		
	}
	
	public List findAllPizzas() {
		// TODO Auto-generated method stub
		return this.listeCode;
	}

	public ArrayList<Pizza> getTableau(){
		return this.tableau;
	}

	
	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		/*
		Pizza aux[] = new Pizza[findAllPizza().length+1];
		
		for(int i=0;i<findAllPizza().length;i++) {
			aux[i] = findAllPizza()[i];
		}
		
		aux[findAllPizza().length] = pizza;
		
		setTableau(aux);
		*/
		getTableau().add(pizza);
	}

	@Override
	public void updatePizza(String code, Pizza pizza) {
		// TODO Auto-generated method stub
			
		this.findPizzaByCode(code).id = pizza.id;
		this.findPizzaByCode(code).libelle = pizza.libelle;
		this.findPizzaByCode(code).prix = pizza.prix;
		this.findPizzaByCode(code).categorie = pizza.categorie;
		
		//On modifie le code à la toute fin sinon bug !!!
		this.findPizzaByCode(code).code = pizza.code;

	}

	@Override
	public void deletePizza(String code) {
		// TODO Auto-generated method stub
		
		/*
		Pizza aux[] = new Pizza[findAllPizza().length-1];
		boolean trouve = false;
		int i=0;
		
		while(!trouve && i<findAllPizza().length) {
			if(code.equals(findAllPizza()[i].code)) {
				trouve = true;
			}
			else i++;
		}
		
		if(trouve) {

			int j=0;
			while(j<i) {
				aux[j]=findAllPizza()[j];
				j++;
			}
			while(j+1<findAllPizza().length) {
				aux[j]=findAllPizza()[j+1];
				j++;
			}
			
		}
		else System.out.println("Nous n'avons pas trouvés votre pizza.");
		

		setTableau(aux);
		*/
		getTableau().remove(findPizzaByCode(code));
		
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub
		
		//System.out.println("DEBUG : code = "+code);
		
		/*
		boolean trouve = false;
		int i=0;
		
		
		while(!trouve && i<findAllPizza().length) {
			//System.out.println("DEBUG : i = "+i);
			if(code.equals(findAllPizza()[i].code)) {
				trouve = true;
			}
			else i++;
		}
		
		//System.out.println("DEBUG : i = "+i);
		return findAllPizza()[i];
		
		*/
		boolean trouve = false;
		Pizza aux = null;
		
		Iterator<Pizza> iterator = getTableau().iterator();
		while(iterator.hasNext() && !trouve) {
			aux = (Pizza) iterator.next();
			if(aux.code.equals(code)) trouve = true;
		}
		return aux;
		
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		/*
		boolean trouve = false;
		int i=0;
		
		
		while(!trouve && i<findAllPizza().length) {
			if(codePizza.equals(findAllPizza()[i].code)) {
				trouve = true;
			}
			else i++;
		}
		
		return trouve;
		*/
		boolean trouve = false;
		Pizza aux;
		
		Iterator<Pizza> iterator = getTableau().iterator();
		while(iterator.hasNext() && !trouve) {
			aux = (Pizza) iterator.next();
			if(aux.code.equals(codePizza)) trouve = true;
		}
		return trouve;
		
	}

	
	


}
