package AubergeInn;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.<br><br>
 * 
 * Cette classe ouvre une connexion avec une BD via JDBC.<br>
 * La méthode serveursSupportes() indique les serveurs supportés.<br>
 * <pre>
 * Pré-condition
 *   Le driver JDBC approprié doit être accessible.
 * 
 * Post-condition
 *   La connexion est ouverte en mode autocommit false et sérialisable, 
 *   (s'il est supporté par le serveur).
 * </pre>
 * <br>
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * @author Marc Frappier - Université de Sherbrooke
 * @version Version 2.0 - 13 novembre 2004
 * 
 * 
 * @author Vincent Ducharme - Université de Sherbrooke
 * @version Version 3.0 - 21 mai 2016
 */
public class Connexion
{
    private MongoClient client;
    private MongoDatabase database;

    /**
     * Ouverture d'une connexion
     * 
     * @serveur serveur a utiliser (local ou dinf)
     * @bd nom de la base de donnees
     * @user userid sur le serveur MongoDB pour la BD specifiee
     * @pass mot de passe sur le serveur MongoDB pour la BD specifiee
     */
    public Connexion(String serveur, String bd, String user, String pass) throws IFT287Exception
    {
    	if (serveur.equals("local"))
        {
            client = new MongoClient();
        }
        else if (serveur.equals("dinf"))
        {
        	MongoClientURI uri = new MongoClientURI("mongodb://"+user+":"+pass+"@bd-info2.dinf.usherbrooke.ca:27017/"+bd+"?ssl=false");
        	client = new MongoClient(uri);
        }
        else
        {
            throw new IFT287Exception("Serveur inconnu");
        }
        
    	database = client.getDatabase(bd);
    	
    	System.out.println("Ouverture de la connexion :");
    	System.out.println("  Connecte sur la BD MongoDB " + bd + " avec l'utilisateur " + user);
    }

    /**
     * fermeture d'une connexion
     */
    public void fermer()
    {
        client.close();
        System.out.println("Connexion fermee");
    }
    
    
    /**
     * retourne la Connection MongoDB
     */
    public MongoClient getConnection()
    {
        return client;
    }
    
    /**
     * retourne la DataBase MongoDB
     */
    public MongoDatabase getDatabase()
    {
        return database;
    }
}