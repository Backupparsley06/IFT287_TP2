package AubergeInn;

import java.sql.SQLException;

public class GestionCommodite {
	private Connexion cx;
	private TableCommodites tableCommodites;
	private TableChambres tableChambres;
	
	public GestionCommodite(TableCommodites tableCommodites, TableChambres tableChambres)
	{
		this.cx = tableCommodites.getConnexion();
		this.tableCommodites = tableCommodites;
		this.tableChambres = tableChambres;
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
        	
        	TupleChambre chambre = tableChambres.getChambre(iDChambre);
        	TupleCommodite commodite = tableCommodites.getCommodite(iDCommodite);
        	
        	if (chambre.getCommodites().contains(commodite))
                throw new IFT287Exception("Commodite deja inclus: " + iDCommodite);

        	chambre.addCommodite(commodite);
            
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
        	
        	TupleChambre chambre = tableChambres.getChambre(iDChambre);
        	TupleCommodite commodite = tableCommodites.getCommodite(iDCommodite);
        	
        	if (!chambre.getCommodites().contains(commodite))
                throw new IFT287Exception("Commodite pas inclus: " + iDCommodite);
        	
        	chambre.getCommodites().remove(commodite);
            
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
