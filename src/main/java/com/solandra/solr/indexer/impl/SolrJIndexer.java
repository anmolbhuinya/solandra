package com.solandra.solr.indexer.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.solandra.solr.constant.SolrConstant;
import com.solandra.solr.indexer.Indexer;

public class SolrJIndexer implements Indexer{

	public boolean indexDocuments(List<SolrInputDocument> solrInputDocumentList) {
		SolrClient solrClient = new HttpSolrClient.Builder(SolrConstant.SOLR_URL+SolrConstant.SOLR_COLLECTION).build();
		try {
			UpdateResponse updateResponse = solrClient.add(solrInputDocumentList);
			System.out.println(updateResponse.getStatus());
			updateResponse = solrClient.commit();
			System.out.println(updateResponse.getStatus());
			return true;
		} catch (SolrServerException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				solrClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
