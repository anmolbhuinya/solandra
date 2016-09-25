package com.solandra.sessionmanager.cassandra.test;

import junit.framework.TestCase;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.Constant;
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
		   String contactPoint = Constant.CONTACT_POINT;
		   Integer port = Constant.PORT;
		   session = sessionManager.createSession(contactPoint, port );
		   
		   assertNotNull(session);
	   }
	   
	   public void testCloseSession(){
		   String contactPoint = Constant.CONTACT_POINT;
		   Integer port = Constant.PORT;
		   session = sessionManager.createSession(contactPoint, port );
		   
		   assertNotNull(session);
		   
		   assertTrue(sessionManager.closeSession(session));
	   }

}
