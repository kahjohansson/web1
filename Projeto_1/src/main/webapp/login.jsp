<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>

</head>

<body>

	<% String contextPath = request.getContextPath().replace("/", ""); %>

	<fmt:bundle basename="messages">
						
		<div class="nav">
			<h1>
				<fmt:message key="project"/>			
			</h1>
			
		</div>

		<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
		
		<fieldset id="login">
			<p><fmt:message key="cliente"/></p>
			<form name="l" action="/<%= contextPath%>/login" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="login_" value=<fmt:message key="login"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>