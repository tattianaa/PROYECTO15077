package BD;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
	
	  // Datos de conexión a MySQL
    private static final String URL      = "jdbc:mysql://localhost:3306/speakers_moda";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "tatiana1607"; // ← pon tu contraseña de MySQL aquí

    // Devuelve una conexión activa a la base de datos
    public static Connection getConexion() {
        try {
            // Carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retorna la conexión
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

}
