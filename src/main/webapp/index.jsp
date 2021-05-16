<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/post" method="post">
    <p>Your name:</p>
    <input type="text" name="name" value="" size="30" autocomplete="off">
    <p>Your email:</p>
    <input type="email" name="email" autocomplete="off">
    <p>Your password:</p>
    <input type="password" name="password" autocomplete="off">
    <p>Your gender:</p>
    <input type="radio" id="m" value="Male" name="gender">
    <label for="m">Male</label>
    <input type="radio" id="f" value="Female" name="gender">
    <label for="f">Female</label>
    <p>About you:</p>
    <textarea name="about" cols="30" rows="10"></textarea>
    <p>Your skills:</p>
    <input type="checkbox" id="coding" value="coding" name="skills">
    <label for="coding">Coding</label>
    <input type="checkbox" id="design" value="design" name="skills">
    <label for="design">design</label>
    <input type="checkbox" id="management" value="management" name="skills">
    <label for="management">management</label>
    <p>Your country:</p>
    <input type="text" name="country" autocomplete="off">
    <p>Your town:</p>
    <input type="text" name="town" autocomplete="off"><br>
    <input type="reset"><br>
    <input type="submit" value="Send" name="submit">
</form>
</body>
</html>