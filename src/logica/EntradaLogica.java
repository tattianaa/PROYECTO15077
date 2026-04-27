package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.EntradaInventario;

public class EntradaLogica {

    private List<EntradaInventario> entradas;

    public EntradaLogica() {
        this.entradas = new ArrayList<>();
    }

    // Registra una entrada nueva
    public void registrarEntrada(String codigoProveedor, String codigoPrenda, String talla,
                                 String color, int cantidad, String fecha) {
        EntradaInventario entrada = new EntradaInventario(
            codigoProveedor, codigoPrenda, talla, color, cantidad, fecha
        );
        entradas.add(entrada);
    }

    public List<EntradaInventario> getEntradas() {
        return new ArrayList<>(entradas);
    }

    // Filtra entradas por código de prenda
    public List<EntradaInventario> registrarEntrada(String codigoPrenda) {
        List<EntradaInventario> filtradas = new ArrayList<>();
        for (EntradaInventario entrada : entradas) {
            if (entrada.getCodigoPrenda().equalsIgnoreCase(codigoPrenda)) {
                filtradas.add(entrada);
            }
        }
        return filtradas;
    }
}