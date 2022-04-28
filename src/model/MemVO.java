package model;

public class MemVO {
	private String mid;
	private String mname;
	private int mwallet;
	private int mplwallet;
	private double mplpercent;
	private int mtotal  ;
	private int mpltotal  ;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMwallet() {
		return mwallet;
	}
	public void setMwallet(int mwallet) {
		this.mwallet = mwallet;
	}
	public int getMplwallet() {
		return mplwallet;
	}
	public void setMplwallet(int mplwallet) {
		this.mplwallet = mplwallet;
	}
	public double getMplpercent() {
		return mplpercent;
	}
	public void setMplpercent(double mplpercent) {
		this.mplpercent = mplpercent;
	}
	public int getMtotal() {
		return mtotal;
	}
	public void setMtotal(int mtotal) {
		this.mtotal = mtotal;
	}
	public int getMpltotal() {
		return mpltotal;
	}
	public void setMpltotal(int mpltotal) {
		this.mpltotal = mpltotal;
	}
	@Override
	public String toString() {
		return "MemVO [mid=" + mid + ", mname=" + mname + ", mwallet=" + mwallet + ", mplwallet=" + mplwallet
				+ ", mplpercent=" + mplpercent + ", mtotal=" + mtotal + ", mpltotal=" + mpltotal + "]";
	}
	
}
