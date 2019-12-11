package it.eng.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Table {
	HashMap colonne;
	
	private String nomeTabellaCompleto;
	private String nomeTabella;
	private List relation1to1;
	private List relation1toN;
	private List relationNto1;
	private List relationNtoNStruttura;
	private List relationNtoNAssociativa;
	private Relation relationStruttura;
	private Relation relationAssociativa;
	private boolean isStruttura;
	private boolean isAssociativa;
	
	private boolean isWrapped = false;
	
	private int sort;
	
	public Table() {
		super();
		colonne = new HashMap();
		relation1to1 = new ArrayList();
		relation1toN = new ArrayList();
		relationNto1 = new ArrayList();
		relationNtoNStruttura = new ArrayList();
		relationNtoNAssociativa = new ArrayList();
		relationStruttura = null;
		relationAssociativa = null;
		isStruttura = false;
		isAssociativa = false;
	}
	
	public Set getColumnNames(){
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
	
	public String toString() {
		String ret = "";
		ret+="\n\t*******"+nomeTabellaCompleto+"*******";
		Set set = colonne.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String colonnename = (String) iter.next();
			Column column = (Column)colonne.get(colonnename);
			ret+="\n\t"+column;			
		}
		return ret;
	}
	
	public List getRelation1to1(){
		return relation1to1;
	}
	
	public List getRelation1toN(){
		return relation1toN;
	}
	
	public List getRelationNto1(){
		return relationNto1;
	}
	
	public List getRelationNtoNAssociativa(){
		return relationNtoNAssociativa;
	}
	
	public List getRelationNtoNStruttura(){
		return relationNtoNStruttura;
	}
	
	public void addRelation1to1(Table table){
		relation1to1.add(new Relation(this,table,Relation.TYPE_RELATION_1_1));
	}

	public void addRelation1toN(Table table){
		relation1toN.add(new Relation(this,table,Relation.TYPE_RELATION_1_N));
	}

	public void addRelationNto1(Table table){
		relationNto1.add(new Relation(this,table,Relation.TYPE_RELATION_N_1));
	}

	public void addRelationNtoNStruttura(Table table12, Table table2) {
		relationNtoNStruttura.add(new Relation(this,table12,table2,Relation.TYPE_RELATION_N_N));
	}
	
	public void addRelationNtoNAssociativa(Table table12, Table table2) {
		relationNtoNAssociativa.add(new Relation(this,table12,table2,Relation.TYPE_RELATION_N_N));
	}
	
	public void setRelationStrutura(Table table1, Table table2) {
		relationStruttura = new Relation(table1,this,table2,Relation.TYPE_RELATION_STRUTTURA);
	}
	
	public void setRelationAssociativa(Table table1, Table table2) {
		relationAssociativa = new Relation(table1,this,table2,Relation.TYPE_RELATION_ASSOCIATIVA);
	}
	
	public Relation getRelationStruttura(){
		return relationStruttura;
	}
	
	public Relation getRelationAssociativa(){
		return relationAssociativa;
	}

	public void setIsStruttura() {
		isStruttura = true;
	}
	
	public boolean isStruttura(){
		return isStruttura;
	}
	
	public void setIsAssociativa() {
		isAssociativa = true;
	}
	
	public boolean isAssociativa(){
		return isAssociativa;
	}

	public boolean isWrapped() {
		return isWrapped;
	}

	public void setWrapped() {
		this.isWrapped = true;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
