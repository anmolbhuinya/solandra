package com.solandra.datauploader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.CassandraConstant;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;
import com.solandra.cassandra.keyspacemanager.impl.KeySpaceManagerImpl;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;
import com.solandra.cassandra.tablemanager.TableManager;
import com.solandra.cassandra.tablemanager.impl.TableManagerImpl;
import com.solandra.datauploader.DataUploader;
import com.solandra.exception.SolandraException;

public class CsvDataUploader implements DataUploader {

	public boolean uploadDataToCassandra(String keySpaceName, File file) {
		if (StringUtils.isBlank(keySpaceName) || null == file)
			return false;

		try{
			SessionManager sessionManager = new SessionManagerImpl();
			Session session = sessionManager.createSession(CassandraConstant.CONTACT_POINT, CassandraConstant.PORT);

			KeySpaceManager keySpaceManager = new KeySpaceManagerImpl();
			keySpaceManager.createKeySpace(session, keySpaceName,
					CassandraConstant.STRATEGY, CassandraConstant.REPLICATION_FACTOR);
			keySpaceManager.useKeySpace(session, keySpaceName);
			insertDataToCassandra(session, file);
			session.close();
			return true;
		}catch(SolandraException se){
			se.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private void insertDataToCassandra(Session session, File file) throws SolandraException{

		BufferedReader fileReader = null;

		try {
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(file));

			// Table name
			String tableName = file.getName();
			if(StringUtils.isNotBlank(tableName)){
				tableName = tableName.substring(0, tableName.indexOf("."));
			}

			// Read the CSV file header
			String headerLine = fileReader.readLine();
			String[] headerArr = headerLine.split(CassandraConstant.COMMA_DELIMITER);

			// Read the CSV second line (data type)
			String dataTypeLine = fileReader.readLine();
			String[] dataTypeArr = dataTypeLine.split(CassandraConstant.COMMA_DELIMITER);

			TableManager tableManager = new TableManagerImpl();
			createTable(tableManager, session, tableName, headerArr,
					dataTypeArr);
			insertData(tableManager, session, tableName, headerArr, dataTypeArr, fileReader);
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
			throw new SolandraException(e.getMessage(), e);
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
				throw new SolandraException(e.getMessage(), e);
			}
		}

	}

	private void insertData(TableManager tableManager, Session session,
			String tableName, String[] headerArr,String[] dataTypeArr, BufferedReader fileReader)
			throws IOException {
		LinkedHashMap<String, String> columnFamilyType = new LinkedHashMap<String, String>();
		for (int i = 0; i < headerArr.length; i++) {
			columnFamilyType.put(headerArr[i], dataTypeArr[i]);
		}
		tableManager.createTable(session, tableName, columnFamilyType);
		
		LinkedHashMap<String, String> columnFamilyData = new LinkedHashMap<String, String>();
		// Read the file line by line starting from the second line
		String line = "";
		while ((line = fileReader.readLine()) != null) {
			// Get all tokens available in line
			String[] tokens = line.split(CassandraConstant.COMMA_DELIMITER);
			if (tokens.length > 0) {
				for (int i = 0; i < headerArr.length; i++) {
					columnFamilyData.put(headerArr[i], tokens[i]);
				}
				tableManager.insertData(session, tableName, columnFamilyData, columnFamilyType);
			}
		}
	}

	private void createTable(TableManager tableManager, Session session,
			String tableName, String[] headerArr, String[] dataTypeArr) {
		LinkedHashMap<String, String> columnFamilyType = new LinkedHashMap<String, String>();

		for (int i = 0; i < headerArr.length; i++) {
			columnFamilyType.put(headerArr[i], dataTypeArr[i]);
		}

		tableManager.createTable(session, tableName, columnFamilyType);
	}

}
