<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Index</title>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet" type="text/css"/>
</head>

<body>

	<fmt:bundle basename="messages">
		<div class="nav">
			<h1>
				<fmt:message key="project"/>			
			</h1>
			
		</div>		
		
		<div class="btns-sup">
			<div class=btns>
				<h2>
					<fmt:message key="welcome"/>
				</h2>

				<a href="login">
					<div class="btn">
						<fmt:message key="login"/>
					</div>
				</a>

				
				<a href="profissional/lista">
					<div class="btn">
						<fmt:message key="listProfessionals"/>
					</div>
				</a>
				
			</div>
		</div>
		
		
    </fmt:bundle>
</body>

</html>
