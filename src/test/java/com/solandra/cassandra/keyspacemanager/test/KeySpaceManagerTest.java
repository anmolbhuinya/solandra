package com.solandra.cassandra.keyspacemanager.test;

import junit.framework.TestCase;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.Constant;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;
import com.solandra.cassandra.keyspacemanager.impl.KeySpaceManagerImpl;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;

public class KeySpaceManagerTest extends TestCase{
	
	private KeySpaceManager keySpaceManager;
	private SessionManager sessionManager;
	private Session session;

	   // assigning the values
	   protected void setUp(){
		   sessionManager = new SessionManagerImpl();
		   session = sessionManager.createSession(Constant.CONTACT_POINT, Constant.PORT);
		   keySpaceManager = new KeySpaceManagerImpl();
	   }
	   
	   protected void tearDown(){
		   if(null!=session){
			   session.close();
		   }
	   }
	   
	   public void testCreateKeySpace(){
		Integer replicationFactor = Constant.REPLICATION_FACTOR;
		String keySpaceName = "testKeySpace";
		String strategy = Constant.STRATEGY;
		boolean flag = keySpaceManager.createKeySpace(session, keySpaceName, strategy, replicationFactor);
		
		assertTrue(flag);
	   }
	   
	   public void testAlterKeySpace(){
			Integer replicationFactor = 1;
			String keySpaceName = "testKeySpace";
			String strategy = Constant.STRATEGY;
			boolean flag = keySpaceManager.alterKeySpace(session, keySpaceName, strategy, replicationFactor);
			
			assertTrue(flag);
	   }
	   
	   public void testUseKeySpace(){

			Integer replicationFactor = Constant.REPLICATION_FACTOR;
			String keySpaceName = "testKeySpace";
			String strategy = Constant.STRATEGY;
			boolean flag = keySpaceManager.createKeySpace(session, keySpaceName, strategy, replicationFactor);
			assertTrue(flag);
		   
		    flag = keySpaceManager.useKeySpace(session, keySpaceName);
		    assertTrue(flag);
	   }
	   
	   public void testDropKeySpace(){


			Integer replicationFactor = Constant.REPLICATION_FACTOR;
			String keySpaceName = "testKeySpace";
			String strategy = Constant.STRATEGY;
			boolean flag = keySpaceManager.createKeySpace(session, keySpaceName, strategy, replicationFactor);
			assertTrue(flag);
		   
		    flag = keySpaceManager.dropKeySpace(session, keySpaceName);
		    assertTrue(flag);
	   
	   }
}
