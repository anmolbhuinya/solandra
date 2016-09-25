package com.solandra.cassandra.sessionmanager;

import com.datastax.driver.core.Session;

/**
 * Session Manager to manage cassandra session
 * 
 * @author anmol
 *
 */
public interface SessionManager {

	/**
	 * Creates Session
	 * 
	 * @param contactPoint
	 * @param port
	 * @return
	 */
	public Session createSession(String contactPoint, Integer port);

	/**
	 * Closes session
	 * 
	 * @param session
	 * @return
	 */
	public boolean closeSession(Session session);

}
