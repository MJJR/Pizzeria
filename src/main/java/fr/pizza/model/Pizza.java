package fr.pizza.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pizza.utils.ToString;

@Entity
@Table(name="Pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	@ToString(separator = "->", upperCase=false)
	@Column(name = "CODE",length = 4, nullable = false, unique = true)
	public String code;
	
	@ToString(upperCase=true)
	@Column(name = "LIBELLE",length = 50, nullable = false, unique = false)
	public String libelle;
	
	@Column(name = "PRIX", nullable = false, unique = false)
	public double prix;
	
	@Embedded
	public CategoriePizza categorie;
	
	public Pizza(){
		
	}
	
	public Pizza(String c,String l,double p,CategoriePizza cp) {
		
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
		res += this.code + " -> " + this.libelle + "(" + this.prix + " ) " + "catégorie : "+this.categorie;
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}
	
	
	
	

}
