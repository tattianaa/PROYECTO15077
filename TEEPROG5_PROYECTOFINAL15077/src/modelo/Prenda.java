package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos de una prenda.
 *
 * Concepto POO aplicado: ENCAPSULAMIENTO + COMPOSICIÓN
 * - Los atributos son privados y se acceden por getters/setters
 * - Prenda "tiene" una lista de Variantes (composición)
 */
public class Prenda {

    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;

    /**
     * Lista de variantes: cada variante es una combinación talla+color con su stock.
     * Reemplaza al antiguo campo "int stock".
     */
    private List<Variante> variantes;

    // Constructor principal — inicia con lista de variantes vacía
    public Prenda(String codigo, String nombre, double precio, String categoria) {
        this.codigo     = codigo;
        this.nombre     = nombre;
        this.precio     = precio;
        this.categoria  = categoria;
        this.variantes  = new ArrayList<>();
    }

    // ── Getters y Setters básicos ──

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // ── Manejo de variantes ──

    /** Devuelve la lista completa de variantes */
    public List<Variante> getVariantes() {
        return variantes;
    }

    /** Agrega una nueva variante (talla + color + stock) a la prenda */
    public void agregarVariante(String talla, String color, int stock) {
        variantes.add(new Variante(talla, color, stock));
    }

    /**
     * Calcula el stock total sumando todas las variantes.
     * Se mantiene este método para que la GUI siga funcionando igual:
     * donde antes mostraba "stock", ahora muestra la suma de todas las variantes.
     */
    public int getStock() {
        int total = 0;
        for (Variante v : variantes) {
            total += v.getStock();
        }
        return total;
    }
}
