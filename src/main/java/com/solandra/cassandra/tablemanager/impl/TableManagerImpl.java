package com.solandra.cassandra.tablemanager.impl;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.Constant;
import com.solandra.cassandra.tablemanager.TableManager;

public class TableManagerImpl implements TableManager {

	public boolean createTable(Session session, String tableName, LinkedHashMap<String, String> columnFamilyType) {
		try{
			String cqlStatement = prepareQueryForCreateTable(tableName, columnFamilyType); 

			session.execute(cqlStatement);
			
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}

	}

	/**
	 * Sample query, assumption: first column would be primary key
	 * 
	 * 		String cqlStatement = "CREATE TABLE users (" + 
	 *	                      " user_name varchar PRIMARY KEY," + 
	 *	                      " password varchar " + 
	 *	                      ");";
	 * @param tableName
	 * @param columnFamily
	 * @return
	 */
	private String prepareQueryForCreateTable(String tableName,
			LinkedHashMap<String, String> columnFamily) {
		StringBuilder query = new StringBuilder("CREATE TABLE ");
		query.append(tableName);
		query.append(" (");
		boolean isFirstColumn = true;
		for(Entry<String, String> entry: columnFamily.entrySet()){
			String columnName = entry.getKey();
			String columnType = entry.getValue();
			
			query.append(columnName);
			query.append(" ");
			query.append(columnType);
			if(isFirstColumn){
				query.append(" PRIMARY KEY,");
				isFirstColumn = false;
			}else{
				query.append(",");
			}
		}
		query.setCharAt(query.lastIndexOf(","), ' ');
		query.append(");");
		return query.toString();
	}

	public boolean alterTable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteTable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean truncateTable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertData(Session session, String tableName, LinkedHashMap<String, String> columnFamily, LinkedHashMap<String, String> columnFamilyType) {
		
		try{
		      //queries
		      String query = prepareQueryToInsert(tableName, columnFamily, columnFamilyType);
		                             
		      //Executing the query
		      session.execute(query);
		        
		      System.out.println("Data Inserted");
		      return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Sample query
	 * 
	 *  String query = "INSERT INTO emp (emp_id, emp_name, emp_city,emp_phone, emp_sal)"
	 *     
	 *        + " VALUES(2,'robin', 'Hyderabad', 9848022339, 40000);" ;
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @return
	 */
	private String prepareQueryToInsert(String tableName,
			LinkedHashMap<String, String> columnFamily, LinkedHashMap<String, String> columnFamilyType) {
		StringBuilder query = new StringBuilder("INSERT INTO ");
		StringBuilder tableSubQuery = new StringBuilder(tableName).append("(");
		StringBuilder valueSubQuery = new StringBuilder("VALUES(");
		
		for(Entry<String, String> entry: columnFamily.entrySet()){
			String columnName = entry.getKey();
			String columnValue = entry.getValue();
			
			tableSubQuery.append(columnName).append(",");
			
			if(Constant.VARCHAR.equalsIgnoreCase(columnFamilyType.get(columnName))){
				valueSubQuery.append("'").append(columnValue).append("'").append(",");
			}else{
				valueSubQuery.append(columnValue).append(",");
			}
		}
		tableSubQuery.setCharAt(tableSubQuery.lastIndexOf(","), ')');
		valueSubQuery.setCharAt(valueSubQuery.lastIndexOf(","), ')');
		
		query.append(tableSubQuery).append(" ").append(valueSubQuery).append(";");
		return query.toString();
	}

	public boolean deleteData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean readData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateData() {
		// TODO Auto-generated method stub
		return false;
	}

}
