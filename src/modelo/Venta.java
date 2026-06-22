package modelo;

public class Venta {
	private int id;
    private String fecha;
    private String tipoComprobante;
    private String medioPago;
    private String documentoCliente;
    private double total;
	public Venta(int id, String fecha, String tipoComprobante, String medioPago, String documentoCliente,
			double total) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoComprobante = tipoComprobante;
		this.medioPago = medioPago;
		this.documentoCliente = documentoCliente;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
	public String getDocumentoCliente() {
		return documentoCliente;
	}
	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente = documentoCliente;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
    
    
}
