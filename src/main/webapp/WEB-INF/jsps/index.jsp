<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<h1 style="position: fixed; transform: rotate(330deg); top: 60px;">
		나의 해야할 일들
	</h1>
	<a href="/add-todo" style="position: fixed; float: right; right: 20px; top: 20px;">
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
					<article class="card" id="ID${todo.id}" data-type="${todo.type}">
						<h3>
							${todo.title}
						</h3>
						<br>
						<h5>
							등록날짜: ${todo.regdateAsString},
							${todo.name}, 우선순위 ${todo.sequence}
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
					<article class="card" id="ID${todo.id}" data-type="${todo.type}">
						<h3>
							${todo.title}
						</h3>
						<br>
						<h5>
							등록날짜: ${todo.regdateAsString},
							${todo.name}, 우선순위 ${todo.sequence}
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
					<article class="card" id="ID${todo.id}" data-type="${todo.type}">
					<h3>
						${todo.title}
					</h3>
					<br>
					<h5>
						등록날짜: ${todo.regdateAsString},
						${todo.name}, 우선순위 ${todo.sequence}
					</h5>
					<button>→</button>
				</article>
			</c:forEach>

		</section>
	</div>

	<script>
	    function mouse_click_event(article) {
	        const article_info = article;
	        return () => {
	        	const move_information = 'id='+article.id.substring(2)+'&type='+article.dataset.type;
	        	
	        	request.open("POST", '/action');
	        	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	        	request.send(move_information);

	        	article_info.remove();
	        	
	        	if(article.dataset.type === 'TODO'){
	        		article_info.dataset.type = 'DOING';
	        		document.querySelector('#DOING').innerHTML += article_info.outerHTML;
	        	}else if(article.dataset.type === 'DOING'){
	        		article_info.dataset.type = 'DONE';
	        		document.querySelector('#DONE').innerHTML += article_info.outerHTML;
	        	}
	        	
        		document.querySelector('#'+article_info.id).lastElementChild.addEventListener('click', mouse_click_event(document.querySelector('#'+article_info.id)));
	        	
	        }
	    }
	
		const request = new XMLHttpRequest();
		request.onreadystatechange = () => {
			if (request.readyState === 4 && request.status === 200) { 
				console.log("ajax 성공");
			}
		}

		
        const cards = document.querySelectorAll('.card');
        
        for (let index = 0; index < cards.length; index++) {
        	cards[index].lastElementChild.addEventListener('click', mouse_click_event(cards[index]));
        }
        
	</script>

</body>
</html>