<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${question}</title>
</head>
<body>

<h1>${question}</h1>
<button>
    ${answer_1}
</button>
<button onclick="location.href='/question?answers=2'">
    ${answer_2}
</button>

</body>
</html>
