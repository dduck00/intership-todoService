<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<h1 style="position: fixed; transform: rotate(330deg); top: 60px;">나의
		해야할 일들</h1>
	<a href="/addTodo"
		style="position: fixed;; float: right; right: 20px; top: 20px;">
		<article class="title_row" style="margin: 0px; text-align: center;">
			<h1>새로운 TODO 등록</h1>
		</article>
	</a>
	<div>
		<section id="TODO">
			<article class="title_row">
				<h1>TODO</h1>
			</article>

			<c:forEach var="todo" items="${Todos}">
				<form action="/action" method="POST">
					<article class="card" id="<c:out value="${todo.id}" />"
						value="<c:out value="${todo.type}" />">
						<h3>
							<c:out value="${todo.title}" />
						</h3>
						<br>
						<h5>
							등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
							,
							<c:out value="${todo.name}" />
							, 우선순위
							<c:out value="${todo.sequence}" />
						</h5>
						<input type="submit" class = "button" value="→"></input>
						<input type="hidden" name="id" value="<c:out value="${todo.id}" />">
						<input type="hidden" name="type" value="<c:out value="${todo.type}" />">
					</article>
				</form>
			</c:forEach>

		</section>

		<section id="DOING">
			<article class="title_row">
				<h1>DOING</h1>
			</article>

			<c:forEach var="todo" items="${Doings}">
				<form action="/action" method="POST">
					<article class="card" id="<c:out value="${todo.id}" />"
						value="<c:out value="${todo.type}" />">
						<h3>
							<c:out value="${todo.title}" />
						</h3>
						<br>
						<h5>
							등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
							,
							<c:out value="${todo.name}" />
							, 우선순위
							<c:out value="${todo.sequence}" />
						</h5>
						<input type="submit" class="button" value="→"></input>
						<input type="hidden" name="id" value="<c:out value="${todo.id}" />">
						<input type="hidden" name="type" value="<c:out value="${todo.type}" />">
					</article>
				</form>
			</c:forEach>

		</section>

		<section id="DONE">
			<article class="title_row">
				<h1>DONE</h1>
			</article>

			<c:forEach var="todo" items="${Dones}">
				<form action="/action" method="POST">
					<article class="card" id="<c:out value="${todo.id}" />"
						value="<c:out value="${todo.type}"/>">
						<h3>
							<c:out value="${todo.title}" />
						</h3>
						<br>
						<h5>
							등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
							,
							<c:out value="${todo.name}" />
							, 우선순위
							<c:out value="${todo.sequence}" />
						</h5>
					</article>
				</form>
			</c:forEach>

		</section>
	</div>



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