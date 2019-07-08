
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="n" scope="request" value="10" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="todo" items="${data}">
		<br>
		<c:out value="${todo.getTitle()}" />
	</c:forEach>


	<script>
		function ajax(data) {
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				console.log(this.responseText);
			});
			oReq.open("POST", "/addTodo", true);//parameter를 붙여서 보낼수있음. 
			oReq.send();
		}

		ajax();
	</script>

</body>
</html>