package AubergeInn;


public class TupleChambre {
	private int iDChambre;
	private String nom;
	private String typeLit;
	private double prixBase;
	
	public TupleChambre() {
	}
	
	public TupleChambre(int iDChambre, String nom, String typeLit, double prixBase) {
		this.setIDChambre(iDChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrixBase(prixBase);
	}
	
	public int getIDChambre()
    {
        return iDChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.iDChambre = iDChambre;
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

}
