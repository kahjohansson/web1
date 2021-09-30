<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Admin Home</title>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet" type="text/css"/>
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<fmt:bundle basename="messages">
		<div class="nav">
			<h1>
				<fmt:message key="admin_area"/>			
			</h1>
			
		</div>		
		
		<div class="btns-sup">
			<div class=btns>
				<h2>
					<fmt:message key="welcome"/>
					${usuarioLogado.nome}
				</h2>
				<a href="/<%= contextPath%>/admin/cliente">
					<div class="btn">
						<fmt:message key="crudClientes"/>
					</div>
				</a>
				<a href="/<%= contextPath%>/admin/profissional">
					<div class="btn">
						<fmt:message key="crudProfissionais"/>
					</div>
				</a>
				
			</div>
		</div>
		
		
    </fmt:bundle>
</body>

</html>
