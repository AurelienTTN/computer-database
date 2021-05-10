package persistence;

public class Dao {
	// Properties
	String url;
	String login;
	String passwd;
	Connection connexion = null;

	// Constructor
	public Dao(String url, String login, String passwd) {
		this.url = url;
		this.login = login;
		this.passwd = passwd;

	}

	public void connection() {

		/* Chargement du driver JDBC pour MySQL */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			/* Gérer les éventuelles erreurs ici. (si java ne le trouve regarder le classpath */
		}
		
		

	}

}
