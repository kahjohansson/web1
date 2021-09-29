<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Cadastro Profissional</title>
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
		
			
		<fieldset>
			<form name="cadastro" action="/<%= contextPath%>/admin/profissional/cadastra" method="POST">
				<div>
					<div>
						<p>CPF</p> 							
						<input type="text"  name="cpf" /> 
						
						<p><fmt:message key="name"/></p>	
						<input type="text" name="nome" value="${profissional.nome}"/> 
                        
                        <p>Email</p> 						
                        <input type="email"  name="email" value="${profissional.email}"/> 
                        
                        <p><fmt:message key="pass"/></p>		
                        <input type="password"  name="senha" value="${profissional.senha}"/> 
                        
                        <p><fmt:message key="area"/></p> 		
                        <input type="text" name="area" value="${profissional.area}"/> 
                        
                        <p><fmt:message key="espec"/></p> 		
                        <input type="text"  name="especialidade" value="${profissional.especialidade}"/> 
                        
                        <p><fmt:message key="resume"/></p> 		
                        <input type="text"  name="curriculo" value="${profissional.curriculo}"/> 
                     
					</div>
					<input type="submit" name="register" value=<fmt:message key="register"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>