<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <style type="text/css">
            .flash {color:red;}
        </style>
    </head>
    <body>
        <h1>Log in for excellent treasures.</h1>
        <c:if test="${not empty flash}">
        <h2 class="flash">${flash}</h2>
        </c:if>
        <form method="POST" action="main">
            <input type="hidden" name="action" value="login"/>
            Username: <input type="text" name="user" required/><br/>
            Password: <input type="password" name="pass" required/><br/>
            <input type="submit" value="Let me in"/>
        </form>
    </body>
</html>
