<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="modelbanda.JavaBeansBanda"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeansBanda> lista = (ArrayList<JavaBeansBanda>) request.getAttribute("bandaLista");

//teste
/*for(int i = 0; i < lista.size(); i++){
	out.println(lista.get(i).getIdbanda());
	out.println(lista.get(i).getNome());
	out.println(lista.get(i).getGenero());
}*/
%>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Shows</title>
</head>
<body>
	<h1>Bandas</h1>
	<a href="novabanda.html">Nova Banda</a>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Gênero</td>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdbanda()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getGenero()%></td>
				<td>
				<a href="select?idbanda=<%=lista.get(i).getIdbanda() %>">Editar</a>
				<a href=" javascript: confirmar(<%=lista.get(i).getIdbanda()%>)">Excluir</a>
				</td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>