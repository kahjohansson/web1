<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
    <link href="/styles.css" rel="stylesheet" type="text/css"/>
    <script src="ajaxNome.js"></script>
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	
	<fmt:bundle basename="messages">
		
	<div class="nav">
			<h1>
				<fmt:message key="project"/>			
			</h1>
			
		</div>
		
		<!-- <div class="extremos">
			<h2>
				<fmt:message key="welcome"/>
				${clienteLogado.nome}
			</h2>
			<a class="btn" href="/<%= contextPath%>/cliente/home.jsp">Home</a>		
	
		</div> -->
	<p><fmt:message key="CliqueEm"/>.</p>
	<table border="1">
		
		<tr>
			<th>Nome</th>
			<th>Especialidade</th>
			<th>Área</th>
		</tr>
		<c:forEach var="profissionais" items="${requestScope.listaProfissionais}">
			
			<tr>
				<td>${profissionais.nome}</td>
				<td>${profissionais.especialidade}</td>
				<td>${profissionais.area}</td>
				<td>
					<a href="/<%= contextPath%>/consultas/agendar?cpf=${profissionais.cpf}">
					AGENDAR
					</a>
				</td>
			</tr>
			
		</c:forEach>
	</table>
    </fmt:bundle>
</body>

</html>
