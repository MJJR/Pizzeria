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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		/*
		 * On peut remplacer les premiers if par :
		 * if (!(obj instanceof Pizza)){
		 * 		return false;
		 * }
		*/
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (categorie != other.categorie)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}
	

}
