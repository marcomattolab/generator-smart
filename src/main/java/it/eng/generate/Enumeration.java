package it.eng.generate;
import java.util.ArrayList;
import java.util.List;

public class Enumeration {
	private String nomeEnumeration;
	private List<String> valoriEnumeration;
	
	public Enumeration() {
		super();
		valoriEnumeration = new ArrayList<>();
	}
	
	public Enumeration(String nomeEnumeration, List<String> valoriEnumeration) {
		super();
		this.valoriEnumeration = valoriEnumeration;
		this.nomeEnumeration = nomeEnumeration;
	}
	
	public String getNomeEnumeration() {
		return nomeEnumeration;
	}

	public void setNomeEnumeration(String nomeEnumeration) {
		this.nomeEnumeration = nomeEnumeration;
	}

	public List<String> getValoriEnumeration() {
		return valoriEnumeration;
	}

	public void setValoriEnumeration(List<String> valoriEnumeration) {
		this.valoriEnumeration = valoriEnumeration;
	}

}
