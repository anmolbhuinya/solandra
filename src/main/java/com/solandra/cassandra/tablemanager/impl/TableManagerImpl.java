package com.solandra.cassandra.tablemanager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.CassandraConstant;
import com.solandra.cassandra.model.Table;
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
	 * First column would be primary key
	 * 
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
			
			if(CassandraConstant.VARCHAR.equalsIgnoreCase(columnFamilyType.get(columnName))){
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

	public Table readData(Session session, String tableName) {
		
		try{
		      //queries
		      String query = prepareQueryToRead(tableName);
		                             
		      //Executing the query
		     ResultSet rs = session.execute(query);
		     if(null==rs) return null;

		     Table table = new Table(new ArrayList<LinkedHashMap>());
		     Iterator<Row> iterator = rs.iterator();
		     Consumer<Row> rowConsumer = new CassandraRowConsumer(table);
			 iterator.forEachRemaining(rowConsumer);
		        
		      System.out.println("Data Read");
		      return table;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private String prepareQueryToRead(String tableName) {
		return "SELECT * FROM "+tableName+" ;";
	}

	public class CassandraRowConsumer implements Consumer<Row>{
		private Table table;
		
		public CassandraRowConsumer(Table table){
			this.table = table;
		}

		public void accept(Row row) {
			table.addRow(row);
		}
		
	}

	public boolean updateData() {
		// TODO Auto-generated method stub
		return false;
	}

}
