<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>Your name:</p>
<form action="${pageContext.request.contextPath}/post" method="post">
    <input type="text" name="Name" value="" size="30"/>
    <input type="submit" value="Send" name="submit">
</form>
</body>
</html>