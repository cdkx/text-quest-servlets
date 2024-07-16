<jsp:useBean id="question" scope="request" type="com.eremin.textquestservlets.model.Question"/>
<jsp:useBean id="answer_1" scope="request" type="com.eremin.textquestservlets.model.Answer"/>
<jsp:useBean id="answer_2" scope="request" type="com.eremin.textquestservlets.model.Answer"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="ru">
<head>
    <title>Quest</title>
</head>
<body>
<h2>${question.getQuestion()}</h2>
<form action="${pageContext.request.contextPath}/question" method="get">
    <p>
        <input name="answerId" type="radio" value="${answer_1.getId()}">${answer_1.getName()}
        <br>
        <input name="answerId" type="radio" value="${answer_2.getId()}">${answer_2.getName()}
    </p>
    <p><input type="submit" value="Ответить"></p>

</form>
</body>
</html>
