package com.solandra.cassandra.keyspacemanager;

import com.datastax.driver.core.Session;

public interface KeySpaceManager {

	boolean createKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor);
	
	boolean alterKeySpace(Session session, String keySpaceName, String strategy, Integer replicationFactor);

	public boolean useKeySpace(Session session, String keySpaceName);
	
	boolean dropKeySpace(Session session, String keySpaceName);

}
