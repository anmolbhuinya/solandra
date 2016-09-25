package com.solandra.sessionmanager.cassandra.test;

import junit.framework.TestCase;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.CassandraConstant;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;

public class SessionManagerTest extends TestCase{


	   private SessionManager sessionManager;
	   private Session session;
	   
	   // assigning the values
	   protected void setUp(){
	      sessionManager = new SessionManagerImpl();
	   }
	   
	   protected void tearDown(){
		   if(session!=null){
			   session.close();
		   }
	   }

	   // test method to add two values
	   public void testCreateSession(){
		   String contactPoint = CassandraConstant.CONTACT_POINT;
		   Integer port = CassandraConstant.PORT;
		   session = sessionManager.createSession(contactPoint, port );
		   
		   assertNotNull(session);
	   }
	   
	   public void testCloseSession(){
		   String contactPoint = CassandraConstant.CONTACT_POINT;
		   Integer port = CassandraConstant.PORT;
		   session = sessionManager.createSession(contactPoint, port );
		   
		   assertNotNull(session);
		   
		   assertTrue(sessionManager.closeSession(session));
	   }

}
