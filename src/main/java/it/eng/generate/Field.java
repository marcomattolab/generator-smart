package it.eng.generate;

/**
 * Field Model
 */
public class Field {
	
	private String fname;
    private String ftype;
    private boolean frequired;
    
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getFtype() {
		return ftype;
	}
	
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
	public boolean isFrequired() {
		return frequired;
	}
	
	public void setFrequired(boolean frequired) {
		this.frequired = frequired;
	}
    
}
