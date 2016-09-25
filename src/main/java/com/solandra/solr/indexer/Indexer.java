package com.solandra.solr.indexer;

import java.util.List;

import org.apache.solr.common.SolrInputDocument;

/**
 * Indexer to index solr input documents to solr.
 * 
 * @author anmol
 *
 */
public interface Indexer {

	/**
	 * Indexes solr input documents in solr
	 * 
	 * @param solrInputDocumentList
	 * @return
	 */
	public boolean indexDocuments(List<SolrInputDocument> solrInputDocumentList);
}
