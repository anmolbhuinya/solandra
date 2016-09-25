package com.solandra.solr.searcher.impl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

import com.solandra.solr.constant.SolrConstant;
import com.solandra.solr.searcher.Searcher;

public class SolrJSearcher implements Searcher{

	public QueryResponse search(SolrParams params) throws SolrServerException, IOException{
		SolrClient solrClient = new HttpSolrClient.Builder(SolrConstant.SOLR_URL+SolrConstant.SOLR_COLLECTION).build();
		
		QueryResponse queryResponse = solrClient.query(params);
		solrClient.close();
		
		return queryResponse;
	}
}
