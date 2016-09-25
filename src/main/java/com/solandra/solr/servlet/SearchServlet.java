package com.solandra.solr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.solandra.solr.searcher.Searcher;
import com.solandra.solr.searcher.impl.SolrJSearcher;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String queryParameters = request.getParameter("q");
		if(StringUtils.isBlank(queryParameters)){
			queryParameters = "*";
		}
		SolrQuery solrQuery = new SolrQuery(queryParameters);
		prepareFilterQuery(request, solrQuery);
		
		Searcher searcher = new SolrJSearcher();
		try {
			QueryResponse queryResponse = searcher.search(solrQuery);
			request.setAttribute("searchResult", queryResponse.toString());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
	}

	private void prepareFilterQuery(HttpServletRequest request, SolrQuery solrQuery) {
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			solrQuery.addFilterQuery("id:"+request.getParameter("id"));
		}
		if(StringUtils.isNotBlank(request.getParameter("city"))){
			solrQuery.addFilterQuery("city:"+request.getParameter("city"));
		}
		if(StringUtils.isNotBlank(request.getParameter("province"))){
			solrQuery.addFilterQuery("province:"+request.getParameter("province"));
		}
		if(StringUtils.isNotBlank(request.getParameter("country"))){
			solrQuery.addFilterQuery("country:"+request.getParameter("country"));
		}
		if(StringUtils.isNotBlank(request.getParameter("zipCode"))){
			solrQuery.addFilterQuery("zipCode:"+request.getParameter("zipCode"));
		}
		if(StringUtils.isNotBlank(request.getParameter("type"))){
			solrQuery.addFilterQuery("type:"+request.getParameter("type"));
		}
		if(StringUtils.isNotBlank(request.getParameter("hasAirCondition"))){
			solrQuery.addFilterQuery("hasAirCondition:"+"True");
		}
		if(StringUtils.isNotBlank(request.getParameter("hasGarden"))){
			solrQuery.addFilterQuery("hasGarden:"+"True");
		}
		if(StringUtils.isNotBlank(request.getParameter("hasPool"))){
			solrQuery.addFilterQuery("hasPool:"+"True");
		}
		if(StringUtils.isNotBlank(request.getParameter("isCloseToBeach"))){
			solrQuery.addFilterQuery("isCloseToBeach:"+"True");
		}
		if(StringUtils.isNotBlank(request.getParameter("dailyPrice"))){
			solrQuery.addFilterQuery("dailyPrice:"+request.getParameter("dailyPrice"));
		}
		if(StringUtils.isNotBlank(request.getParameter("currency"))){
			solrQuery.addFilterQuery("currency:"+request.getParameter("currency"));
		}
		if(StringUtils.isNotBlank(request.getParameter("rangeSearch"))){
			String minRoomNum = request.getParameter("roomsNumberMin");
			String maxRoomNum = request.getParameter("roomsNumberMax");
			solrQuery.addFilterQuery("roomsNumber:"+"["+minRoomNum+" TO "+maxRoomNum+"]");
		}else if(StringUtils.isNotBlank(request.getParameter("roomsNumber"))){
			solrQuery.addFilterQuery("roomsNumber:"+request.getParameter("roomsNumber"));
		}
		
        
	}
}
