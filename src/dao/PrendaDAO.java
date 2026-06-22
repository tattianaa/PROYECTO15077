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

            // ── ¡MODIFICADO AQUÍ! ──
            // Se eliminó el bucle for de variantes en la inserción inicial de la prenda
            // para evitar el choque con la lógica automática de Entradas en MySQL.

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // Trae todas las prendas con su stock acumulado directamente desde MySQL
    public static List<Prenda> listar() {
        List<Prenda> lista = new ArrayList<>();
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call ListarPrendas()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Prenda p = new Prenda(
                    rs.getString("codigo"),      // 1. codigo
                    rs.getString("nombre"),      // 2. nombre
                    rs.getDouble("precio"),      // 3. precio
                    rs.getString("categoria"),   // 4. categoria
                    0,                           // 5. id
                    rs.getString("imagen"),      // 6. imagen
                    rs.getInt("stock_total"),    // 7. stockTotal (Lee el SUM de tu base de datos)
                    new ArrayList<>()            // 8. variantes vacías
                );
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
 // ── AGREGADO: Trae el desglose de tallas y stocks de una prenda específica desde MySQL
    public static List<Variante> ListarVariantes(String codigoPrenda) {
        List<Variante> lista = new ArrayList<>();
        try {
            CallableStatement cs = Conexion.getConexion().prepareCall("{call ListarVariantes(?)}");
            cs.setString(1, codigoPrenda);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                // Se leen las columnas de la tabla variantes de la BD
                Variante v = new Variante(
                    rs.getString("talla"),
                    rs.getInt("stock")
                );
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println("Error al listar variantes en DAO: " + e.getMessage());
        }
        return lista;
    }
}