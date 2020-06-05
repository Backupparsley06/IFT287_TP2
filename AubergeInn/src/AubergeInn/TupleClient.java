package AubergeInn;

public class TupleClient {
	private int iDClient;
	private String nom;
	private String prenom;
	private int age;
	
	public TupleClient() {
	}
	
	public TupleClient(int iDClient, String nom, String prenom, int age) {
		this.setIDClient(iDClient);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
	}
	
	public int getIDClient()
    {
        return iDClient;
    }

    public void setIDClient(int iDClient)
    {
        this.iDClient = iDClient;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
