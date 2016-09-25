package com.solandra.cassandra.constant;

public interface CassandraConstant {

	String CONTACT_POINT = "127.0.0.1";
	Integer PORT = 9042;
	Integer REPLICATION_FACTOR = 2;
	String STRATEGY = "SimpleStrategy";
	String COMMA_DELIMITER = ",";
	
	//Cassandra data type
	String VARCHAR = "varchar";
	String BOOLEAN = "boolean";
	String DATE = "date";
	String DOUBLE = "double";
	String INTEGER = "int";

}
