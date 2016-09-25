<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload Data to Cassandra</title>
</head>
<body>
        <h3>Choose File to Upload in Cassandra</h3>
        <form action="uploadFile" method="post" enctype="multipart/form-data">
                <input type="file" name="file" /> 
                <input type="submit" value="upload" />
        </form>
        
        
        <h3>Or</h3>
        <h3>Go To Search Page</h3>
        <a href="search.jsp">Search Rental</a>
        
        <h3>Or</h3>
        <h3>Index Cassandra Data To Solr</h3>
        <form method="post" action="importDataToSolrFromCassandra">
		    <button type="submit">Index</button>
	    </form>
</body>
</html>