package modelo;

public class Cliente {
	private String documento;
	private String nombre;
	private String telefono;
	private String direccion;
	private String tipoDocumento; //ruc o dni
	//const
	public Cliente(String documento, String nombre, String telefono, String direccion, String tipoDocumento) {
		
		this.documento = documento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	@Override
	public String toString() {
		return nombre + " - " + documento;
	}
	
	

}
