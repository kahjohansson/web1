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
		
		
	<table border="1">
		
		<tr>
			<th>CPF</th>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Senha</th>
			<th>Área</th>
			<th>Especialidade</th>
			<th>Curriculo</th>
			<th>Ação</th>

		</tr>
		<c:forEach var="profissional" items="${requestScope.listaProfissional}">
			
			<tr>
				<td>${profissional.cpf}</td>
				<td>${profissional.nome}</td>
				<td>${profissional.email}</td>
				<td>${profissional.senha}</td>
				<td>${profissional.area}</td>
				<td>${profissional.especialidade}</td>
				<td>${profissional.curriculo}</td>
				<td>
					<a href="/<%= contextPath%>/profissional/editar?cpf=${cliente.cpf}">
					EDITAR
					</a>
					<a href="/<%= contextPath%>/profissional/remover?cpf=${cliente.cpf}">
					REMOVER
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
    </fmt:bundle>
</body>

</html>
