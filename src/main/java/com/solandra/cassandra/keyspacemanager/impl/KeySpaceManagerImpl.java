package com.solandra.cassandra.keyspacemanager.impl;

import org.apache.commons.lang3.StringUtils;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;

public class KeySpaceManagerImpl implements KeySpaceManager {

	public boolean createKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor){
		
		  if(null==session
				  || StringUtils.isBlank(keySpaceName)
				  || StringUtils.isBlank(strategy)
				  || null==replicationFactor
				  || 0>=replicationFactor){
			  return false;
		  }
		  
		  try{
			  //Query
		      String query = "CREATE KEYSPACE IF NOT EXISTS "+keySpaceName
		    	 +" WITH replication "
		         + "= {'class':'"+strategy+"', 'replication_factor':"+replicationFactor+"};";
		                    
		      //Executing the query
		      session.execute(query);
		      
		      System.out.println("Keyspace created"); 
		      return true;
		  }catch(Exception e){
			  System.out.println(e);
			  return false;
		  }

	}
	
	public boolean alterKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor) {
		  if(null==session
				  || StringUtils.isBlank(keySpaceName)
				  || StringUtils.isBlank(strategy)
				  || null==replicationFactor
				  || 0>=replicationFactor){
			  return false;
		  }
		  
		  try{
			  //Query
		      String query = "ALTER KEYSPACE "+keySpaceName
		    	 +" WITH replication "
		         + "= {'class':'"+strategy+"', 'replication_factor':"+replicationFactor+"};";
		                    
		      //Executing the query
		      session.execute(query);
		      
		      System.out.println("Keyspace altered"); 
		      return true;
		  }catch(Exception e){
			  System.out.println(e);
			  return false;
		  }
	}
	
	public boolean useKeySpace(Session session, String keySpaceName){
		if(null==session || StringUtils.isBlank(keySpaceName)) return false;
		
		try{
			String query = "USE "+keySpaceName +";";
			session.execute(query);
			System.out.println("Using "+keySpaceName);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}


	public boolean dropKeySpace(Session session, String keySpaceName) {
		if(null==session || StringUtils.isBlank(keySpaceName)) return false;
		
		try{
			String query = "DROP KEYSPACE "+keySpaceName + ";";
			session.execute(query);
			System.out.println("Keyspace dropped.");
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

}
