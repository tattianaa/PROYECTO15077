package dao;

import java.sql.*;
import BD.Conexion;
import modelo.Cliente;

public class ClienteDAO {

    // =========================================================================
    // 🔍 Método para BUSCAR un cliente por DNI o RUC en MySQL
    // =========================================================================
    public static Cliente buscarPorDocumento(String documento) {
        Cliente c = null;
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call BuscarCliente(?)}");
            cs.setString(1, documento);
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                // Sincronizado con tu Cliente.java (tipoDocumento primero)
                c = new Cliente(
                    rs.getString("tipo_documento"), // 1. tipoDocumento
                    rs.getString("documento"),      // 2. documento
                    rs.getString("nombre"),         
                    rs.getString("telefono"),       
                    rs.getString("direccion"),       
                    rs.getString("correo")           
                );
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente en DAO: " + e.getMessage());
        }
        return c;
    }
    
    // =========================================================================
    // 💾 Método para REGISTRAR un cliente nuevo en MySQL
    // =========================================================================
    public static boolean insertar(Cliente c) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call InsertarCliente(?,?,?,?,?,?)}");
            
            // CORRECCIÓN CRUCIAL: Alineado con el PROCEDURE de tu MySQL
            cs.setString(1, c.getTipoDocumento()); // 1. p_tipo (Recibe "BOLETA ELECTRÓNICA" -> entra en VARCHAR(25))
            cs.setString(2, c.getDocumento());     // 2. p_documento (Recibe "60455382" -> entra en VARCHAR(11))
            cs.setString(3, c.getNombre());        // 3. p_nombre
            cs.setString(4, c.getTelefono());      // 4. p_telefono
            cs.setString(5, c.getDireccion());     // 5. p_direccion
            cs.setString(6, c.getCorreo());        // 6. p_correo
            
            cs.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar cliente en DAO: " + e.getMessage());
            return false;
        }
    }
}