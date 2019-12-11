package it.eng.generate;

public class Relation {
	
	public static final int TYPE_RELATION_1_1 = 0;
	public static final int TYPE_RELATION_1_N = 1;
	public static final int TYPE_RELATION_N_1 = 2;
	public static final int TYPE_RELATION_N_N = 3;
	public static final int TYPE_RELATION_STRUTTURA = 4;
	public static final int TYPE_RELATION_ASSOCIATIVA = 5;
	
	protected Table table1;
	protected Table table12;
	protected Table table2;
	private int type;
	
	public Relation(Table table1, Table table2, int type) {
		super();
		this.table1 = table1;
		this.table2 = table2;
		this.type = type;
	}
	
	public Relation(Table table1,Table table12, Table table2, int type) {
		super();
		this.table1 = table1;
		this.table12 = table12;
		this.table2 = table2;
		this.type = type;
	}

	public Table getTable1() {
		return table1;
	}
	
	public Table getTable12() {
		return table12;
	}

	public Table getTable2() {
		return table2;
	}
	
	public boolean isRelation1to1(){
		return type == TYPE_RELATION_1_1;
	}
	
	public boolean isRelation1toN(){
		return type == TYPE_RELATION_1_N;
	}
	
	public boolean isRelationNto1(){
		return type == TYPE_RELATION_N_1;
	}
	
	public boolean isRelationNtoN(){
		return type == TYPE_RELATION_N_N;
	}

}
