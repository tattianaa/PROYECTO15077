package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorLogica {

    // Lista privada
    private List<Proveedor> proveedores;

    // Constructor
    public ProveedorLogica() {
        proveedores = new ArrayList<>();
    }

    // Agregar proveedor
    public void agregarProveedor(String codigo, String nombre, String ruc, String telefono) {
        Proveedor p = new Proveedor(codigo, nombre, ruc, telefono);
        proveedores.add(p);
    }

    // Obtener copia de la lista
    public List<Proveedor> getProveedores() {
        return new ArrayList<>(proveedores);
    }

    // Buscar proveedor por código
    public int buscarProveedor(String codigo) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    // Validar datos
    public String validar(String nombre, String ruc) {

        // Nombre: solo letras y espacios
        if (!nombre.matches("[a-zA-Z ]+")) {
            return "El nombre solo debe contener letras";
        }

        // RUC: exactamente 11 dígitos
        if (!ruc.matches("\\d{11}")) {
            return "El RUC debe tener exactamente 11 dígitos numéricos";
        }

        return "OK";
    }
}
