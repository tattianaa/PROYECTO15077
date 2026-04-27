package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Prenda;
import modelo.Variante;

public class InventarioLogica {

    // Lista interna de prendas — por ahora en memoria, luego se conectará a base de datos
    private List<Prenda> listaPrendas = new ArrayList<>();

    // Devuelve una copia de la lista de prendas
    public List<Prenda> getPrendas() {
        return new ArrayList<>(listaPrendas);
    }

   
    // MÉTODOS SOBRECARGADOS
    // Mismo nombre "gestionar", Java elige cuál ejecutar según los parámetros recibidos
    
 // Recibe todos los datos → agrega una prenda nueva con sus variantes a la lista
    public void gestionar(String codigo, String nombre, double precio,
                          String categoria, List<Variante> variantes) {
        Prenda p = new Prenda(codigo, nombre, precio, categoria);
        for (Variante v : variantes) {
            p.agregarVariante(v.getTalla(), v.getColor(), v.getStock());
        }
        listaPrendas.add(p);
    }

    // Recibe un String (código) → busca la prenda y retorna su posición en la lista, o -1 si no existe
    
    public int gestionar(String codigo) {
        for (int i = 0; i < listaPrendas.size(); i++) {
            if (listaPrendas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1;
    }

    // Recibe un int (posición) → elimina la prenda en esa posición de la lista
    public boolean gestionar(int fila) {
        if (fila >= 0 && fila < listaPrendas.size()) {
            listaPrendas.remove(fila);
            return true;
        }
        return false;
    }

    // Recibe texto y categoría → filtra prendas por nombre/código y categoría
    // Si texto está vacío, no filtra por texto
    // Si categoria es "TODAS", no filtra por categoría
    public List<Prenda> gestionar(String texto, String categoria) {
        List<Prenda> filtradas = new ArrayList<>();
        for (Prenda p : listaPrendas) {
            // Verifica si el nombre o código contiene el texto buscado
            boolean coincideTexto = texto.isEmpty()
                || p.getCodigo().toLowerCase().contains(texto.toLowerCase())
                || p.getNombre().toLowerCase().contains(texto.toLowerCase());
            // Verifica si la categoría coincide (o si es TODAS)
            boolean coincideCategoria = categoria.equals("TODAS")
                || p.getCategoria().equalsIgnoreCase(categoria);
            // Solo agrega si cumple ambos filtros
            if (coincideTexto && coincideCategoria) {
                filtradas.add(p);
            }
        }
        return filtradas;
    }

    
    // ─────────────────────────────────────────
    // OTROS MÉTODOS
    // ─────────────────────────────────────────

    // Verifica si el código ya existe en otra posición distinta a la que se está editando
    public boolean codigoExisteEnOtraFila(String codigo, int filaActual) {
        int pos = gestionar(codigo);
        return pos != -1 && pos != filaActual;
    }

    // Valida los datos al editar una prenda
    public String validarEdicion(String nombre, String precio) {
        if (nombre.isEmpty()) {
            return "El nombre no puede estar vacío.";
        }
        if (!nombre.matches("[a-zA-Z ]+")) {
            return "El nombre solo debe tener letras.";
        }
        if (precio.isEmpty()) {
            return "El precio no puede estar vacío.";
        }
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            return "Precio inválido. Solo se permiten números.";
        }
        return "OK";
    }

    // Valida los datos antes de agregar una prenda nueva
    public String validarTodo(String nombre, String codigo, String precio) {
        if (!nombre.matches("[a-zA-Z ]+")) {
            return "El nombre solo debe tener letras.";
        }
        if (!codigo.matches("[A-Z]{3}[0-9]{3}")) {
            return "El código debe ser tipo ABC123.";
        }
        if (gestionar(codigo) != -1) {
            return "Ese código ya existe.";
        }
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            return "Precio inválido.";
        }
        return "OK";
    }

    // Valida la lista de variantes antes de guardar
    public String validarVariantes(List<Variante> variantes) {
        if (variantes.isEmpty()) {
            return "Ingresa al menos una variante con stock mayor a 0.";
        }
        for (Variante v : variantes) {
            String color = v.getColor().trim();
            if (color.isEmpty()) {
                return "El color de la talla " + v.getTalla() + " no puede estar vacío.";
            }
            if (!color.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return "El color '" + color + "' solo debe contener letras.";
            }
            if (v.getStock() == -1) {
                return "El stock de la talla " + v.getTalla() + " debe ser un número entero.";
            }
            if (v.getStock() <= 0) {
                return "El stock de " + v.getTalla() + " / " + color + " debe ser mayor a 0.";
            }
        }
        return "OK";
    }

    // Edita los datos de una prenda existente en la posición indicada
    public boolean editarPrenda(int fila, String codigo, String nombre,
                                String precio, String categoria,
                                List<Variante> variantes) {
        if (codigoExisteEnOtraFila(codigo, fila)) return false;
        Prenda p = listaPrendas.get(fila);
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setPrecio(Double.parseDouble(precio));
        p.setCategoria(categoria);
        p.getVariantes().clear();
        for (Variante v : variantes) {
            p.agregarVariante(v.getTalla(), v.getColor(), v.getStock());
        }
        return true;
    }
 // Devuelve lista de prendas en formato "CODIGO - Nombre" para mostrar en combo
    public List<String> getPrendasFormato() {
        List<String> lista = new ArrayList<>();
        for (Prenda p : listaPrendas) {
            lista.add(p.getCodigo() + " - " + p.getNombre());
        }
        return lista;
    }

}
