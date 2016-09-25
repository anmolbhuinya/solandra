package com.solandra.cassandra.tablemanager;

import java.util.LinkedHashMap;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.model.Table;

/**
 * Table Manager to manage table (column family) in Cassandra
 * 
 * @author anmol
 *
 */
public interface TableManager {

	/**
	 * Creates table in cassandra under keyspace defined in session
	 * 
	 * @param session
	 * @param tableName
	 * @param columnFamilyType
	 * @return
	 */
	boolean createTable(Session session, String tableName, LinkedHashMap<String, String> columnFamilyType);
	
	/**
	 * Alters table
	 * TODO: needs to be modified and implemented
	 * 
	 * @return
	 */
	boolean alterTable();
	
	/**
	 * TODO needs to be modified and implemented
	 * 
	 * @return
	 */
	boolean deleteTable();
	
	/**
	 * TODO needs to be modified and implemented
	 * 
	 * @return
	 */
	boolean truncateTable();
	
	//CRUD operations
	/**
	 * inserts data to Cassandra
	 * 
	 * @param session
	 * @param tableName
	 * @param columnFamilyData
	 * @param columnFamilyType
	 * @return
	 */
	boolean insertData(Session session, String tableName, LinkedHashMap<String, String> columnFamilyData, LinkedHashMap<String, String> columnFamilyType);
	
	/**
	 * TODO: needs to be implemented
	 * @return
	 */
	boolean deleteData();
	
	/**
	 * Reads data from cassandra
	 * 
	 * @param session
	 * @param tableName
	 * @return
	 */
	Table readData(Session session, String tableName);
	
	/**
	 * TODO: needs to be modified and implemented
	 * 
	 * @return
	 */
	boolean updateData();
}
