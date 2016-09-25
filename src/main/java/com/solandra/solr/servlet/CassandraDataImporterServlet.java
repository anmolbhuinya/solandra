package com.solandra.solr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solandra.solr.dih.DataImportHandler;
import com.solandra.solr.dih.impl.CassandraDataImportHandler;

/**
 * Servlet to accept request for data import from cassandra to <br>
 * solr
 * 
 * @author anmol
 *
 */
public class CassandraDataImporterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DataImportHandler dataImportHandler = new CassandraDataImportHandler();
		boolean flag = dataImportHandler.importData();
		
		if(flag){
			request.getRequestDispatcher("/search.jsp").forward(request, response);
		}
	}
}
