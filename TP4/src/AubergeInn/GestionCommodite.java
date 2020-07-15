package AubergeInn;


public class GestionCommodite {
	private TableCommodites tableCommodites;
	private TableChambres tableChambres;
	private TableInclusionCommodites tableInclusionCommodite;
	
	public GestionCommodite(TableCommodites tableCommodites, TableChambres tableChambres, TableInclusionCommodites tableInclusionCommodite)
	{
		this.tableCommodites = tableCommodites;
		this.tableChambres = tableChambres;
		this.tableInclusionCommodite = tableInclusionCommodite;
	}
	
	public void ajouter(int iDCommodite, String description, double surplusPrix)
            throws IFT287Exception
    {
        try
        {
        	if (tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe deja: " + iDCommodite);
        	if (surplusPrix < 0)
        		throw new IFT287Exception("Prix de base invalide");

            // Ajout du Client.
        	tableCommodites.Insert(iDCommodite, description, surplusPrix);
            
        }
        catch (Exception e)
        {

            throw e;
        }
    }
	
	public void inclure(int iDChambre, int iDCommodite)
            throws IFT287Exception
    {
        try
        {
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	if (!tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe pas: " + iDCommodite);
        	if (tableInclusionCommodite.existe(iDChambre, iDCommodite))
                throw new IFT287Exception("Commodite deja inclus: " + iDCommodite);

            // Ajout de l'inclusion.
        	tableInclusionCommodite.insert(iDChambre, iDCommodite);
            

        }
        catch (Exception e)
        {
            throw e;
        }
    }
	
	public void enlever(int iDChambre, int iDCommodite)
            throws IFT287Exception
    {
        try
        {
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	if (!tableCommodites.existe(iDCommodite))
                throw new IFT287Exception("Commodite existe pas: " + iDCommodite);
        	if (!tableInclusionCommodite.existe(iDChambre, iDCommodite))
                throw new IFT287Exception("Commodite pas inclus: " + iDCommodite);

            // Ajout de l'inclusion.
        	tableInclusionCommodite.delete(iDChambre, iDCommodite);
            

        }
        catch (Exception e)
        {

            throw e;
        }
    }
}
