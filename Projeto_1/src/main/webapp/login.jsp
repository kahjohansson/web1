<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet" type="text/css"/>

</head>

<body>

	<% String contextPath = request.getContextPath().replace("/", ""); %>

	<fmt:bundle basename="messages">
						
		<div class="nav">
			<h1>
				<fmt:message key="project"/>			
			</h1>
			
		</div>
		
		<fieldset id="logCli">
			<p><fmt:message key="cliente"/></p>
			<form name="teste" action="/login" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="loginCliente" value=<fmt:message key="login"/> />
				</div>
			</form>
			<a href="/<%= contextPath%>/cliente/pagina_cadastro"><fmt:message key="createAccount"/></a>
		</fieldset>
		
		<fieldset class = "none" id="logPro">
			<p><fmt:message key="profissional"/></p>
			<form name="teste" action="/<%= contextPath%>/login" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="loginProfissional" value=<fmt:message key="login"/> />
				</div>
			</form>
			<a href="/<%= contextPath%>/"><fmt:message key="createAccount"/></a>
		</fieldset>

    </fmt:bundle>
</body>

</html>