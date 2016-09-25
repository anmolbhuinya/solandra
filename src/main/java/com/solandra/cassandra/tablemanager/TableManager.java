package com.solandra.cassandra.tablemanager;

import java.util.LinkedHashMap;

import com.datastax.driver.core.Session;

public interface TableManager {

	boolean createTable(Session session, String tableName, LinkedHashMap<String, String> columnFamilyType);
	
	boolean alterTable();
	
	boolean deleteTable();
	
	boolean truncateTable();
	
	//CRUD operations
	
	boolean insertData(Session session, String tableName, LinkedHashMap<String, String> columnFamilyData, LinkedHashMap<String, String> columnFamilyType);
	
	boolean deleteData();
	
	boolean readData();
	
	boolean updateData();
}
