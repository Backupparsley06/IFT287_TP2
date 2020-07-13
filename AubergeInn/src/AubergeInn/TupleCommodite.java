package AubergeInn;

import org.bson.Document;

public class TupleCommodite {
	private int idCommodite;
	private String description;
	private double surplusPrix;
	
	public TupleCommodite() {
	}
	
	public TupleCommodite(Document d) {
		this.setIDCommodite(d.getInteger("idCommodite"));
		this.setDescription(d.getString("description"));
		this.setSurplusPrix(d.getDouble("surplusPrix"));
	}
	
	public TupleCommodite(int iDCommodite, String description, double surplusPrix) {
		this.setIDCommodite(iDCommodite);
		this.setDescription(description);
		this.setSurplusPrix(surplusPrix);
	}
	
	public int getIDCommodite()
    {
        return idCommodite;
    }

    public void setIDCommodite(int idCommodite)
    {
        this.idCommodite = idCommodite;
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
    
    public Document toDocument()
    {
    	return new Document().append("idCommodite", idCommodite)
    			             .append("description", description)
    			             .append("surplusPrix", surplusPrix);
    }
}
