<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelbanda.JavaBeansLocal"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeansLocal> listaL = (ArrayList<JavaBeansLocal>) request.getAttribute("localLista");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Shows</title>
</head>
<body>
	<h1>Locais</h1>
	<a href="novolocal.html">Novo Local</a>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Endereço</td>
				<td>Capacidade</td>
			</tr>
		</thead>
		<tbody>

			<%
			for (int i = 0; i < listaL.size(); i++) {
			%>
			<tr>
				<td><%=listaL.get(i).getIdlocal()%></td>
				<td><%=listaL.get(i).getEndereco()%></td>
				<td><%=listaL.get(i).getCapacidade()%></td>
				<td>
				<a href="select?idlocal=<%= listaL.get(i).getIdlocal() %>">Editar</a>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>