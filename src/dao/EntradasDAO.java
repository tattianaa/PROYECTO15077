package dao;

	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;
	import javax.swing.JOptionPane;
	import modelo.EntradaInventario;
	import BD.Conexion;

	public class EntradasDAO {

	    // Guarda una entrada nueva en MySQL
	    public static boolean insertar(EntradaInventario e) {
	        try {
	            CallableStatement cs = Conexion.getConexion().prepareCall("{call InsertarEntrada(?,?,?,?,?)}");
	            cs.setString(1, e.getCodigoProveedor());
	            cs.setString(2, e.getCodigoPrenda());
	            cs.setString(3, e.getTalla());
	            cs.setInt(4, e.getCantidad());
	            cs.setString(5, e.getFecha());
	            cs.execute();
	            return true;
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Error al insertar entrada: " + ex.getMessage());
	            return false;
	        }
	    }

	    // Trae todas las entradas desde MySQL
	    public static List<EntradaInventario> listar() {
	        List<EntradaInventario> lista = new ArrayList<>();
	        try {
	            CallableStatement cs = Conexion.getConexion().prepareCall("{call ListarEntradas()}");
	            ResultSet rs = cs.executeQuery();
	            while (rs.next()) {
	                lista.add(new EntradaInventario(
	                    rs.getString("codigo_proveedor"),
	                    rs.getString("codigo_prenda"),
	                    rs.getString("talla"),
	                    rs.getInt("cantidad"),
	                    rs.getString("fecha")
	                ));
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al listar entradas: " + e.getMessage());
	        }
	        return lista;
	    }


}
