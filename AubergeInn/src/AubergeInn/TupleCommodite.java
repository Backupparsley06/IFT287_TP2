package AubergeInn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleCommodite {
    @Id @GeneratedValue
    private long id;
    
	private int iDCommodite;
	private String description;
	private double surplusPrix;
	
	public TupleCommodite() {
	}
	
	public TupleCommodite(int iDCommodite, String description, double surplusPrix) {
		this.setIDCommodite(iDCommodite);
		this.setDescription(description);
		this.setSurplusPrix(surplusPrix);
	}
	
	public int getIDCommodite()
    {
        return iDCommodite;
    }

    public void setIDCommodite(int iDCommodite)
    {
        this.iDCommodite = iDCommodite;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getSurplusPrix()
    {
        return surplusPrix;
    }

    public void setSurplusPrix(double surplusPrix)
    {
        this.surplusPrix = surplusPrix;
    }
}
