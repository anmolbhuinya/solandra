package com.solandra.cassandra.sessionmanager.impl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.solandra.cassandra.sessionmanager.SessionManager;

public class SessionManagerImpl implements SessionManager{

	public Session createSession(String contactPoint, Integer port){
		
		try{
		      //Creating Cluster object
		      Cluster cluster = Cluster.builder().addContactPoint(contactPoint).withPort(port).build();
		 
		      //Creating Session object
		      Session session = cluster.connect();
		      
		      return session;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public boolean closeSession(Session session) {
		if(null==session) return false;
		
        try{
        	session.close();
        	return true;
        }catch(Exception e){
        	return false;
        }
	}
	
}
