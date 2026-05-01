package gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import logica.InventarioLogica;
import logica.ProveedorLogica;

// Panel de registro de entradas de mercadería
// Recibe las instancias de lógica desde GestorAdministrativo para leer proveedores y prendas
public class GestorEntradas extends JPanel {

    // Referencias a la lógica para recargar combos cuando el panel se hace visible
    private InventarioLogica inventario;
    private ProveedorLogica proveedores;

    public GestorEntradas(InventarioLogica inventario, ProveedorLogica proveedores) {
        this.inventario  = inventario;
        this.proveedores = proveedores;
        setLayout(null);
        setBackground(new Color(245, 242, 225));

        // Título del panel
        JLabel lblTitulo = new JLabel("Registro de Entradas de Mercadería");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(20, 15, 400, 28);
        add(lblTitulo);

        // Panel blanco que contiene el formulario de registro
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        formPanel.setBounds(20, 55, 758, 180);
        add(formPanel);

        // Combo: proveedor — muestra los proveedores registrados en formato "CODIGO - Nombre"
        JLabel lProv = new JLabel("Proveedor:");
        lProv.setFont(new Font("Arial", Font.PLAIN, 12));
        lProv.setBounds(15, 15, 150, 20);
        formPanel.add(lProv);
        JComboBox<String> comboProveedor = new JComboBox<>();
        comboProveedor.addItem("-- Seleccionar --");
        comboProveedor.setBounds(15, 35, 160, 30);
        formPanel.add(comboProveedor);

        // Combo: prenda — muestra las prendas registradas en formato "CODIGO - Nombre"
        JLabel lPrenda = new JLabel("Prenda:");
        lPrenda.setFont(new Font("Arial", Font.PLAIN, 12));
        lPrenda.setBounds(190, 15, 130, 20);
        formPanel.add(lPrenda);
        JComboBox<String> comboPrenda = new JComboBox<>();
        comboPrenda.addItem("-- Seleccionar --");
        comboPrenda.setBounds(190, 35, 160, 30);
        formPanel.add(comboPrenda);

        // ComboBox: talla de la prenda
        JLabel lTalla = new JLabel("Talla:");
        lTalla.setFont(new Font("Arial", Font.PLAIN, 12));
        lTalla.setBounds(365, 15, 60, 20);
        formPanel.add(lTalla);
        String[] tallas = {"XS", "S", "M", "L", "XL", "26", "28", "30", "32", "34", "ÚNICA"};
        JComboBox<String> comboTalla = new JComboBox<>(tallas);
        comboTalla.setBounds(365, 35, 80, 30);
        formPanel.add(comboTalla);

        // Campo: color de la prenda
        JLabel lColor = new JLabel("Color:");
        lColor.setFont(new Font("Arial", Font.PLAIN, 12));
        lColor.setBounds(460, 15, 60, 20);
        formPanel.add(lColor);
        JTextField txtColor = new JTextField();
        txtColor.setBounds(460, 35, 90, 30);
        formPanel.add(txtColor);

        // Campo: cantidad de unidades que ingresaron
        JLabel lCantidad = new JLabel("Cantidad:");
        lCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        lCantidad.setBounds(565, 15, 80, 20);
        formPanel.add(lCantidad);
        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(565, 35, 70, 30);
        formPanel.add(txtCantidad);

        // Fecha: se autogenera con la fecha de hoy — no editable
        JLabel lFecha = new JLabel("Fecha:");
        lFecha.setFont(new Font("Arial", Font.PLAIN, 12));
        lFecha.setBounds(650, 15, 60, 20);
        formPanel.add(lFecha);
        String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        JTextField txtFecha = new JTextField(fechaHoy);
        txtFecha.setEditable(false);
        txtFecha.setBackground(new Color(240, 240, 240));
        txtFecha.setBounds(650, 35, 95, 30);
        formPanel.add(txtFecha);

        // Botón para registrar la entrada
        JButton btnRegistrar = new JButton("REGISTRAR ENTRADA");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegistrar.setBackground(new Color(130, 190, 140));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setOpaque(true);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setBounds(15, 110, 200, 36);
        formPanel.add(btnRegistrar);

        // Botón para limpiar los campos editables del formulario
        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(180, 180, 185));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setBounds(230, 110, 120, 36);
        formPanel.add(btnLimpiar);

        // Título del historial
        JLabel lblHistorial = new JLabel("Historial de Entradas");
        lblHistorial.setFont(new Font("Arial", Font.BOLD, 13));
        lblHistorial.setBounds(20, 248, 250, 22);
        add(lblHistorial);

        // Tabla que muestra todas las entradas registradas
        String[] columnas = {"Proveedor", "Prenda", "Talla", "Color", "Cantidad", "Fecha"};
        DefaultTableModel modeloEntradas = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable tablaEntradas = new JTable(modeloEntradas);
        tablaEntradas.setRowHeight(28);
        tablaEntradas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEntradas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEntradas.getTableHeader().setBackground(new Color(240, 235, 225));
        tablaEntradas.setBackground(Color.WHITE);
        tablaEntradas.setGridColor(new Color(220, 220, 220));
        tablaEntradas.setSelectionBackground(new Color(220, 190, 195));

        JScrollPane scrollEntradas = new JScrollPane(tablaEntradas);
        scrollEntradas.setBounds(20, 275, 758, 340);
        scrollEntradas.setBorder(new LineBorder(new Color(220, 220, 220)));
        add(scrollEntradas);

        // Recargar combos cada vez que el panel se hace visible
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                // Recargar proveedores registrados
                comboProveedor.removeAllItems();
                comboProveedor.addItem("-- Seleccionar --");
                for (String s : proveedores.getProveedoresFormato()) comboProveedor.addItem(s);
                // Recargar prendas registradas
                comboPrenda.removeAllItems();
                comboPrenda.addItem("-- Seleccionar --");
                for (String s : inventario.getPrendasFormato()) comboPrenda.addItem(s);
            }
        });

        // Limpiar campos editables — los combos vuelven a "-- Seleccionar --"
        btnLimpiar.addActionListener(e -> {
            comboProveedor.setSelectedIndex(0);
            comboPrenda.setSelectedIndex(0);
            comboTalla.setSelectedIndex(0);
            txtColor.setText("");
            txtCantidad.setText("");
        });

        // Validar y registrar la entrada al presionar el botón
        btnRegistrar.addActionListener(e -> {
            // Leer proveedor seleccionado — extraer solo el código
            String proveedorSeleccionado = (String) comboProveedor.getSelectedItem();
            if (proveedorSeleccionado.equals("-- Seleccionar --")) {
                JOptionPane.showMessageDialog(this, "Selecciona un proveedor.");
                return;
            }
            String codigoProveedor = proveedorSeleccionado.split(" - ")[0];

            // Leer prenda seleccionada — extraer solo el código
            String prendaSeleccionada = (String) comboPrenda.getSelectedItem();
            if (prendaSeleccionada.equals("-- Seleccionar --")) {
                JOptionPane.showMessageDialog(this, "Selecciona una prenda.");
                return;
            }
            String codigoPrenda = prendaSeleccionada.split(" - ")[0];

            String talla    = (String) comboTalla.getSelectedItem();
            String color    = txtColor.getText().trim();
            String cantidad = txtCantidad.getText().trim();
            String fecha    = txtFecha.getText().trim();

            // Verificar que color y cantidad no estén vacíos
            if (color.isEmpty() || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Color y cantidad son obligatorios.");
                return;
            }

            // Verificar que la cantidad sea un número entero mayor a 0
            int cant;
            try {
                cant = Integer.parseInt(cantidad);
                if (cant <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero mayor a 0.");
                return;
            }

            // TODO: conectar con EntradaLogica y actualizar stock cuando se implemente
            // Por ahora solo agrega la fila a la tabla visual
            modeloEntradas.addRow(new Object[]{codigoProveedor, codigoPrenda, talla, color, cant, fecha});
            JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");

            // Limpiar el formulario después de registrar
            btnLimpiar.doClick();
        });
    }
}
