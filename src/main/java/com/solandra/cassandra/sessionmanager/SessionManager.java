package com.solandra.cassandra.sessionmanager;

import com.datastax.driver.core.Session;

public interface SessionManager {

	public Session createSession(String contactPoint, Integer port);

	public boolean closeSession(Session session);

}
