package AubergeInn;

import java.sql.SQLException;

public class GestionChambre {
	private Connexion cx;
	private TableChambres tableChambres;
	private TableInclusionCommodites tableInclusionCommodite;
	
	public GestionChambre(TableChambres tableChambres, TableInclusionCommodites tableInclusionCommodite)
	{
		this.cx = tableChambres.getConnexion();
		this.tableChambres = tableChambres;
		this.tableInclusionCommodite = tableInclusionCommodite;
	}
	
	public void ajouter(int iDChambre, String nom, String typeLit, double prixBase)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe déjà : " + iDChambre);
        	if (prixBase < 0)
        		throw new IFT287Exception("Prix de base invalide");

            // Ajout du Client.
        	tableChambres.insert(iDChambre, nom, typeLit, prixBase);
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void supprimer(int iDChambre)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas : " + iDChambre);
        	
        	// suppression des commoditées inclus
        	tableInclusionCommodite.deleteOnChambre(iDChambre);

            // suppression de la Chambre.
        	tableChambres.delete(iDChambre);
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
