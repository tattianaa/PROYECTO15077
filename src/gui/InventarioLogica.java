package gui;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class InventarioLogica {
	// es el método que hará el trabajo de borrar en los dos lados
    public void eliminarPrenda(int fila, DefaultTableModel modelo, JPanel grid) {
        if (fila >= 0) {
            // 1. Borra de la tabla (Panel Gestión)
            if (fila < modelo.getRowCount()) {
                modelo.removeRow(fila);
            }      
            // 2. Borra la tarjeta (Panel Inicio)
            if (fila < grid.getComponentCount()) {
                grid.remove(fila);
                grid.revalidate();
                grid.repaint();
            } 
            JOptionPane.showMessageDialog(null, "Se eliminó de la tabla y del catálogo.");
        }
    }
    
   
    //validar datos
    public String validarTodo(String nombre, String codigo, String precio, String stock, DefaultTableModel modelo) {
        if (!nombre.matches("[a-zA-Z ]+")) {
            return "El nombre solo debe tener letras.";
        }

        if (!codigo.matches("[A-Z]{3}[0-9]{3}")) {
            return "El código debe ser tipo ABC123.";
        }
        // Verificar duplicado
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String cod = (String) modelo.getValueAt(i, 0);
            if (cod.equalsIgnoreCase(codigo)) {
                return "Ese código ya existe.";
            }
        }
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            return "Precio inválido.";
        }
        if (!stock.matches("\\d+")) {
            return "Stock inválido.";
        }
        return "OK";
    }
    public void editarPrenda(int fila, DefaultTableModel modelo, String codigo, String nombre, String stock, String precio, String categoria) {

        // Validar código duplicado
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (i != fila) {
                String cod = (String) modelo.getValueAt(i, 0);
                if (cod.equalsIgnoreCase(codigo)) {
                    JOptionPane.showMessageDialog(null, "Ese código ya existe.");
                    return;
                }
            }
        }

        // Actualizar datos
        modelo.setValueAt(codigo, fila, 0);
        modelo.setValueAt(nombre, fila, 2);
        modelo.setValueAt(stock, fila, 3);
        modelo.setValueAt("S/. " + precio, fila, 4);
        modelo.setValueAt(categoria, fila, 5);

        JOptionPane.showMessageDialog(null, "Prenda actualizada correctamente.");
    }
    
    

}
