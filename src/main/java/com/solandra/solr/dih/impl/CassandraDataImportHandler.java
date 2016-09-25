package com.solandra.solr.dih.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.solr.common.SolrInputDocument;

import com.datastax.driver.core.Session;
import com.solandra.cassandra.constant.CassandraConstant;
import com.solandra.cassandra.keyspacemanager.KeySpaceManager;
import com.solandra.cassandra.keyspacemanager.impl.KeySpaceManagerImpl;
import com.solandra.cassandra.model.Table;
import com.solandra.cassandra.sessionmanager.SessionManager;
import com.solandra.cassandra.sessionmanager.impl.SessionManagerImpl;
import com.solandra.cassandra.tablemanager.TableManager;
import com.solandra.cassandra.tablemanager.impl.TableManagerImpl;
import com.solandra.exception.SolandraException;
import com.solandra.solr.dih.DataImportHandler;
import com.solandra.solr.indexer.Indexer;
import com.solandra.solr.indexer.impl.SolrJIndexer;

public class CassandraDataImportHandler implements DataImportHandler{
	private static final String KEYSPACE_NAME = "rental";//TODO: read from properties file
	private static final String TABLE_NAME = "rental";//TODO: read from properties file

	
	public boolean importData(){
		try{
			Session session = initCassandraSession();
			TableManager tableManager = new TableManagerImpl();
			Table table = tableManager.readData(session, TABLE_NAME);
            			
			List<SolrInputDocument> solrInputDocumentList = new ArrayList<SolrInputDocument>();
			populateSolrInputDocument(table, solrInputDocumentList);
			Indexer indexer = new SolrJIndexer();
			boolean flag = indexer.indexDocuments(solrInputDocumentList);
			
			session.close();
			return flag;
		}catch(SolandraException se){
			se.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * TODO: read data config for mapping column to field
	 * 
	 * @param table
	 * @param solrInputDocumentList
	 */
	private void populateSolrInputDocument(Table table,
			List<SolrInputDocument> solrInputDocumentList) {
		if(null==table) return;
		
		List<LinkedHashMap> rows = table.getRows();
		for(LinkedHashMap row: rows){
			if(null==row) continue;
			
			SolrInputDocument solrInputDocument = new SolrInputDocument();
			BiConsumer<String, Object> solrDocConsumer = new SolrDocConsumer(solrInputDocument);
			row.forEach(solrDocConsumer);
			
			solrInputDocumentList.add(solrInputDocument);
		}
	}
	
	public class SolrDocConsumer implements BiConsumer<String, Object>{
		private SolrInputDocument solrInputDocument;
		private Map<String, String> columnToFieldMap = new HashMap<String, String>();
		
		public SolrDocConsumer(SolrInputDocument solrInputDocument){
			this.solrInputDocument = solrInputDocument;
			populateColumnToFieldMap();
		}

		/**
		 * TODO: move out the mappings in xml
		 */
		private void populateColumnToFieldMap() {
			columnToFieldMap.put("id", "id");
			columnToFieldMap.put("city", "city");
			columnToFieldMap.put("province", "province");
			columnToFieldMap.put("country", "country");
			columnToFieldMap.put("zipcode", "zipCode");
			columnToFieldMap.put("type", "type");
			columnToFieldMap.put("hasaircondition", "hasAirCondition");
			columnToFieldMap.put("hasgarden", "hasGarden");
			columnToFieldMap.put("haspool", "hasPool");
			columnToFieldMap.put("isclosetobeach", "isCloseToBeach");
			columnToFieldMap.put("dailyprice", "dailyPrice");
			columnToFieldMap.put("currency", "currency");
			columnToFieldMap.put("roomsnumber", "roomsNumber");
		}

		public void accept(String key, Object value) {
			if(columnToFieldMap.containsKey(key)){
				solrInputDocument.addField(columnToFieldMap.get(key), value);
			}else{
				solrInputDocument.addField(key, value);
			}
		}
	}


	private Session initCassandraSession() {
		SessionManager sessionManager = new SessionManagerImpl();
		Session session = sessionManager.createSession(CassandraConstant.CONTACT_POINT, CassandraConstant.PORT);

		KeySpaceManager keySpaceManager = new KeySpaceManagerImpl();
		keySpaceManager.createKeySpace(session, KEYSPACE_NAME,
				CassandraConstant.STRATEGY, CassandraConstant.REPLICATION_FACTOR);
		keySpaceManager.useKeySpace(session, KEYSPACE_NAME);
		return session;
	}
}
