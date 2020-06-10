// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package AubergeInn;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.sql.*;
import java.text.DecimalFormat;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    private static GestionAubergeInn gestionAubergeInn;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        gestionAubergeInn = null;
        
        try
        {
            // Il est possible que vous ayez à déplacer la connexion ailleurs.
            // N'hésitez pas à le faire!
        	gestionAubergeInn = new GestionAubergeInn(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (gestionAubergeInn != null)
            	gestionAubergeInn.fermer();
        }
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterClient"))
                {
                    // Lecture des parametres
                	int iDClient = readInt(tokenizer);
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    int age = readInt(tokenizer);
                    gestionAubergeInn.getGestionClient().ajouter(iDClient, nom, prenom, age);
                }
                else if (command.equals("supprimerClient"))
                {
                	int iDClient = readInt(tokenizer);
                	gestionAubergeInn.getGestionClient().supprimer(iDClient);
                }
                else if (command.equals("ajouterChambre"))
                {
                    // Lecture des parametres
                	int iDChambre = readInt(tokenizer);
                    String nom = readString(tokenizer);
                    String typeLit = readString(tokenizer);
                    double prixBase = readDouble(tokenizer);
                    gestionAubergeInn.getGestionChambre().ajouter(iDChambre, nom, typeLit, prixBase);
                }
                else if (command.equals("supprimerChambre"))
                {
                    // Lecture des parametres
                	int iDChambre = readInt(tokenizer);
                    gestionAubergeInn.getGestionChambre().supprimer(iDChambre);
                }
                else if (command.equals("ajouterCommodite"))
                {
                    // Lecture des parametres
                	int idCommodite = readInt(tokenizer);
                    String description = readString(tokenizer);
                    double surplusPrix = readDouble(tokenizer);
                    gestionAubergeInn.getGestionCommodite().ajouter(idCommodite, description, surplusPrix);
                }
                else if (command.equals("inclureCommodite"))
                {
                    // Lecture des parametres
                	int idChambre = readInt(tokenizer);
                	int idCommodite = readInt(tokenizer);
                    gestionAubergeInn.getGestionCommodite().inclure(idChambre, idCommodite);
                }
                else if (command.equals("enleverCommodite"))
                {
                    // Lecture des parametres
                	int idChambre = readInt(tokenizer);
                	int idCommodite = readInt(tokenizer);
                    gestionAubergeInn.getGestionCommodite().enlever(idChambre, idCommodite);
                }
                else if (command.equals("afficherChambresLibres"))
                {
                	
                	DecimalFormat df = new DecimalFormat("#0.00");
                	System.out.println("idChambre : nom : typeLit : prixDeLocation");
                	System.out.println("------------------------------------------");
                	for(TupleChambre tupleChambre : gestionAubergeInn.getGestionInterrogation().afficherChambresLibres()) {
            			System.out.println(tupleChambre.getIDChambre() 
            					+ " : " + tupleChambre.getNom()
            					+ " : " + tupleChambre.getTypeLit()
            					+ " : " + df.format(tupleChambre.getPrixTotal()));
                	}
                }
                else if (command.equals("afficherClient"))
                {
                    // Lecture des parametres
                	int idClient = readInt(tokenizer);
                	TupleClient client = gestionAubergeInn.getGestionInterrogation().afficherClient(idClient);
                	DecimalFormat df = new DecimalFormat("#0.00");
                	System.out.println("idClient : nom : prenom : age");
                	System.out.println("-----------------------------");
                	System.out.println(client.getIDClient() 
                			+ " : " + client.getNom()
                			+ " : " + client.getPrenom()
                			+ " : " + client.getAge());
                	
                	System.out.println("idReservation : idClient : idChambre : dateDebut : dateFin : prixDeLocation");
                	System.out.println("---------------------------------------------------------------------------");
                	for(TupleReservation tupleReservation: 
                		client.getReservations()) {
                		System.out.println(tupleReservation.getIDReservation() 
                    			+ " : " + tupleReservation.getIDClient()
                    			+ " : " + tupleReservation.getIDChambre()
                    			+ " : " + tupleReservation.getDateDebut()
                    			+ " : " + tupleReservation.getDateFin()
                    			+ " : " + df.format(tupleReservation.getPrix()));
            		}
                	System.out.println(" Total ");
                	System.out.println("-------");
                	System.out.println(df.format(client.getPrixTotal()));
                }
                else if (command.equals("afficherChambre"))
                {
                    // Lecture des parametres
                	int idChambre = readInt(tokenizer);
                    TupleChambre tupleChambre = gestionAubergeInn.getGestionInterrogation().afficherChambre(idChambre);
                    DecimalFormat df = new DecimalFormat("#0.00");
                	
                	System.out.println("idChambre : nom : typeLit : prixDeBase");
                	System.out.println("--------------------------------------");
                	System.out.println(tupleChambre.getIDChambre() 
        					+ " : " + tupleChambre.getNom()
        					+ " : " + tupleChambre.getTypeLit()
        					+ " : " + df.format(tupleChambre.getPrixBase()));
                	System.out.println("idCommodite : description : surPlusPrix");
                	System.out.println("---------------------------------------");
                	for (TupleCommodite tupleCommodite 
                			: tupleChambre.getCommodites()) {
                		System.out.println(tupleCommodite.getIDCommodite() 
            					+ " : " + tupleCommodite.getDescription()
            					+ " : " + df.format(tupleCommodite.getSurplusPrix()));
                	}
                	System.out.println(" Total ");
                	System.out.println("-------");
                	System.out.println(df.format(tupleChambre.getPrixTotal()));
                }
                else if (command.equals("reserver"))
                {
                    // Lecture des parametres
                	int idClient = readInt(tokenizer);
                	int idChambre = readInt(tokenizer);
                	Date dateDebut = readDate(tokenizer);
                	Date dateFin = readDate(tokenizer);
                    gestionAubergeInn.getGestionReservation().reserver(idClient, idChambre, dateDebut, dateFin);
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (IFT287Exception e)
        {
        	System.out.println("Erreur: " + e.getMessage());
        }
        catch (Exception e)
        {
        	// ON NE DEVRAIS JAMAIS RENTRER ICI!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("Une erreur impr�vue s'est produit.");
            System.out.println("Erreur: " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            
            //cx.rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws IFT287Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new IFT287Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new IFT287Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new IFT287Exception("Autre parametre attendu");
    }
    
    /**
     * Lecture d'un double java de la transaction entree a l'ecran
     */
    static double readDouble(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Double.valueOf(token).doubleValue();
            }
            catch (NumberFormatException e)
            {
                throw new IFT287Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new IFT287Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new IFT287Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new IFT287Exception("Autre parametre attendu");
    }

}
