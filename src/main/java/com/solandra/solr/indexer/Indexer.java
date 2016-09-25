package com.solandra.solr.indexer;

import java.util.List;

import org.apache.solr.common.SolrInputDocument;

public interface Indexer {

	public boolean indexDocuments(List<SolrInputDocument> solrInputDocumentList);
}
