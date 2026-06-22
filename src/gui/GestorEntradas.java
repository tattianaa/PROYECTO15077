package gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import logica.InventarioLogica;
import logica.ProveedorLogica;

public class GestorEntradas extends JPanel {

    private static final long serialVersionUID = 1L;
    private ProveedorLogica proveedores;
    private logica.EntradaLogica entradas;
    private DefaultTableModel modeloEntradas;

    public GestorEntradas(InventarioLogica inventario, ProveedorLogica proveedores, logica.EntradaLogica entradas) {
       
        this.proveedores = proveedores;
        this.entradas = entradas;
        
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

        // Combo: proveedor
        JLabel lProv = new JLabel("Proveedor:");
        lProv.setFont(new Font("Arial", Font.PLAIN, 12));
        lProv.setBounds(15, 15, 150, 20);
        formPanel.add(lProv);
        JComboBox<String> comboProveedor = new JComboBox<>();
        comboProveedor.addItem("-- Seleccionar --");
        comboProveedor.setBounds(15, 35, 160, 30);
        formPanel.add(comboProveedor);

        // Combo: prenda
        JLabel lPrenda = new JLabel("Prenda:");
        lPrenda.setFont(new Font("Arial", Font.PLAIN, 12));
        lPrenda.setBounds(190, 15, 130, 20);
        formPanel.add(lPrenda);
        JComboBox<String> comboPrenda = new JComboBox<>();
        comboPrenda.addItem("-- Seleccionar --");
        comboPrenda.setBounds(190, 35, 160, 30);
        formPanel.add(comboPrenda);

        // ComboBox: talla
        JLabel lTalla = new JLabel("Talla:");
        lTalla.setFont(new Font("Arial", Font.PLAIN, 12));
        lTalla.setBounds(365, 15, 60, 20);
        formPanel.add(lTalla);
        String[] tallas = {"XS", "S", "M", "L", "XL", "26", "28", "30", "32", "34", "ÚNICA"};
        JComboBox<String> comboTalla = new JComboBox<>(tallas);
        comboTalla.setBounds(365, 35, 80, 30);
        formPanel.add(comboTalla);

        // Campo: cantidad
        JLabel lCantidad = new JLabel("Cantidad:");
        lCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        lCantidad.setBounds(565, 15, 80, 20);
        formPanel.add(lCantidad);
        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(565, 35, 70, 30);
        formPanel.add(txtCantidad);

        // Fecha
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

        // Botón registrar
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

        // Botón limpiar
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

        // Historial
        JLabel lblHistorial = new JLabel("Historial de Entradas");
        lblHistorial.setFont(new Font("Arial", Font.BOLD, 13));
        lblHistorial.setBounds(20, 248, 250, 22);
        add(lblHistorial);

        String[] columnas = {"Proveedor", "Prenda", "Talla", "Cantidad", "Fecha"};
        modeloEntradas = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
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

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                comboProveedor.removeAllItems();
                comboProveedor.addItem("-- Seleccionar --");
                for (String s : proveedores.getProveedoresFormato()) comboProveedor.addItem(s);
                
                comboPrenda.removeAllItems();
                comboPrenda.addItem("-- Seleccionar --");
                for (String s : inventario.getPrendasFormato()) comboPrenda.addItem(s);
                cargarEntradasDesdeDB();
            }
        });

        btnLimpiar.addActionListener(e -> {
            comboProveedor.setSelectedIndex(0);
            comboPrenda.setSelectedIndex(0);
            comboTalla.setSelectedIndex(0);
            txtCantidad.setText("");
        });

        btnRegistrar.addActionListener(e -> {
            String proveedorSeleccionado = (String) comboProveedor.getSelectedItem();
            if (proveedorSeleccionado.equals("-- Seleccionar --")) {
                JOptionPane.showMessageDialog(this, "Selecciona un proveedor.");
                return;
            }
            String codigoProveedor = proveedorSeleccionado.split(" - ")[0];

            String prendaSeleccionada = (String) comboPrenda.getSelectedItem();
            if (prendaSeleccionada.equals("-- Seleccionar --")) {
                JOptionPane.showMessageDialog(this, "Selecciona una prenda.");
                return;
            }
            String codigoPrenda = prendaSeleccionada.split(" - ")[0];

            String talla    = (String) comboTalla.getSelectedItem();
            String cantidad = txtCantidad.getText().trim();
            String fecha    = txtFecha.getText().trim();

            if (cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campo obligatorio. Vuelva a intentarlo");
                return;
            }

            int cant;
            try {
                cant = Integer.parseInt(cantidad);
                if (cant <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero mayor a 0.");
                return;
            }

            modelo.EntradaInventario entrada = new modelo.EntradaInventario(codigoProveedor, codigoPrenda, talla, cant, fecha);
            
            // ── Llama de forma segura al Store Procedure de MySQL
            boolean ok = dao.EntradasDAO.insertar(entrada);
            if (!ok) return;
            
            // ==============================================================
            // CORRECCIÓN HISTÓRICA: Se eliminó la línea duplicada que sumaba stock manual
            // ==============================================================
            
            entradas.registrarEntrada(codigoProveedor, codigoPrenda, talla, cant, fecha);

            modeloEntradas.addRow(new Object[]{codigoProveedor, codigoPrenda, talla, cant, fecha});
            JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");
            btnLimpiar.doClick();
        });
    }

    private void cargarEntradasDesdeDB() {
        modeloEntradas.setRowCount(0);
        for (modelo.EntradaInventario e : dao.EntradasDAO.listar()) {
            modeloEntradas.addRow(new Object[]{
                e.getCodigoProveedor(),
                e.getCodigoPrenda(),
                e.getTalla(),
                e.getCantidad(),
                e.getFecha()
            });
        }
    }
}