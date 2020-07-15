package AubergeInn;

import org.bson.Document;

public class TupleInclusionCommodite {
	private int idChambre;
	private int idCommodite;
	
	public TupleInclusionCommodite() {
	}
	
	public TupleInclusionCommodite(Document d) {
		this.setIDChambre(d.getInteger("idChambre"));
		this.setIDCommodite(d.getInteger("idCommodite"));
	}
	
	public TupleInclusionCommodite(int idChambre, int idCommodite) {
		this.setIDChambre(idChambre);
		this.setIDCommodite(idCommodite);
	}
	
	public int getIDChambre()
    {
        return idChambre;
    }

    public void setIDChambre(int idChambre)
    {
        this.idChambre = idChambre;
    }

	public int getIDCommodite()
    {
        return idCommodite;
    }

    public void setIDCommodite(int idCommodite)
    {
        this.idCommodite = idCommodite;
    }
    
    public Document toDocument()
    {
    	return new Document().append("idChambre", idChambre)
    			             .append("idCommodite", idCommodite);
    }
}
