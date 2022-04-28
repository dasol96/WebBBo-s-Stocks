package model;

public class StockVO {
	private int spk;
	private String sname;
	private int smkprice ;// -> 시가
	private int snprice;  //-> 현재가
	private double sudpercent; //-> 등락률
	private int sytrade ; //-> 전일거래량
	private int sntrade ;//-> 현재거래량
	public int getSpk() {
		return spk;
	}
	public void setSpk(int spk) {
		this.spk = spk;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSmkprice() {
		return smkprice;
	}
	public void setSmkprice(int smkprice) {
		this.smkprice = smkprice;
	}
	public int getSnprice() {
		return snprice;
	}
	public void setSnprice(int snprice) {
		this.snprice = snprice;
	}
	public double getSudpercent() {
		return sudpercent;
	}
	public void setSudpercent(double sudpercent) {
		this.sudpercent = sudpercent;
	}
	public int getSytrade() {
		return sytrade;
	}
	public void setSytrade(int sytrade) {
		this.sytrade = sytrade;
	}
	public int getSntrade() {
		return sntrade;
	}
	public void setSntrade(int sntrade) {
		this.sntrade = sntrade;
	}
	@Override
	public String toString() {
		return "StockVO [spk=" + spk + ", sname=" + sname + ", smkprice=" + smkprice + ", snprice=" + snprice
				+ ", sudpercent=" + sudpercent + ", sytrade=" + sytrade + ", sntrade=" + sntrade + "]";
	}
	
}
