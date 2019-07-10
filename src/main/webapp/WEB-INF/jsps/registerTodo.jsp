<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body style="text-align:center;">

    <form action="/addTodo" method="post">
        <div style="display:inline-block;margin:5% 25%; width:30% ;border: 1px solid black; text-align:left;padding:1% 10% 5% 10%">
            <h1>
                	할일 등록
            </h1>
            <label for="what_todo">
                <h4 class="left_align">
                    	어떤 일인가요?
                </h4>
            </label>
            <input type="text" name="title" id="what_todo" maxlength="24" placeholder="swift 공부하기">
            <label for="who_todo">
                <h4 class="left_align">
                   	 누가 할일인가요?
                </h4>
            </label>
            <input type="text" name="name" id="who_todo" placeholder="홍길동">

            <h4 class="left_align">
               	 우선순위를 선택하세요
            </h4>
            <input type="radio" name="sequence" value="1" id="sequence1">
            <label for="sequence1">1</label>
            <input type="radio" name="sequence" value="2" id="sequence2">
            <label for="sequence2">2</label>
            <input type="radio" name="sequence" value="3" id="sequence3">
            <label for="sequence3">3</label>

            <br>
            <div class="left_align" style="margin:0px;">
                <a href="/main" style="display: inline-flex;margin:0px;text-decoration: none;">
                    <div class="make_file_button">이전</div>
                </a>
                <input class="make_file_button" type="submit">
                <input class="make_file_button" type="reset">
            </div>
        </div>
    </form>
</body>

</html>