<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quest</title>
</head>
<body>
<header>
    <h1>${question.getQuestion()}</h1>
    <br>
    <form action="${pageContext.request.contextPath}/question" method="get">
        <button>Начать игру заново</button>
    </form>
</header>
</body>
</html>
