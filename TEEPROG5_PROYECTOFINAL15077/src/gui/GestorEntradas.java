package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

// Panel de registro de entradas de mercadería
// Se incrusta dentro de GestorAdministrativo usando CardLayout
public class GestorEntradas extends JPanel {

    public GestorEntradas() {
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

        // Campo: código del proveedor
        JLabel lProv = new JLabel("Proveedor (código):");
        lProv.setFont(new Font("Arial", Font.PLAIN, 12));
        lProv.setBounds(15, 15, 150, 20);
        formPanel.add(lProv);
        JTextField txtProveedor = new JTextField();
        txtProveedor.setBounds(15, 35, 150, 30);
        formPanel.add(txtProveedor);

        // Campo: código de la prenda
        JLabel lPrenda = new JLabel("Prenda (código):");
        lPrenda.setFont(new Font("Arial", Font.PLAIN, 12));
        lPrenda.setBounds(180, 15, 130, 20);
        formPanel.add(lPrenda);
        JTextField txtPrenda = new JTextField();
        txtPrenda.setBounds(180, 35, 130, 30);
        formPanel.add(txtPrenda);

        // ComboBox: talla de la prenda
        JLabel lTalla = new JLabel("Talla:");
        lTalla.setFont(new Font("Arial", Font.PLAIN, 12));
        lTalla.setBounds(325, 15, 60, 20);
        formPanel.add(lTalla);
        String[] tallas = {"XS", "S", "M", "L", "XL", "26", "28", "30", "32", "34", "ÚNICA"};
        JComboBox<String> comboTalla = new JComboBox<>(tallas);
        comboTalla.setBounds(325, 35, 80, 30);
        formPanel.add(comboTalla);

        // Campo: color de la prenda
        JLabel lColor = new JLabel("Color:");
        lColor.setFont(new Font("Arial", Font.PLAIN, 12));
        lColor.setBounds(420, 15, 60, 20);
        formPanel.add(lColor);
        JTextField txtColor = new JTextField();
        txtColor.setBounds(420, 35, 100, 30);
        formPanel.add(txtColor);

        // Campo: cantidad de unidades que ingresaron
        JLabel lCantidad = new JLabel("Cantidad:");
        lCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        lCantidad.setBounds(535, 15, 80, 20);
        formPanel.add(lCantidad);
        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(535, 35, 80, 30);
        formPanel.add(txtCantidad);

        // Campo: fecha de ingreso en formato dd/MM/yyyy
        JLabel lFecha = new JLabel("Fecha (dd/MM/yyyy):");
        lFecha.setFont(new Font("Arial", Font.PLAIN, 12));
        lFecha.setBounds(630, 15, 160, 20);
        formPanel.add(lFecha);
        JTextField txtFecha = new JTextField();
        txtFecha.setBounds(630, 35, 110, 30);
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
        btnRegistrar.setBounds(15, 100, 200, 36);
        formPanel.add(btnRegistrar);

        // Botón para limpiar todos los campos del formulario
        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(180, 180, 185));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setBounds(230, 100, 120, 36);
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

        // Limpiar todos los campos del formulario
        btnLimpiar.addActionListener(e -> {
            txtProveedor.setText("");
            txtPrenda.setText("");
            txtColor.setText("");
            txtCantidad.setText("");
            txtFecha.setText("");
            comboTalla.setSelectedIndex(0);
        });

        // Validar y registrar la entrada al presionar el botón
        btnRegistrar.addActionListener(e -> {
            String proveedor = txtProveedor.getText().trim().toUpperCase();
            String prenda    = txtPrenda.getText().trim().toUpperCase();
            String talla     = (String) comboTalla.getSelectedItem();
            String color     = txtColor.getText().trim();
            String cantidad  = txtCantidad.getText().trim();
            String fecha     = txtFecha.getText().trim();

            // Verificar que ningún campo esté vacío
            if (proveedor.isEmpty() || prenda.isEmpty() || color.isEmpty()
                    || cantidad.isEmpty() || fecha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
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

            // Verificar que la fecha tenga el formato dd/MM/yyyy
            if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.");
                return;
            }

            // TODO: conectar con EntradaLogica.registrarEntrada() cuando se implemente la base de datos
            // Por ahora solo agrega la fila a la tabla visual
            modeloEntradas.addRow(new Object[]{proveedor, prenda, talla, color, cant, fecha});
            JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");

            // Limpiar el formulario después de registrar
            btnLimpiar.doClick();
        });
    }
}
