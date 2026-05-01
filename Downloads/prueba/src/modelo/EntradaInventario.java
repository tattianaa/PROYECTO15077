package modelo;

public class EntradaInventario {

    private String codigoProveedor;
    private String codigoPrenda;
    private String talla;
    private String color;
    private int cantidad;
    private String fecha; // Formato esperado: dd/MM/yyyy

    public EntradaInventario(String codigoProveedor, String codigoPrenda, String talla,
                             String color, int cantidad, String fecha) {
        this.codigoProveedor = codigoProveedor;
        this.codigoPrenda = codigoPrenda;
        this.talla = talla;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }
}

