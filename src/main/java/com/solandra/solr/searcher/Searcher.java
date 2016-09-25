package com.solandra.solr.searcher;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

/**
 * Searcher
 * 
 * @author anmol
 *
 */
public interface Searcher {

	/**
	 * Search based on search parameters
	 * 
	 * @param params
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	 QueryResponse search(SolrParams params) throws SolrServerException, IOException;
}
