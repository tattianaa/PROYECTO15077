package modelo;

/**
 * Representa una combinación específica de talla + color de una prenda.
 * Por ejemplo: talla "M", color "Negro", stock 3.
 *
 * Concepto POO aplicado: COMPOSICIÓN
 * Prenda "tiene" una lista de Variantes (relación tiene-un)
 */
public class Variante {

    private String talla;   // Ej: "S", "M", "L" o "28", "30", "32"
    private String color;   // Ej: "Negro", "Blanco", "Rojo"
    private int stock;      // Cuántas unidades hay de esta combinación exacta

    // Constructor: crea una variante con talla, color y stock inicial
    public Variante(String talla, String color, int stock) {
        this.talla = talla;
        this.color = color;
        this.stock = stock;
    }

    // ── Getters y Setters ──

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return talla + " / " + color + " → " + stock + " uds";
    }
}
