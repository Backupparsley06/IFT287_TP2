package AubergeInn;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class TupleChambre {
	private int idChambre;
	private String nom;
	private String typeLit;
	private double prixBase;
	private List<TupleCommodite> commodites;
	
	public TupleChambre() {
		this.setCommodites();
	}
	
	public TupleChambre(Document d) {
		this.setIDChambre(d.getInteger("idChambre"));
		this.setNom(d.getString("nom"));
		this.setTypeLit(d.getString("typeLit"));
		this.setPrixBase(d.getDouble("prixBase"));
		setCommodites();
	}
	
	public TupleChambre(int iDChambre, String nom, String typeLit, double prixBase) {
		this.setIDChambre(iDChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrixBase(prixBase);
		this.setCommodites();
	}
	
	public int getIDChambre()
    {
        return idChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.idChambre = iDChambre;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getTypeLit()
    {
        return typeLit;
    }

    public void setTypeLit(String typeLit)
    {
        this.typeLit = typeLit;
    }

    public double getPrixBase()
    {
        return prixBase;
    }

    public void setPrixBase(double prixBase)
    {
        this.prixBase = prixBase;
    }
    
	public List<TupleCommodite> getCommodites()
    {
        return commodites;
    }

    public void setCommodites()
    {
        this.commodites = new ArrayList<TupleCommodite>();
    }
    
    public double getPrixTotal()
    {
    	double prix = getPrixBase();
    	for (TupleCommodite c : getCommodites()) 
    		prix += c.getSurplusPrix();
    	return prix;
    }
    
    public Document toDocument()
    {
    	return new Document().append("idChambre", idChambre)
    			             .append("nom", nom)
    			             .append("typeLit", typeLit)
    			             .append("prixBase", prixBase);
    }

}
