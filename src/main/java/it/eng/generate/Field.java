package it.eng.generate;

/**
 * Field Model
 */
public class Field {
	private String fname;
    private String ftype;
    private boolean frequired;
    private Integer fsize;
    private Integer fminlength;	//
    private Integer fmaxlength;	//
    private String fpattern;		//
    					
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

	public Integer getFsize() {
		return fsize;
	}

	public void setFsize(Integer fsize) {
		this.fsize = fsize;
	}

	public Integer getFminlength() {
		return fminlength;
	}

	public void setFminlength(Integer fminlength) {
		this.fminlength = fminlength;
	}

	public Integer getFmaxlength() {
		return fmaxlength;
	}

	public void setFmaxlength(Integer fmaxlength) {
		this.fmaxlength = fmaxlength;
	}

	public String getFpattern() {
		return fpattern;
	}

	public void setFpattern(String fpattern) {
		this.fpattern = fpattern;
	}
    
}
