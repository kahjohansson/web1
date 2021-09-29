<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Atualização de profissional</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<fmt:bundle basename="messages">
	
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
			

			<form name="edicao" action="/<%= contextPath%>/profissional/atualizar" method="POST">
					<fieldset>
						<legend> EDICAO DE profissionalS </legend>
						
						CPF <br/>
						<input type="text" name="cpf" value="${profissional.cpf}" readonly/> <br/>
						
						NOME <br/>
						<input type="text" name="nome" value="${profissional.nome}"/> <br/>
						
						EMAIL <br/>
						<input type="email" name="email" value="${profissional.email}"/> <br/>
						
						SENHA <br/>
						<input type="password" name="senha" value="${profissional.senha}"/> <br/>
						
						ÁREA <br/>
						<input type="text" name="area" value="${profissional.area}"/> <br/>
						
						ESPECIALIDADE <br/>
						<input type="text" name="especialidade" value="${profissional.especialidade}"/> <br/>
						
						CURRÍCULO <br/>
						<input type="text" name="curriculo" value="${profissional.curriculo}"/> <br/>

						<input type="submit" name="enviar" value="atualizar" />
					</fieldset>
			</form>
		

    </fmt:bundle>
</body>

</html>