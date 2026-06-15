package modelo;

import java.util.ArrayList;
import java.util.List;


public class Prenda {

    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;
    private int id;
    private String imagen;
    private int stockTotal = 0;

    /**
     * Lista de variantes: cada variante es una combinación talla+color con su stock.
     * Reemplaza al antiguo campo "int stock".
     */
    private List<Variante> variantes;

    // Constructor principal — inicia con lista de variantes vacía
    
    public Prenda(String codigo, String nombre, double precio, String categoria, int id, String imagen, int stockTotal,
			List<Variante> variantes) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.id = id;
		this.imagen = imagen;
		this.stockTotal = stockTotal;
		this.variantes = variantes;

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


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	


	public int getStockTotal() {
		return stockTotal;
	}



	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}



	public void setVariantes(List<Variante> variantes) {
		this.variantes = variantes;
	}


	/** Devuelve la lista completa de variantes */
    public List<Variante> getVariantes() {
        return variantes;
    }

    /** Agrega una nueva variante (talla  stock) a la prenda */
    public void agregarVariante(String talla, int stock) {
        variantes.add(new Variante(talla, stock));
    }

    public int stockPorVariante() {  // Método que devuelve un número entero
        int totalVariante = 0;  // Crea una variable llamada "total" que empieza en 0
        for (Variante v : variantes) {  // Recorre CADA variante (cada talla) de la lista
            totalVariante += v.getStock();  // Suma el stock de esa talla al total
        }
        return totalVariante;  // Devuelve el número total
    
    }
    public void sumarStock(int cantidad) {
    	 stockTotal += cantidad;
    }
}
