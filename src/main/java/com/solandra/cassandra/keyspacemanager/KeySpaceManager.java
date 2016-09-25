package com.solandra.cassandra.keyspacemanager;

import com.datastax.driver.core.Session;

/**
 * Keyspacemanager to manage keyspace in cassandra
 * 
 * @author anmol
 *
 */
public interface KeySpaceManager {

	/**
	 * Creates keyspace
	 * 
	 * @param session
	 * @param keySpaceName
	 * @param strategy
	 * @param replicationFactor
	 * @return
	 */
	boolean createKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor);
	
	/**
	 * alter key space
	 * 
	 * @param session
	 * @param keySpaceName
	 * @param strategy
	 * @param replicationFactor
	 * @return
	 */
	boolean alterKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor);

	/**
	 * Uses keyspace
	 * 
	 * @param session
	 * @param keySpaceName
	 * @return
	 */
	public boolean useKeySpace(Session session, String keySpaceName);
	
	/**
	 * drops keyspace
	 * 
	 * @param session
	 * @param keySpaceName
	 * @return
	 */
	boolean dropKeySpace(Session session, String keySpaceName);

}
