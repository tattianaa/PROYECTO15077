package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Prenda;
import modelo.Variante;

public class InventarioLogica {

    //  lista interna de prendas-temporal
    private List<Prenda> listaPrendas = new ArrayList<>();
    // Devuelve una copia de la lista 
    public List<Prenda> getPrendas() {
        return new ArrayList<>(listaPrendas);
    }

    //Busca una prenda por código
    public int buscarPrenda(String codigo) {
        for (int i = 0; i < listaPrendas.size(); i++) {
            if (listaPrendas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1;
    }
    // Verifica si el código ya existe en otra posición distinta a la que se está editando 
    public boolean codigoExisteEnOtraFila(String codigo, int filaActual) {
        int pos = buscarPrenda(codigo);
        return pos != -1 && pos != filaActual;
    }
   
    
     //Valida los datos al editar una prenda.
 
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

    
     //Valida los datos antes de agregar una prenda nueva.
    
     
    public String validarTodo(String nombre, String codigo, String precio) {
        if (!nombre.matches("[a-zA-Z ]+")) {
            return "El nombre solo debe tener letras.";
        }
        if (!codigo.matches("[A-Z]{3}[0-9]{3}")) {
            return "El código debe ser tipo ABC123.";
        }
        if (buscarPrenda(codigo) != -1) {
            return "Ese código ya existe.";
        }
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            return "Precio inválido.";
        }
        return "OK";
    }

    
     //Valida la lista de variantes antes de guardar.
     
    public String validarVariantes(List<Variante> variantes) {
        if (variantes.isEmpty()) {
            return "Ingresa al menos una variante con stock mayor a 0.";
        }
        for (Variante v : variantes) {
            String color = v.getColor().trim();
            // Color no puede estar vacío
            if (color.isEmpty()) {
                return "El color de la talla " + v.getTalla() + " no puede estar vacío.";
            }
            // Color solo acepta letras y espacios
            if (!color.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return "El color '" + color + "' solo debe contener letras.";
            }
            // Stock debe ser número entero — si es -1 significa que el usuario escribió letras
            if (v.getStock() == -1) {
                return "El stock de la talla " + v.getTalla() + " debe ser un número entero.";
            }
            // Stock debe ser mayor a 0
            if (v.getStock() <= 0) {
                return "El stock de " + v.getTalla() + " / " + color + " debe ser mayor a 0.";
            }
        }
        return "OK";
    }
    public void agregarPrenda(String codigo, String nombre, double precio,
                              String categoria, List<Variante> variantes) {
        Prenda p = new Prenda(codigo, nombre, precio, categoria);
        for (Variante v : variantes) {
            p.agregarVariante(v.getTalla(), v.getColor(), v.getStock());
        }
        listaPrendas.add(p);
    }

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

    
     // Elimina la prenda en la posición indicada.
     
    public boolean eliminarPrenda(int fila) {
        if (fila >= 0 && fila < listaPrendas.size()) {
            listaPrendas.remove(fila);
            return true;
        }
        return false;
    }
}
