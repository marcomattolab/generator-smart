package it.eng.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Table {
	HashMap<String, Column> colonne;
	private String nomeTabellaCompleto;
	private String nomeTabella;
	private List<String> profiles;
	private int sort;
	
	public Table() {
		super();
		colonne = new HashMap<>();
	}
	
	public List<Column> getColumns(){
		List<Column> list = new ArrayList<>(colonne.values());
		return list;
	}

	public List<Column> getSortedColumns(){
		List<Column> list = new ArrayList<Column>(colonne.values());
		//DEVELOP SORTING FOR COLUMS
		Collections.sort(list, (d1, d2) -> {
			return d1.getSortColumn() - d2.getSortColumn();
		});
		return list;
	}
	
	public Set<String> getColumnNames(){
		return colonne.keySet();
	}
	
	public Column getColumn(String key){
		return (Column) colonne.get(key);
	}
	
	public String getNomeTabellaCompleto(){
		return nomeTabellaCompleto;
	}
	
	public void setNomeTabellaCompleto(String nomeTabellaCompleto){
		this.nomeTabellaCompleto = nomeTabellaCompleto;
	}
	
	public void setNomeTabella(String nomeTabella){
		this.nomeTabella = nomeTabella;
	}
	
	public String getNomeTabella(){
		return nomeTabella;
	}
	
	public void addColumn(Column column){
		colonne.put(column.getName(), column);
	}

	public void removeColumn(Column column){
		colonne.remove(column.getName());
	}
	
	public String toString() {
		String ret = "";
		ret+="\n\t*******"+nomeTabellaCompleto+"*******";
		Set<String> set = colonne.keySet();
		for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
			String colonnename = (String) iter.next();
			Column column = (Column)colonne.get(colonnename);
			ret+="\n\t"+column;			
		}
		return ret;
	}
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

}
