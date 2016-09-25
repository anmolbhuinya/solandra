package com.solandra.cassandra.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Row;

/**
 * Table (Column family) model for cassandra table
 * 
 * @author anmol
 *
 */
public class Table{

	private List<LinkedHashMap> rows;

	public Table(ArrayList<LinkedHashMap> rows) {
		this.rows = rows;
	}

	public List<LinkedHashMap> getRows() {
		return rows;
	}

	public void setRows(List<LinkedHashMap> rows) {
		this.rows = rows;
	}

	public void addRow(Row row) {
		LinkedHashMap tRow = new LinkedHashMap();
		
		ColumnDefinitions columnDefinitions = row.getColumnDefinitions();
		int numOfColumns = columnDefinitions.size();
		
		for(int i=0; i<numOfColumns; i++){
			DataType dataType = columnDefinitions.getType(i);
			if(dataType.equals(DataType.cboolean())){
				tRow.put(columnDefinitions.getName(i), row.getBool(i));
			}else if(dataType.equals(DataType.cdouble())){
				tRow.put(columnDefinitions.getName(i), row.getDouble(i));
			}else if(dataType.equals(DataType.cint())){
				tRow.put(columnDefinitions.getName(i), row.getInt(i));
			}else if(dataType.equals(DataType.date())){
				tRow.put(columnDefinitions.getName(i), row.getDate(i));
			}else if(dataType.equals(DataType.varchar())){
				tRow.put(columnDefinitions.getName(i), row.getString(i));
			}
			else {
				tRow.put(columnDefinitions.getName(i), row.get(i, String.class));
			}
		}
		
		rows.add(tRow);
	}
}
