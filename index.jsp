<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script>

function decode(){
	var encodevalue = document.getElementById("encodedvalue");
	
	$.post("http://localhost:7878/JSP/Servlet",encodevalue,function(data,err){
		if(err!=null){
		$("p").append("<span>"+data+"</span")
		}
	});
}

</script>
<body>

<h1> Form for registration</h1>
<form  action="Servlet" method="post" onsubmit="return hello();">
User:<input type="text" name="encodedvalue" id="encodedvalue" >

<input type ="submit" value="click me"  >


</form>
 


</body>
</html>