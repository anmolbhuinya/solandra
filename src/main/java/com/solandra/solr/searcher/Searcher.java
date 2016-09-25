package com.solandra.solr.searcher;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

public interface Searcher {

	 QueryResponse search(SolrParams params) throws SolrServerException, IOException;
}
