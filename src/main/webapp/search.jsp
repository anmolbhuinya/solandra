<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rental Search</title>

      <script type="text/javascript">
            function sayHello() {
               alert("Hello World")
            }
      </script>



</head>
<body>
        <a href="index.jsp">Home Page</a>

		<h3>Search Rental</h3>
        <form action="rentalSearch">
			  Search<br>
			  <input type="text" name="q" value="">
			  <br>
			  <br>
			  <br>
			  <br>
			  Fields
			  <br>
			  id:<br><input type="text" name="id" value=""><br><br>
			  city:<br><input type="text" name="city" value=""><br><br>
			  province:<br><input type="text" name="province" value=""><br><br>
			  country:<br><input type="text" name="country" value=""><br><br>
			  zipCode:<br><input type="text" name="zipCode" value=""><br><br>
			  type:<br><input type="text" name="type" value=""><br><br>
			  <input type="checkbox" name="hasAirCondition" value="hasAirCondition">AirCondition<br><br>
			  <input type="checkbox" name="hasGarden" value="hasGarden">Garden<br><br>
			  <input type="checkbox" name="hasPool" value="hasPool">Pool<br><br>
			  <input type="checkbox" name="isCloseToBeach" value="isCloseToBeach">CloseToBeach<br><br>
			  dailyPrice:<br><input type="text" name="dailyPrice" value=""><br><br>
			  currency:<br><input type="text" name="currency" value=""><br><br>
			  roomsNumber:<br><input type="text" name="roomsNumber" value=""><br><br>
			  Range Search: RoomsNumber:<input type="radio" name="rangeSearch" value="rangeSearch"><br>
			  Min (Inclusive): <input type="text" name="roomsNumberMin" value="">
			  Max (Inclusive): <input type="text" name="roomsNumberMax" value="">
			  
			  <br>
			  <br>
			  <input type="submit" value="Submit">
		</form>

</body>
</html>