package com.solandra.cassandra.tablemanager.test;

import java.util.LinkedHashMap;

import junit.framework.TestCase;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.Constant;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;
import com.solandra.cassandra.keyspacemanager.impl.KeySpaceManagerImpl;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;
import com.solandra.cassandra.tablemanager.TableManager;
import com.solandra.cassandra.tablemanager.impl.TableManagerImpl;


public class TableManagerTest extends TestCase{

	private KeySpaceManager keySpaceManager;
	private SessionManager sessionManager;
	private Session session;
	private String KEYSPACENAME = "TEST_KEYSPACE";
	private TableManager tableManager;

	   // assigning the values
	   protected void setUp(){
		   sessionManager = new SessionManagerImpl();
		   session = sessionManager.createSession(Constant.CONTACT_POINT, Constant.PORT);
		   keySpaceManager = new KeySpaceManagerImpl();
		   keySpaceManager.createKeySpace(session, KEYSPACENAME, Constant.STRATEGY, Constant.REPLICATION_FACTOR);
		   keySpaceManager.useKeySpace(session, KEYSPACENAME);
		   tableManager = new TableManagerImpl();
	   }
	   
	   protected void tearDown(){
		   keySpaceManager.dropKeySpace(session, KEYSPACENAME);
		   if(null!=session){
			   session.close();
		   }
	   }
	   
	   public void testCreateTable(){
		   String tableName="TEST_FAMILY_COLUMN";
		   LinkedHashMap<String, String> columnFamily =getTestColumnFamily();
		   boolean flag = tableManager.createTable(session, tableName, columnFamily);
		   assertTrue(flag);
	   }
	   
	   public void testInsertData(){
		   String tableName="TEST_FAMILY_COLUMN";
		   LinkedHashMap<String, String> columnFamily =getTestColumnFamily();
		   boolean flag = tableManager.createTable(session, tableName, columnFamily);
		   assertTrue(flag);
	   
		   LinkedHashMap<String, String> columnFamilyData = getTestColumnFamilyData();
		   flag = tableManager.insertData(session, tableName, columnFamilyData, columnFamily);
		   assertTrue(flag);
	   }

	private LinkedHashMap<String, String> getTestColumnFamilyData() {
		LinkedHashMap<String, String> testColumnFamily = new LinkedHashMap<String, String>();
		testColumnFamily.put("id", "234");
		testColumnFamily.put("city", "LA");
		testColumnFamily.put("province", "province2");
		testColumnFamily.put("hasBath", "true");
		testColumnFamily.put("price", "45.34");
		testColumnFamily.put("rank", "23");
		return testColumnFamily;
	}

	private LinkedHashMap<String, String> getTestColumnFamily() {
		LinkedHashMap<String, String> testColumnFamily = new LinkedHashMap<String, String>();
		testColumnFamily.put("id", "varchar");
		testColumnFamily.put("city", "varchar");
		testColumnFamily.put("province", "varchar");
		testColumnFamily.put("hasBath", "boolean");
		testColumnFamily.put("price", "double");
		testColumnFamily.put("rank", "int");
		return testColumnFamily;
	}
}
