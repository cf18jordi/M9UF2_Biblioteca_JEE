package servlet;

@WebServlet("/ConsultaLibrosServlet")
public class ConsultaLibrosServlet extends HttpServlet{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException, ParseException {
		BaseDatos db = new BaseDatos();
		String boton = request.getParameter("submit");
		String filtro = "";
		if(boton.equals("Consulta Libros")) {
			filtro = request.getParameter("titulo");
		} else if (boton.equals("Insertar Libro")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			DateFormat formmater = new SImpleDateFormat("dd/MM/yyyy");
			Date fecha = formatter.parse(request.getParameter("fecha"));
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.insertarLibro(libro);
		}
		ArrayList<Libro> libros = db.consultaLibros(filtro);
		request.setAttribue("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
	}
}
