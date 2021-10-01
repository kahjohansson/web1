<%@ page contentType="text/html" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

				<!DOCTYPE html>
				<html>

				<head>
					<title>Home</title>
					<meta charset="UTF-8">
					<link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css" />
				</head>

				<body>

					<% String contextPath=request.getContextPath().replace("/", "" ); %>


						<fmt:bundle basename="messages">
							<div class="nav">
								<h1>
									<fmt:message key="project" />
								</h1>

							</div>

							<div class="extremos">
								<h2>
									<fmt:message key="welcome" />
									${clienteLogado.nome}
								</h2>
							</div>

							<table border="1">

								<tr>
									<th>Nome</th>
									<th>Data</th>

								</tr>


								<c:forEach var="i" items="${listaConsulta}">

									<tr>
										<td>${i.nome}</td>
										<td>${i.data}</td>
									</tr>

								</c:forEach>


							</table>

						</fmt:bundle>
				</body>

				</html>