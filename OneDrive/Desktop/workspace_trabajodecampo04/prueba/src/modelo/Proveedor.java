package modelo;

public class Proveedor {
	
	  private String codigo;
	  private String nombre;
	  private String ruc;
	  private String telefono;
	 
	  public Proveedor(String codigo, String nombre, String ruc, String telefono) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.ruc = ruc;
		this.telefono = telefono;
	  }

	  public String getCodigo() {
		  return codigo;
	  }

	  public void setCodigo(String codigo) {
		  this.codigo = codigo;
	  }

	  public String getNombre() {
		  return nombre;
	  }

	  public void setNombre(String nombre) {
		  this.nombre = nombre;
	  }

	  public String getRuc() {
		  return ruc;
	  }

	  public void setRuc(String ruc) {
		  this.ruc = ruc;
	  }

	  public String getTelefono() {
		  return telefono;
	  }

	  public void setTelefono(String telefono) {
		  this.telefono = telefono;
	  }
	  
	  @Override
	    public String toString() {
	        return "Proveedor{" +
	                "codigo='" + codigo + '\'' +
	                ", nombre='" + nombre + '\'' +
	                ", ruc='" + ruc + '\'' +
	                ", telefono='" + telefono + '\'' +
	                '}';
	    }
	  

}
