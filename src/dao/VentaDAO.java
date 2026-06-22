package dao;

import java.sql.*;
import BD.Conexion;
import modelo.Venta;

public class VentaDAO {

    // Método para insertar la venta y retornar el ID generado por la BD
    public static int insertarVenta(Venta v) {
        int idGenerado = -1;
        try {
            // El procedimiento tiene 5 parámetros de entrada y 1 parámetro OUT (el sexto '?')
            CallableStatement cs = Conexion.getConexion().prepareCall("{call InsertarVenta(?,?,?,?,?,?)}");
            
            cs.setString(1, v.getFecha());
            cs.setString(2, v.getTipoComprobante());
            cs.setString(3, v.getMedioPago());
            cs.setString(4, v.getDocumentoCliente());
            cs.setDouble(5, v.getTotal());
            
            // Registrar el sexto parámetro como tipo Entero (OUT)
            cs.registerOutParameter(6, Types.INTEGER);
            
            cs.execute();
            
            // Obtener el ID asignado por MySQL
            idGenerado = cs.getInt(6);
            
        } catch (Exception e) {
            System.out.println("Error al insertar Venta en DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return idGenerado;
    }
}
