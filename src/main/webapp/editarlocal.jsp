<%@ page language="java" contentType="text/html; charset=UTF-81"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar Local</title>
</head>
<body>
<h1>Editar local</h1>
	<form name="frmLocal" action="updatelocal">
		<table>
		<tr>
				<td><input type="text" name="idlocal" readonly value="<% out.print(request.getAttribute("idlocal")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="endereco" value="<% out.print(request.getAttribute("endereco")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="capacidade" value="<% out.print(request.getAttribute("capacidade")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Alterar" onClick="validarlocal()">
	</form>
	<script src="scripts/validadorlocal.js"></script>

</body>
</html>