package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logica.InventarioLogica;
import logica.ProveedorLogica;
import modelo.Proveedor;

public class GestorProveedores extends JPanel {

    // Tabla donde se muestran los proveedores registrados
    private DefaultTableModel modeloTabla;

    private ProveedorLogica gestor = new ProveedorLogica();

    public GestorProveedores() {
        setLayout(null);
        setBackground(new Color(245, 242, 225)); // fondo crema del proyecto

        // ── Título ──
        JLabel lblTitulo = new JLabel("Gestión de Proveedores");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 300, 30);
        add(lblTitulo);

        // ── Botón nuevo proveedor ──
        JButton btnNuevo = new JButton("+ NUEVO PROVEEDOR");
        btnNuevo.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevo.setBackground(new Color(130, 190, 140)); // verde
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBorderPainted(false);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setOpaque(true);
        btnNuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevo.setBounds(580, 15, 190, 36);
        add(btnNuevo);

        // ── Tabla de proveedores ──
        String[] columnas = {"Código", "Nombre", "RUC", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; } // solo lectura
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(240, 235, 225));
        tabla.setBackground(Color.WHITE);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(220, 190, 195));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 65, 750, 500);
        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
        add(scroll);

        // ── Acción del botón: abre formulario ──
        btnNuevo.addActionListener(e -> abrirFormulario());
    }

    // Abre el diálogo para registrar un nuevo proveedor
    private void abrirFormulario() {
        // Busca la ventana padre para centrar el diálogo
        JFrame ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog dlg = new JDialog(ventanaPadre, "Nuevo Proveedor", true);
        dlg.setSize(400, 380);
        dlg.setLocationRelativeTo(ventanaPadre);
        dlg.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dlg.setContentPane(panel);

        // ── Campos del formulario ──
        JLabel lCodigo = new JLabel("Código (ej: PRV001):");
        lCodigo.setBounds(20, 20, 180, 20);
        panel.add(lCodigo);
        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(20, 42, 350, 30);
        panel.add(txtCodigo);

        JLabel lNombre = new JLabel("Nombre:");
        lNombre.setBounds(20, 80, 180, 20);
        panel.add(lNombre);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(20, 102, 350, 30);
        panel.add(txtNombre);

        JLabel lRuc = new JLabel("RUC (11 dígitos):");
        lRuc.setBounds(20, 140, 180, 20);
        panel.add(lRuc);
        JTextField txtRuc = new JTextField();
        txtRuc.setBounds(20, 162, 350, 30);
        panel.add(txtRuc);

        JLabel lTelefono = new JLabel("Teléfono:");
        lTelefono.setBounds(20, 200, 180, 20);
        panel.add(lTelefono);
        JTextField txtTelefono = new JTextField();
        txtTelefono.setBounds(20, 222, 350, 30);
        panel.add(txtTelefono);

        // ── Botones guardar y cancelar ──
        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBackground(new Color(130, 190, 140)); // verde
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setOpaque(true);
        btnGuardar.setBounds(20, 265, 160, 36);
        panel.add(btnGuardar);

        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(new Color(220, 100, 100)); // rojo
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setOpaque(true);
        btnCancelar.setBounds(210, 265, 160, 36);
        panel.add(btnCancelar);

        btnCancelar.addActionListener(e -> dlg.dispose());

        btnGuardar.addActionListener(e -> {
            String codigo   = txtCodigo.getText().trim().toUpperCase();
            String nombre   = txtNombre.getText().trim();
            String ruc      = txtRuc.getText().trim();
            String telefono = txtTelefono.getText().trim();

            // Validar que ningún campo esté vacío
            if (codigo.isEmpty() || nombre.isEmpty() || ruc.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(dlg, "Todos los campos son obligatorios.");
                return;
            }

            // PASO 2: Valida con gestor.validar(nombre, ruc)
            // — nombre solo debe tener letras
            // — ruc debe tener exactamente 11 números
            // Si no retorna "OK" muestra el error con JOptionPane y haz return
            String resultado = gestor.validar(nombre, ruc);

            if (!resultado.equals("OK")) {
                JOptionPane.showMessageDialog(dlg, resultado);
                return;
            }
            // PASO 3: Guardar
                gestor.agregarProveedor(codigo, nombre, ruc, telefono);
            // PASO 4: Recargar tabla
            gestor.getProveedores();

            JOptionPane.showMessageDialog(dlg, "Proveedor registrado correctamente.");
            dlg.dispose();
        });

        dlg.setVisible(true);
    }
}
