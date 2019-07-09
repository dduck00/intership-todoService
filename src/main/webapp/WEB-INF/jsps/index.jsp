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
					<article class="card" data-id="${todo.id}"
						data-type="${todo.type}">
						<h3>
							${todo.title}
						</h3>
						<br>
						<h5>
							등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
							,
							${todo.name}
							, 우선순위
							${todo.sequence}
						</h5>
						<button>→</button>
					</article>
			</c:forEach>

		</section>

		<section id="DOING">
			<article class="title_row">
				<h1>DOING</h1>
			</article>

			<c:forEach var="todo" items="${Doings}">
					<article class="card" data-id="${todo.id}"
						data-type="${todo.type}">
						<h3>
							${todo.title}
						</h3>
						<br>
						<h5>
							등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
							,
							${todo.name}
							, 우선순위
							${todo.sequence}
						</h5>
						<button>→</button>
					</article>
			</c:forEach>

		</section>

		<section id="DONE">
			<article class="title_row">
				<h1>DONE</h1>
			</article>

			<c:forEach var="todo" items="${Dones}">
					<article class="card" data-id="${todo.id}"
						data-type="${todo.type}">
					<h3>
						${todo.title}
					</h3>
					<br>
					<h5>
						등록날짜: <fmt:formatDate type="date" pattern="yyyy.MM.dd" value="${todo.regdate}" />
						,
						${todo.name}
						, 우선순위
						${todo.sequence}
					</h5>
				</article>
			</c:forEach>

		</section>
	</div>

	<script>
	    function mouse_click_event(article) {
	        const article_info = article;
	        return () => {
	        	request.open("GET", "/action?id="+article.dataset.id+"&type="+article.dataset.type)
	        	request.send();
	        	
	        	if(article.dataset.type === 'TODO'){
	        		document.querySelector('#DOING').innerHTML += article_info.innerHTML;
	        	}else if(article.dataset.type==='DOING'){
	        		document.querySelector('#DONE').innerHTML += article_info.innerHTML;
	        	}
	        	
	        	
	        	article_info.remove();
	        	
	        	
	        	
	        	
	        }
	    }
	
		const request = new XMLHttpRequest(); // XMLHttpRequest 생성
		request.onreadystatechange = () => {
			if (request.readyState === 4 && request.status === 200) { // request가 끝났으며(4), 성공적(200)인 경우.
				console.log(request.responseText);
			}
		}

		
        const a = document.querySelectorAll('.card');
        console.log(a);
        for (var length = 0; length < a.length; length++) {
            console.dir(a[length].addEventListener('click', mouse_click_event(a[length])));
        }
        
	</script>

</body>
</html>