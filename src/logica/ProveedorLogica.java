package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorLogica {

    // Lista de proveedores en memoria
    private List<Proveedor> proveedores;

    // Constructor
    public ProveedorLogica() {
        proveedores = new ArrayList<>();
    }

    // Obtener copia de la lista
    public List<Proveedor> getProveedores() {
        return new ArrayList<>(proveedores);
    }

    // ─────────────────────────────────────────
    // MÉTODOS SOBRECARGADOS
    // Mismo nombre "gestionar", Java elige cuál usar según los parámetros
    // ─────────────────────────────────────────

    // Recibe un String (código) → busca el proveedor y retorna su posición, o -1 si no existe
    public int Gestionproveedor(String codigo) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
 // Recibe un int (posición) → elimina el proveedor en esa posición de la lista
    public boolean Gestionproveedor(int fila) {
        if (fila >= 0 && fila < proveedores.size()) {
            proveedores.remove(fila);
            return true;
        }
        return false;
    }


    // Recibe todos los datos → agrega un proveedor nuevo a la lista
    public void Gestionproveedor(String codigo, String nombre, String ruc, String telefono) {
        Proveedor p = new Proveedor(codigo, nombre, ruc, telefono);
        proveedores.add(p);
    }

    // ─────────────────────────────────────────
    // OTROS MÉTODOS
    // ─────────────────────────────────────────

    

    // Devuelve lista de proveedores en formato "CODIGO - Nombre" para mostrar en combo
    public List<String> getProveedoresFormato() {
        List<String> lista = new ArrayList<>();
        for (Proveedor p : proveedores) {
            lista.add(p.getCodigo() + " - " + p.getNombre());
        }
        return lista;
    }

    // Limpia la lista en memoria
    public void limpiar() {
        proveedores.clear();
    }
}
