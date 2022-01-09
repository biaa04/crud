<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Editar Banda</title>
</head>
<body>
<h1>Editar banda</h1>
	<form name="frmBanda" action="update">
		<table>
		<tr>
				<td><input type="text" name="idbanda" readonly value="<% out.print(request.getAttribute("idbanda")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" value="<% out.print(request.getAttribute("nome")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="genero" value="<% out.print(request.getAttribute("genero")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Alterar" onClick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>