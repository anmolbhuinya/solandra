package com.solandra.datauploader.test;

import java.io.File;

import junit.framework.TestCase;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.CassandraConstant;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;
import com.solandra.cassandra.keyspacemanager.impl.KeySpaceManagerImpl;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;
import com.solandra.datauploader.DataUploader;
import com.solandra.datauploader.impl.CsvDataUploader;

public class DataUploaderTest extends TestCase{

	   private DataUploader dataUploader;
	   private String KEYSPACENAME="TEST_KEYSPACE";
	   private String fileName = "testFile.csv";
	   private KeySpaceManager keySpaceManager;
	   private SessionManager sessionManager;
	   
	   // assigning the values
	   protected void setUp(){
		   dataUploader = new CsvDataUploader();
		   keySpaceManager = new KeySpaceManagerImpl();
		   sessionManager = new SessionManagerImpl();
	   }
	   
	   protected void tearDown(){
		   Session session = sessionManager.createSession(CassandraConstant.CONTACT_POINT, CassandraConstant.PORT);
		   keySpaceManager.dropKeySpace(session , KEYSPACENAME);
		   if(null!=session){
			   session.close();
		   }
	   }

	   // test method to add two values
	   public void testUploadFile(){
		   String workingDirectory = System.getProperty("user.dir");
		   String workingPath = File.separator +"src"+File.separator+"test"+File.separator+"resources"+File.separator;
		   String filePath = workingDirectory+workingPath+fileName;
		   File file = new File(filePath);
		   boolean flag = dataUploader.uploadDataToCassandra(KEYSPACENAME, file );
		   assertTrue(flag);
	   }
	   
}
