package model;

public class HavingVO {
	private int pk;
	private String hname;
	private int hcnt;
	private String hstock;
	private int htotal ;
	private int hnprice;  
	private double hplpercent ; 
	private int hpltotal;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public int getHcnt() {
		return hcnt;
	}
	public void setHcnt(int hcnt) {
		this.hcnt = hcnt;
	}
	public String getHstock() {
		return hstock;
	}
	public void setHstock(String hstock) {
		this.hstock = hstock;
	}
	public int getHtotal() {
		return htotal;
	}
	public void setHtotal(int htotal) {
		this.htotal = htotal;
	}
	public int getHnprice() {
		return hnprice;
	}
	public void setHnprice(int hnprice) {
		this.hnprice = hnprice;
	}
	public double getHplpercent() {
		return hplpercent;
	}
	public void setHplpercent(double hplpercent) {
		this.hplpercent = hplpercent;
	}
	public int getHpltotal() {
		return hpltotal;
	}
	public void setHpltotal(int hpltotal) {
		this.hpltotal = hpltotal;
	}
	@Override
	public String toString() {
		return "HavingVO [pk=" + pk + ", hname=" + hname + ", hcnt=" + hcnt + ", hstock=" + hstock + ", htotal="
				+ htotal + ", hnprice=" + hnprice + ", hplpercent=" + hplpercent + ", hpltotal=" + hpltotal + "]";
	} 
	
}
