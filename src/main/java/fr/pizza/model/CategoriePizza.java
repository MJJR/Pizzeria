package fr.pizza.model;

import javax.persistence.Column;

public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");
	
	@Column(name = "CATEGORIE", length = 50 , nullable = false, unique = false)
	private String nom;
	
	private CategoriePizza(String nom) { 
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public static CategoriePizza searchCategoryByName(String categorie) {
		//System.out.println("DEBUG : categorie = "+categorie);
		CategoriePizza tabCat[] = CategoriePizza.values();
		boolean trouve = false;
		int j=0;
		while(!trouve && j<tabCat.length) {
			if(categorie.equals(tabCat[j].getNom())) trouve = true;
			else j++;
		}
		//System.out.println("DEBUG : tabCat[j] = "+tabCat[j]);
		return tabCat[j];
	}

}
