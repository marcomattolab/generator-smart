package it.eng.generate;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Date;

public class Column {
	private String name;
	private Class typeColumn;
	private boolean key = false;
	private int columnSize;
	private boolean nullable =false;
	private String enumeration; //TODO Fill This!!
	
	/**
	 * @param key
	 * @param name
	 * @param column
	 */
	public Column() {
		super();
	}

	/**
	 * @return Returns the typeColumn.
	 */
	public Class getTypeColumn() {
		return typeColumn;
	}

	/**
	 * @param typeColumn The typeColumn to set.
	 */
	public void setTypeColumn(int typeColumn) {
		this.typeColumn = converterRequestSQLintoTypeJava(typeColumn);
	}

	/**
	 * @param isKey The isKey to set.
	 */
	public void setKey() {
		this.key = true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public Class typeColumn(){
		return typeColumn;
	}
	
	public boolean isKey(){
		return key;
	}
	
	public Class convertDBtoJava(String type){
		if(type.equals("VARCHAR") || type.equals("CHAR") || type.equals("String")) return String.class;
		else if(type.equals("DECIMAL") || type.equals("INT") || type.equals("TYNINT")) return BigDecimal.class;
		else if(type.equals("Boolean")) return Boolean.class;
		else if(type.equals("Long")) return Long.class;
		else if(type.equals("Double")) return Double.class;
		else if(type.equals("Integer")) return Integer.class;
		else if(type.equals("Date") || type.equals("LocalDate")) return Date.class;
		else return Object.class;
	}
	
	public String toString() {
		return "\n\t\t"+name+"-"+"-"+key+"-"+typeColumn.getName();
	}
	
	public static int converterTypeJavaintoRequestSQL(Object o) {
		if (o==null) {
			return Types.CHAR;
		}
		String classes = o.getClass().getName();
		if (classes.equals("java.lang.Integer"))
			return Types.INTEGER;
		else if (classes.equals("java.math.BigDecimal"))
			return Types.DECIMAL;
		else if (classes.equals("java.sql.Blob"))
			return Types.BLOB;
		else if (classes.equals("java.lang.Boolean"))
			return Types.BOOLEAN;
		else if (classes.equals("java.sql.Date") || classes.equals("java.util.Date"))
			return Types.DATE;
		else if (classes.equals("java.lang.Double"))
			return Types.DOUBLE;
		else if (classes.equals("java.lang.Float"))
			return Types.FLOAT;
		else if (classes.equals("java.lang.Long"))
			return Types.DECIMAL;
		else if (classes.equals("java.lang.Short"))
			return Types.SMALLINT;
		else if (classes.equals("java.lang.String"))
			return Types.CHAR;
		else if (classes.equals("java.sql.Time"))
			return Types.TIME;
		else if (classes.equals("java.sql.Timestamp"))
			return Types.TIMESTAMP;
		else
			return Types.CHAR;
	}
	
	public String getLabelType() {
		String classes = typeColumn.getName();
		if (classes.equals("java.lang.Integer")||classes.equals("java.lang.Long")||classes.equals("java.lang.Short"))
			return "Integer";
		else if (classes.equals("java.math.BigDecimal")||classes.equals("java.lang.Double")||classes.equals("java.lang.Float"))
			return "Numeric";
		else if (classes.equals("java.lang.Boolean"))
			return "Boolean";
		else if (classes.equals("java.sql.Date")||classes.equals("java.sql.Timestamp")||classes.equals("java.util.Date"))
			return "Date";
		else
			return "String";
	}
	
	public static Class converterRequestSQLintoTypeJava(int type) {
		if (type==Types.CHAR||type==Types.LONGVARCHAR||type==Types.VARCHAR)
			return String.class;
		else if (type==Types.INTEGER)
			return java.lang.Integer.class;
		else if (type==Types.DECIMAL)
			return java.math.BigDecimal.class;
		else if(type==Types.BLOB)
			return Blob.class;
		else if(type==Types.BOOLEAN)
			return Boolean.class;
		else if(type==Types.DATE)
			return LocalDate.class;
		else if(type==Types.DOUBLE)
			return Double.class;
		else if(type==Types.NUMERIC)
			return Long.class;
		else if(type==Types.FLOAT)
			return Float.class;
		else if(type==Types.SMALLINT)
			return java.lang.Short.class;
		else if (type==Types.TIME) 
			return java.sql.Time.class;
		else if (type==Types.TIMESTAMP) 
			return java.time.Instant.class;
		else if (type==Types.TIMESTAMP_WITH_TIMEZONE) //FIXME Check This!
			return java.time.ZonedDateTime.class;
		else
			return Object.class;
	}
	
	public static int corvertModelType(String cTypeColumn) {
		if ("String".equals(cTypeColumn)) 
			return Types.VARCHAR;
		else if ("Blob".equals(cTypeColumn))
			return Types.BLOB;
		else if ("Float".equals(cTypeColumn))
			return Types.FLOAT;
		else if ("Double".equals(cTypeColumn))
			return Types.DOUBLE;
		else if ("Integer".equals(cTypeColumn))
			return Types.INTEGER;
		else if ("BigDecimal".equals(cTypeColumn))
			return Types.DECIMAL;
		else if ("Long".equals(cTypeColumn))
			return Types.NUMERIC;
		else if ("Boolean".equals(cTypeColumn))
			return Types.BOOLEAN;
		else if ("LocalDate".equals(cTypeColumn) || "Date".equals(cTypeColumn))
			return Types.DATE;
		else if ("Time".equals(cTypeColumn))
			return Types.TIME;
		else if ("Instant".equals(cTypeColumn))
			return Types.TIMESTAMP;
		else if ("ZonedDateTime".equals(cTypeColumn))
			return Types.TIMESTAMP_WITH_TIMEZONE;
		else return Types.CHAR;
			
	}


	public void setColumnSize(int columnSize) {
		this.columnSize=columnSize;
	}
	
	public int getColumnSize() {
		return this.columnSize;
	}

	public void setNullable() {
		this.nullable = true;
	}

	public boolean isNullable() {
		return nullable;
	}

	public String getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

}
