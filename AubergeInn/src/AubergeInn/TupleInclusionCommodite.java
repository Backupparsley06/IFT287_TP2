package AubergeInn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleInclusionCommodite {
    @Id @GeneratedValue
    private long id;
	
	private int iDChambre;
	private int iDCommodite;
	
	public TupleInclusionCommodite() {
	}
	
	public TupleInclusionCommodite(int iDChambre, int iDCommodite) {
		this.setIDChambre(iDChambre);
		this.setIDCommodite(iDCommodite);
	}
	
	public int getIDChambre()
    {
        return iDChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.iDChambre = iDChambre;
    }

	public int getIDCommodite()
    {
        return iDCommodite;
    }

    public void setIDCommodite(int iDCommodite)
    {
        this.iDCommodite = iDCommodite;
    }
}
