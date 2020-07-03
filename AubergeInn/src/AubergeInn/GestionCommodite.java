package AubergeInn;

import java.sql.SQLException;

public class GestionCommodite {
	private Connexion cx;
	private TableCommodites tableCommodites;
	private TableChambres tableChambres;
	private TableInclusionCommodites tableInclusionCommodite;
	
	public GestionCommodite(TableCommodites tableCommodites, TableChambres tableChambres, TableInclusionCommodites tableInclusionCommodite)
	{
		this.cx = tableCommodites.getConnexion();
		this.tableCommodites = tableCommodites;
		this.tableChambres = tableChambres;
		this.tableInclusionCommodite = tableInclusionCommodite;
	}
	
	public void ajouter(int iDCommodite, String description, double surplusPrix)
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	if (tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe deja: " + iDCommodite);
        	if (surplusPrix < 0)
        		throw new IFT287Exception("Prix de base invalide");

            // Ajout du Client.
        	tableCommodites.Insert(new TupleCommodite(iDCommodite, description, surplusPrix));
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void inclure(int iDChambre, int iDCommodite)
            throws IFT287Exception, SQLException
    {
        try
        {
        	cx.demarreTransaction();
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	if (!tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe pas: " + iDCommodite);
        	if (tableInclusionCommodite.existe(iDChambre, iDCommodite))
                throw new IFT287Exception("Commodite deja inclus: " + iDCommodite);

            // Ajout de l'inclusion.
        	tableInclusionCommodite.insert(new TupleInclusionCommodite(iDChambre, iDCommodite));
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void enlever(int iDChambre, int iDCommodite)
            throws IFT287Exception, SQLException
    {
        try
        {
        	cx.demarreTransaction();
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	if (!tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe pas: " + iDCommodite);
        	if (!tableInclusionCommodite.existe(iDChambre, iDCommodite))
                throw new IFT287Exception("Commodite pas inclus: " + iDCommodite);

            // Ajout de l'inclusion.
        	tableInclusionCommodite.delete(new TupleInclusionCommodite(iDChambre, iDCommodite));
            
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
