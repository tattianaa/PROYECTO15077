package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Proveedor;
import BD.Conexion;
public class ProveedorDAO {

    // Guarda un proveedor nuevo en MySQL
    public static boolean insertar(Proveedor p) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call InsertarProveedor(?,?,?,?)}");
            cs.setString(1, p.getCodigo());
            cs.setString(2, p.getNombre());
            cs.setString(3, p.getRuc());
            cs.setString(4, p.getTelefono());
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Trae todos los proveedores desde MySQL
    public static List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call ListarProveedores()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Proveedor(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("ruc"),
                    rs.getString("telefono")
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    // Elimina un proveedor desde MySQL
    public static boolean eliminar(String codigo) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call EliminarProveedor(?)}");
            cs.setString(1, codigo);
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Actualiza los datos de un proveedor en MySQL
    public static boolean editar(Proveedor p) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call EditarProveedor(?,?,?,?)}");
            cs.setString(1, p.getCodigo());
            cs.setString(2, p.getNombre());
            cs.setString(3, p.getRuc());
            cs.setString(4, p.getTelefono());
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar proveedor: " + e.getMessage());
            return false;
        }
    }


}

