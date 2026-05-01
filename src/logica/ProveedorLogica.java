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
    public int gestionar(String codigo) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    // Recibe todos los datos → agrega un proveedor nuevo a la lista
    public void gestionar(String codigo, String nombre, String ruc, String telefono) {
        Proveedor p = new Proveedor(codigo, nombre, ruc, telefono);
        proveedores.add(p);
    }

    // ─────────────────────────────────────────
    // OTROS MÉTODOS
    // ─────────────────────────────────────────

    // Valida los datos del formulario antes de guardar
    public String validar(String codigo, String nombre, String ruc, String telefono) {

        // Código: formato ABC123 (3 letras + 3 números)
        if (!codigo.matches("[A-Z]{3}[0-9]{3}")) {
            return "El código debe tener el formato ABC123 (3 letras y 3 números).";
        }

        // Código duplicado
        if (gestionar(codigo) != -1) {
            return "Ese código de proveedor ya existe.";
        }

        // Nombre: solo letras y espacios
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return "El nombre solo debe contener letras.";
        }

        // RUC: exactamente 11 dígitos
        if (!ruc.matches("\\d{11}")) {
            return "El RUC debe tener exactamente 11 dígitos numéricos.";
        }

        // Teléfono: exactamente 9 dígitos
        if (!telefono.matches("\\d{9}")) {
            return "El teléfono debe tener exactamente 9 dígitos numéricos.";
        }

        return "OK";
    }

    // Devuelve lista de proveedores en formato "CODIGO - Nombre" para mostrar en combo
    public List<String> getProveedoresFormato() {
        List<String> lista = new ArrayList<>();
        for (Proveedor p : proveedores) {
            lista.add(p.getCodigo() + " - " + p.getNombre());
        }
        return lista;
    }
}
