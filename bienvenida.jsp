<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Benvingut</title>
	</head>
	<body>
		<%
			String user = request.getParameter("usuario");
			
			String ini = request.getParameter("iniciado");
			
			String method = request.getParameter("method");
			
			StringBuilder strBldr = new StringBuilder();
			
			if (ini.equals("false")){
				
				strBldr.append("<h1>Conectado a la BD</h1>");
			}
			
			strBldr.append("<h1>Bienvenido usuario " + user + " (llamada " + method + ")</h1>");
			
			out.println(strBldr.toString());
			
		%>
		<form method=GET action=ConsultaLibrosServlet>
		
			Selección de Libro: <input type="text" name="titulo"><br><br>
			
			<input type="submit" value="Consulta Libros" name="submit">
			
		</form>
	</body>
</html>
