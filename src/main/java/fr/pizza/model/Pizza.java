package fr.pizza.model;

import java.lang.reflect.Field;

import fr.pizza.utils.ToString;

public class Pizza {
	
	static int nbPizza = 0;
	public int id;
	
	@ToString(separator = "->", upperCase=false)
	public String code;
	
	@ToString(upperCase=true)
	public String libelle;
	public double prix;
	public CategoriePizza categorie;
	
	public Pizza(String c,String l,double p,CategoriePizza cp) {
		
		this.id = nbPizza++;
		this.code = c;
		this.libelle = l;
		this.prix = p;
		this.categorie = cp;
		
	}
	
	public Pizza(int i,String c,String l,double p,CategoriePizza categorie) {
		
		this.id = i;
		this.code = c;
		this.libelle = l;
		this.prix = p;
		this.categorie = categorie;
		
	}
	
	public String toString() {
		
		
		for(Field f : this.getClass().getFields()){
			ToString annot = f.getAnnotation(ToString.class);
			if (annot != null){
				
			}
		}
		
		
		String res ="";
		res += this.code + " -> " + this.libelle + "(" + this.prix + " €) " + "catégorie : "+this.categorie;
		return res;
		
	}

}
