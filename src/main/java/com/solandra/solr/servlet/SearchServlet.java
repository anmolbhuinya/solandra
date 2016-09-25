package com.solandra.solr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

import com.solandra.solr.searcher.Searcher;
import com.solandra.solr.searcher.impl.SolrJSearcher;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Searcher searcher = new SolrJSearcher();
		SolrParams params = null;
		try {
			QueryResponse queryResponse = searcher.search(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
	}
}
