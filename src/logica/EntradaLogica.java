package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.EntradaInventario;

public class EntradaLogica {

    // Lista de entradas en memoria — por ahora en memoria, luego se conectará a base de datos
    private List<EntradaInventario> entradas;

    public EntradaLogica() {
        this.entradas = new ArrayList<>();
    }

    // Devuelve todas las entradas registradas
    public List<EntradaInventario> getEntradas() {
        return new ArrayList<>(entradas);
    }

    // ─────────────────────────────────────────
    // MÉTODOS SOBRECARGADOS
    // Mismo nombre "registrarEntrada", Java elige cuál usar según los parámetros
    // ─────────────────────────────────────────

    // Recibe un String (código de prenda) → filtra y retorna las entradas de esa prenda
    public List<EntradaInventario> registrarEntrada(String codigoPrenda) {
        List<EntradaInventario> filtradas = new ArrayList<>();
        for (EntradaInventario entrada : entradas) {
            if (entrada.getCodigoPrenda().equalsIgnoreCase(codigoPrenda)) {
                filtradas.add(entrada);
            }
        }
        return filtradas;
    }

    // Recibe todos los datos → registra una nueva entrada en la lista
    public void registrarEntrada(String codigoProveedor, String codigoPrenda, String talla,
                                int cantidad, String fecha) {
    	// Crea la entrada con los datos correctos y la agrega a la lista
    	EntradaInventario entrada = new EntradaInventario(codigoProveedor, codigoPrenda, talla, cantidad, fecha);

        
        entradas.add(entrada);
    }
}
