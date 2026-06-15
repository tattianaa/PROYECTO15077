package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Prenda;
import modelo.Variante;
import BD.Conexion;
public class PrendaDAO {

    // Guarda una prenda nueva en MySQL
    public static boolean insertar(Prenda p) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call InsertarPrenda(?,?,?,?,?)}");
            cs.setString(1, p.getCodigo());
            cs.setString(2, p.getNombre());
            cs.setDouble(3, p.getPrecio());
            cs.setString(4, p.getCategoria());
            cs.setString(5, p.getImagen());
            cs.execute();

            // Guarda cada talla de la prenda
            for (Variante v : p.getVariantes()) {
                CallableStatement csV = Conexion.getConexion().prepareCall("{call ActualizarStock(?,?,?)}");
                csV.setString(1, p.getCodigo());
                csV.setString(2, v.getTalla());
                csV.setInt(3, v.getStock());
                csV.execute();
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // Trae todas las prendas con sus tallas desde MySQL
    public static List<Prenda> listar() {
        List<Prenda> lista = new ArrayList<>();
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call ListarPrendas()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Prenda p = new Prenda(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getString("categoria"),
                    0,
                    rs.getString("imagen"),
                    0,
                    new ArrayList<>()
                );
                // Trae las tallas de esa prenda
                CallableStatement csV = Conexion.getConexion().prepareCall("{call ListarVariantes(?)}");
                csV.setString(1, p.getCodigo());
                ResultSet rsV = csV.executeQuery();
                while (rsV.next()) {
                    p.agregarVariante(rsV.getString("talla"), rsV.getInt("stock"));
                }
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // Elimina la prenda y sus tallas desde MySQL
    public static boolean eliminar(String codigo) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call EliminarPrenda(?)}");
            cs.setString(1, codigo);
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // Actualiza nombre, precio, categoria e imagen en MySQL
    public static boolean editar(Prenda p) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call EditarPrenda(?,?,?,?,?)}");
            cs.setString(1, p.getCodigo());
            cs.setString(2, p.getNombre());
            cs.setDouble(3, p.getPrecio());
            cs.setString(4, p.getCategoria());
            cs.setString(5, p.getImagen());
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar: " + e.getMessage());
            return false;
        }
    }

    // Suma stock a una talla — si la talla ya tiene stock la suma, si no la registra por primera vez
    public static boolean actualizarStock(String codigoPrenda, String talla, int cantidad) {
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call ActualizarStock(?,?,?)}");
            cs.setString(1, codigoPrenda);
            cs.setString(2, talla);
            cs.setInt(3, cantidad);
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }
}
