<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Admin Home</title>
    <meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
    <link href="styles.css" rel="stylesheet" type="text/css"/>
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
		
        <div class="sub_nav">
            <h1>
                <fmt:message key="welcome"/>
            </h1>
        </div>
		
		
		<div class="btns-sup">
			<div class=btns>
				<h2>
					<fmt:message key="professionalsManagement"/>
				</h2>
				<a href="/<%= contextPath%>/admin/profissional/pagina_cadastro">
					<div class="btn">
						<fmt:message key="insertion"/>
					</div>
				</a>
				<a href="/<%= contextPath%>/admin/profissional/lista">
					<div class="btn">
						<fmt:message key="rud"/>
					</div>
				</a>
				
			</div>
		</div>
		
		
    </fmt:bundle>
</body>

</html>
