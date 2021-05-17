package com.excilys.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.Service;
import com.excilys.mapper.*;

public class Dao {
	// Properties
	private String url;
	private String login;
	private String passwd;
	private Connection con = null;
	private Mapper mappy;
	private static Dao instance;
	
	// Query
	private static final String AJOUT_ONE_COMPUTER = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	
	private static final String UPDATE_COMPUTER_NAME = "UPDATE computer SET name=? WHERE id=?";
	private static final String UPDATE_COMPUTER_INTRODUCED = "UPDATE computer SET introduced=? WHERE id=?";
	private static final String UPDATE_COMPUTER_DISCONTINUED = "UPDATE computer SET discontinued=? WHERE id=?";
	private static final String UPDATE_COMPUTER_COMPANY_ID = "UPDATE computer SET company_id=? WHERE id=?";
	
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";

	// Constructor
	private Dao(String url, String login, String passwd) {
		this.url = url;
		this.login = login;
		this.passwd = passwd;
		this.mappy=new Mapper();
		
	}
	
	public static Dao getInstance(String url,String login,String passwd) {
        if (instance == null) {
            instance = new Dao(url,login,passwd);
        }
        return instance;
    }

	public void connection() {
		
		/* On essaye de se connecter à notre base de donnée grâce aux informations passées au construteur*/
		try {
		    this.con = DriverManager.getConnection( this.url, this.login, this.passwd );
		  
		} catch ( SQLException e ) {
			System.out.println(e);
		}
		     
	}
	
	public int getNombreTotalOrdinateur() {
		String query = "SELECT COUNT(*) as numero FROM computer;";
		ResultSet results = null;
		int count=0;
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			results.next();
			count = results.getInt("numero");
		}catch(Exception e) {
			System.out.println(e+"exception due a la requete");
		}

		return count;
	}
	
	
		
	public List <Computer> listeSpecifiquesComputers(int debut,int nombre) {
		String query = "SELECT * FROM computer WHERE id LIMIT "+debut+","+nombre+";";
		ResultSet results=null;
		List<Computer> computers = new ArrayList<>();
		
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			computers = this.mappy.dataToListComputer(results);
		}catch(Exception e) {
			System.out.println(e+"exception due a la requete");
		}
			
		return computers;
	}
	
	//Renvoie la liste des PC
	
	public List <Computer> listeComputer() {
		String query = "SELECT * FROM computer;";
		ResultSet results=null;
		List<Computer> computers = new ArrayList<>();
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			computers = this.mappy.dataToListComputer(results);
		}catch(Exception e) {
			System.out.println(e+"exception due a la requete");
		}
			
		return computers;
	}
	
	//Renvoie la liste des compagnies
	
	public List<Company> listeCompanies() {
		String query = "SELECT * FROM company;";
		ResultSet results=null;
		List<Company> companies = new ArrayList<>();
		
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			companies = this.mappy.dataToListCompany(results);
		}catch(Exception e) {
			System.out.println(e+"exception due a la requete");
		}
			
		return companies;
	}
	
	//renvoie un seul pc en fonction de son ID
	
	public Computer oneComputer(int id) {
		String query = "SELECT * FROM computer WHERE id="+id+";";
		ResultSet results=null;
		Computer computer = null;

		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			computer = this.mappy.dataToComputer(results);
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}
		return computer;
				
	}
	
	//Ajoute un pc en fonction des données rentrées par l'utilisateur
	public void ajouterUnComputer(Computer c) {
		
		String name = c.getName();
		LocalDate date_entree_pc = c.getEntryDate();
		LocalDate date_sortie_pc=c.getOutDate();
		int company_id = c.getIdCompany();
		try {
			PreparedStatement ps = this.con.prepareStatement(AJOUT_ONE_COMPUTER);
			
			ps.setString(1,name);
			ps.setDate(2, Date.valueOf(date_entree_pc));
			ps.setDate(3, Date.valueOf(date_sortie_pc));			
			ps.setInt(4, company_id);
			ps.executeUpdate();
			
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}
	}
	
	
	// Mets à jour le champ spécifié par l'utilisateur
	
	public void updateComputer(int id, int colonne, Object value) {
		
		
		switch(colonne) {
		
		// Si l'utilisateur veut changer le nom
		case 2:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_NAME);
				ps.setString(1, (String)value);
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 3:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_INTRODUCED);
				ps.setDate(1, Date.valueOf((LocalDate)value));
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 4:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_DISCONTINUED);
				ps.setDate(1, Date.valueOf((LocalDate)value));
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 5:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_COMPANY_ID);
				ps.setInt(1, (int)value);
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}	
		}
	}
	
	// efface un pc à l'ID correspondand
	public void deleteComputer(int id) {
		
		try {
			PreparedStatement ps = this.con.prepareStatement(DELETE_COMPUTER);
			ps.setInt(1,id);
			ps.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e+"exception due a la requete");
		}
		 
	}
		
			
		
}

