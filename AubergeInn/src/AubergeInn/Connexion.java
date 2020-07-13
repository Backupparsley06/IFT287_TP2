package AubergeInn;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.<br><br>
 * 
 * Cette classe ouvre une connexion avec une BD via JDBC.<br>
 * La m√©thode serveursSupportes() indique les serveurs support√©s.<br>
 * <pre>
 * Pr√©-condition
 *   Le driver JDBC appropri√© doit √™tre accessible.
 * 
 * Post-condition
 *   La connexion est ouverte en mode autocommit false et s√©rialisable, 
 *   (s'il est support√© par le serveur).
 * </pre>
 * <br>
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * @author Marc Frappier - Universit√© de Sherbrooke
 * @version Version 2.0 - 13 novembre 2004
 * 
 * 
 * @author Vincent Ducharme - Universit√© de Sherbrooke
 * @version Version 3.0 - 21 mai 2016
 */
public class Connexion
{
    private MongoClient client;
    private MongoDatabase database;

    /**
     * Ouverture d'une connexion
     * 
     * @serveur serveur ‡ utiliser (local ou dinf)
     * @bd nom de la base de donnÈes
     * @user userid sur le serveur MongoDB pour la BD specifiÈe
     * @pass mot de passe sur le serveur MongoDB pour la BD specifiÈe
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
    	System.out.println("  ConnectÈ sur la BD MongoDB " + bd + " avec l'utilisateur " + user);
    }

    /**
     * fermeture d'une connexion
     */
    public void fermer()
    {
        client.close();
        System.out.println("Connexion fermÈe");
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