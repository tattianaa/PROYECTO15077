package modelo;

public class EntradaInventario {

    private String codigoProveedor;
    private String codigoPrenda;
    private String talla;
    private int cantidad;
    private String fecha; // Formato esperado: dd/MM/yyyy

    public EntradaInventario(String codigoProveedor, String codigoPrenda, String talla,
                              int cantidad, String fecha) {
        this.codigoProveedor = codigoProveedor;
        this.codigoPrenda = codigoPrenda;
        this.talla = talla;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public String getCodigoPrenda() {
        return codigoPrenda;
    }

    public String getTalla() {
        return talla;
    
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }
}

