<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Cassandra Data Upload</title>
</head>
<body>
	<h4>${requestScope["message"]}</h4>
	
	<h2>Index Rental Data to Solr</h2>
	<form method="post" action="importDataToSolrFromCassandra">
		<button type="submit">Index</button>
	</form>
</body>
</html>