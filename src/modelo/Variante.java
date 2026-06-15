package modelo;

/**
 * Representa una combinación específica de talla + color de una prenda.
 * Por ejemplo: talla "M", ", stock 3.
 *
 * Concepto POO aplicado: COMPOSICIÓN
 * Prenda "tiene" una lista de Variantes (relación tiene-un)
 */
public class Variante {

    private String talla;   // Ej: "S", "M", "L" o "28", "30", "32"
    private int stock;      // Cuántas unidades hay de esta combinación exacta

    // Constructor: crea una variante con talla, color y stock inicial
    public Variante(String talla, int stock) {
        this.talla = talla;
        
        this.stock = stock;
    }

    // ── Getters y Setters ──

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Representación legible: útil para debug y para mostrar en pantalla
    @Override
    public String toString() {
        return talla  + " → " + stock + " uds";
    }
}
