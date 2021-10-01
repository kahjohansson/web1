<%@ page contentType="text/html" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

				<!DOCTYPE html>
				<html>

				<head>
					<title>Upload de currículo</title>
					<meta charset="UTF-8">
					<link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css" />
					<link href="styles.css" rel="stylesheet" type="text/css" />
				</head>

				<body>

					<% String contextPath=request.getContextPath().replace("/", "" ); %>

						<fmt:bundle basename="messages">

							<div class="nav">
								<h1>
									<fmt:message key="project" />
								</h1>

							</div>


							<fieldset>
								<form action="/<%= contextPath%>/upload" method="post" enctype="multipart/form-data">
									<input type="file" name="guru_file" size="50" />
									<br />
									<input type="submit" value="Upload" />
								</form>
							</fieldset>

						</fmt:bundle>
				</body>

				</html>