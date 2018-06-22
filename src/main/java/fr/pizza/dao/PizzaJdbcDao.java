package fr.pizza.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.pizza.model.CategoriePizza;
import fr.pizza.model.Pizza;

public class PizzaJdbcDao implements IPizzaDao {

	private Connection myConnection;

	/**
	 * Fonction :
	 * 
	 */
	public PizzaJdbcDao() {
		
		Statement statement = null;
		ResultSet resultats = null;
		
		setMyConnection(null);
		ResourceBundle resourcesJdbc = ResourceBundle.getBundle("jdbc");

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			myConnection = DriverManager.getConnection(resourcesJdbc.getString("URL"), resourcesJdbc.getString("USER"),
					resourcesJdbc.getString("PASSWORD"));

			statement = getMyConnection().createStatement();
			resultats = statement.executeQuery("SELECT MAX(id) AS max FROM pizzas");

			if (resultats.next()) {
				Integer id = resultats.getInt("max");
				Pizza.nbPizza = id++;
			}

		} catch (ClassNotFoundException classE) {
			System.out.println("Class not found ! \n" + classE);
		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				if (resultats != null) {
					resultats.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}
	}

	/**
	 * Fonction :
	 * 
	 */
	public void close() {

		try {
			if (myConnection != null) {
				myConnection.close();
			}
		} catch (SQLException sqlE) {

			System.out.println("Problem with sql connexion ! \n" + sqlE);

		}

	}

	@Override
	public List findAllPizzas() {
		// TODO Auto-generated method stub
		
		Statement statement = null;
		ResultSet resultats = null;

		List<Pizza> list = new ArrayList<Pizza>();

		try {
			statement = getMyConnection().createStatement();
			resultats = statement.executeQuery("SELECT * FROM pizzas ");

			Integer id;
			String code;
			String libelle;
			Integer prix;
			CategoriePizza categorie;

			while (resultats.next()) {
				id = resultats.getInt("id");
				code = resultats.getString("code");
				libelle = resultats.getString("libelle");
				prix = resultats.getInt("prix");
				categorie = CategoriePizza.valueOf(resultats.getString("categorie"));

				list.add(new Pizza(id, code, libelle, prix, categorie));
			}

		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				if (resultats != null) {
					resultats.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}

		return list;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		Statement statement = null;
		try {
			statement = getMyConnection().createStatement();
			String requete = "INSERT INTO Pizzas (id, code, libelle, prix, categorie) VALUES (";
			requete += "" + Pizza.nbPizza++ + " , '" + pizza.code + "' , '" + pizza.libelle + "' , " + pizza.prix
					+ " , '" + pizza.categorie + "')";

			int resRequete = statement.executeUpdate(requete);
			System.out.println("\nRésultat de la requète d'ajout de pizza = " + resRequete + "\n");

		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}

	}

	@Override
	public void updatePizza(String code, Pizza pizza) {
		// TODO Auto-generated method stub
		PreparedStatement updatePizzaSt = null;
		try {
			Integer identifiant = findPizzaByCode(code).id;
			
			//Statement statement = getMyConnection().createStatement();
			/*
			int resRequete;

			resRequete = statement.executeUpdate("UPDATE PIZZAS SET id = " + pizza.id + " , code ='" + pizza.code
					+ "' , libelle = '" + pizza.libelle + "' , prix = '" + pizza.prix + "' , categorie = '"
					+ pizza.categorie + "' WHERE ID=" + identifiant);
			*/
			
			updatePizzaSt = getMyConnection().prepareStatement("UPDATE pizzas SET id=?,code=?,libelle=?,prix=?,categorie=? WHERE id=?");
					updatePizzaSt.setInt(1,pizza.id);
					updatePizzaSt.setString(2,pizza.code);
					updatePizzaSt.setString(3,pizza.libelle);
					updatePizzaSt.setDouble(4,pizza.prix);
					updatePizzaSt.setString(5,pizza.categorie.name());
					updatePizzaSt.setInt(6,identifiant);
					updatePizzaSt.executeUpdate();
			
			
			//System.out.println("\nRésultat de la maj de la categorie de la pizza = " + resRequete + "\n");

		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				if (updatePizzaSt != null){
					updatePizzaSt.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}

	}

	@Override
	public void deletePizza(String code) {
		// TODO Auto-generated method stub
		Statement statement = null;
		try {
			
			Integer identifiant = findPizzaByCode(code).id;
			
			Statement leStatement = getMyConnection().createStatement();
			
			int resRequete;
			if (pizzaExists(code)) {
				resRequete = leStatement.executeUpdate("DELETE FROM PIZZAS  WHERE id=" + identifiant);
				System.out.println("\nRésultat de la suppression de la pizza = " + resRequete + "\n");

			}
		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}

	}

	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub
		Pizza res = null;
		String requete = "SELECT * FROM pizzas WHERE code = '" + code + "'";

		Integer id;
		String resCode;
		String libelle;
		Integer prix;
		CategoriePizza categorie;
		
		Statement statement = null;
		ResultSet resultats = null;

		try {
			statement = getMyConnection().createStatement();
			resultats =statement.executeQuery(requete);

			if (resultats.next()) {
				id = resultats.getInt("id");
				resCode = resultats.getString("code");
				libelle = resultats.getString("libelle");
				prix = resultats.getInt("prix");
				categorie = CategoriePizza.valueOf(resultats.getString("categorie"));

				res = new Pizza(id, resCode, libelle, prix, categorie);
			}

		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				if (resultats != null) {
					resultats.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}

		return res;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		Statement statement = null;
		ResultSet resultats = null;
		
		boolean res = false;
		String requete = "SELECT * FROM pizzas WHERE code = '" + codePizza + "'";

		try {
			statement = getMyConnection().createStatement();
			resultats = statement.executeQuery(requete);
			res = resultats.next();
		} catch (SQLException sqlE) {
			System.out.println("Problem with sql connexion ! \n" + sqlE);
		} finally {
			try {

				if (resultats != null) {
					resultats.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlE) {

				System.out.println("Problem with sql connexion ! \n" + sqlE);

			}

		}
		return res;
	}

	/**
	 * @return the myConnection
	 */
	public Connection getMyConnection() {
		return myConnection;
	}

	/**
	 * @param myConnection
	 *            the myConnection to set
	 */
	public void setMyConnection(Connection myConnection) {
		this.myConnection = myConnection;
	}

}
