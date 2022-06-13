
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import servlet.Libro;

public class BaseDatos{
    private Connection conexion;
    
    public BasedeDades(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String conex = "jdbc:mysql://localhost:3306/usuarios";
            this.conexion = DriverManager.getConnection(conex, "root", "root"); 
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean compruebaUsuario(String usuario, String password){
        boolean check = false;
        try {
            Statement s = conexion.createStatement();
            String sql = "SELECT count(*) FROM USUARIOS WHERE usuario='" + usuario + "' and password='" + password + "'";
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            rs.next();
            if(rs.getInt(1)>0){
                check = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    public ArrayList<Libro> consultaLibros (String filtro){
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            Statement s = conexion.createStatement();
            String sql = "SELECT * FROM LIBROS WHERE TITULO LIKE '%" + filtro + "%'";
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            while (rs.next()){
                Libro libro = new Libro(rs.getInt(1),
                                        rs.getString(2),
                                        rs.getString(3), 
                                        rs.getString(4),
                                        rs.getDate(5),
                                        rs.getString(6),
                                        rs.getInt(7));
                lista.add(libro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public void insertarLibro(libro libro){
        String query = "INSERT INTO libros (id, titulo, autor, editorial, fecha, categoria, novedad) values (?, ?, ?, ?, ?, ?, ?)";
        try {
			
            PreparedStatement ps;
            
            ps = conexion.PreparedStatement(query);
            ps.setInt(1, libro.getId());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setString(5, libro.getEditorial());
            ps.setString(6, libro.getCategoria());
            ps.setInt(7, libro.getNovedad());

            java.sql.Date sqlDate = new java.sql.Date(libro.getFecha().getTime());
            ps.setDate(5, sqlDate);
            
        } catch (Exception e) {
			
            System.out.println(e.getMessage());
        }
    }
    public void eliminarLibro(String id){
		
        String query = = "DELETE FROM libros WHERE id =" + id;
        
        
        try {
            PreparedStatement ps = conexion.PreparedStatement(query);
            ps.executeUpdate();
            
        } catch (Exception e) {
			
            System.out.println(e.getMessage());
        }
    }
    
    public Libro recuperarLibro(String id){
        Libro libro = null;
        
        try {
            Statement s = conexion.createStatement();
            String sql = "SELECT * FROM libros WHERE id = " + id;
            s.execute(sql);
            
            ResultSet rs = s.getResultSet();
            rs.next();
            
            libro = new Libro(rs.getInt(1),
            
            rs.getString(2),
            rs.getString(3), 
            rs.getString(4),
            rs.getDate(5),
            rs.getString(6),
            rs.getInt(7));
            
        } catch (Exception e) {
			
            System.out.println(e.getMessage());
        }
        return libro;
    }
    
    public void modificarLibro(libro libro){
		
        String query = "UPDATE libros WHERE id = '" + libro.getId() + "'  
                        SET titulo  = '"+ libro.getTitulo() + "', 
                        autor = '" + libro.getAutor() + "', 
                        editorial = '" +  libro.getEditorial() + "', 
                        fecha = '" +  libro.getFecha() + "', 
                        categoria = '" +  libro.getCategoria() + "', 
                        novedad = '" + libro.getNovedad() + "'";
        try {
            Statement s = conexion.createStatement();
            s.execute(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
