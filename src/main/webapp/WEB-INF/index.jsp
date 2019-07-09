<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="todo" items="${listTodos}">
		<br>
		<c:out value="${todo.title}" />
		<br>
		<c:out value="${todo.getRegdate()}" />
	</c:forEach>


	<script>
		var request = new XMLHttpRequest(); // XMLHttpRequest 생성
		request.open("GET", "/addTodo"); // 데이터를 GET Method로 요청
		request.onreadystatechange = function() {
			if (request.readyState === 4 && request.status === 200) { // request가 끝났으며(4), 성공적(200)인 경우.
				console.log(request.responseText);
			}
		}
		request.send(null);
	</script>

</body>
</html>