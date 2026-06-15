package Formularios;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormNuevoProveedor extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    // ── Componentes visuales ──
    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtRuc;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnCancelar;

    // ── Referencias de lógica ──
    private javax.swing.table.DefaultTableModel modeloTabla;
    private logica.ProveedorLogica gestor;

    // ── Para previsualizar en Eclipse ──
    public static void main(String[] args) {
        try {
            FormNuevoProveedor dialog = new FormNuevoProveedor(
                new javax.swing.table.DefaultTableModel(),
                new logica.ProveedorLogica()
            );
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ── Constructor: solo diseño visual ──
    public FormNuevoProveedor(javax.swing.table.DefaultTableModel modeloTabla, logica.ProveedorLogica gestor) {
        this.modeloTabla = modeloTabla;
        this.gestor = gestor;

        setTitle("Registrar Nuevo Proveedor");
        setBounds(100, 100, 440, 420);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(245, 242, 225));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Registrar Nuevo Proveedor");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(0, 64, 128));
        lblTitulo.setBounds(10, 10, 280, 20);
        contentPanel.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código (ej: PRV-001):");
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCodigo.setBounds(10, 45, 180, 14);
        contentPanel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(10, 65, 350, 30);
        contentPanel.add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNombre.setBounds(10, 105, 100, 14);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(10, 125, 350, 30);
        contentPanel.add(txtNombre);

        JLabel lblRuc = new JLabel("RUC (11 dígitos):");
        lblRuc.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRuc.setBounds(10, 165, 150, 14);
        contentPanel.add(lblRuc);

        txtRuc = new JTextField();
        txtRuc.setBounds(10, 185, 350, 30);
        contentPanel.add(txtRuc);

        JLabel lblTelefono = new JLabel("Teléfono (9 dígitos):");
        lblTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTelefono.setBounds(10, 225, 150, 14);
        contentPanel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(10, 245, 350, 30);
        contentPanel.add(txtTelefono);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setBackground(new Color(130, 190, 140));
        btnGuardar.setBounds(10, 300, 160, 36);
        contentPanel.add(btnGuardar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.addActionListener(this);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setBackground(new Color(220, 100, 100));
        btnCancelar.setBounds(226, 300, 160, 36);
        contentPanel.add(btnCancelar);
    }

    // ── Eventos ──
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar)  do_btnGuardar_actionPerformed(e);
        if (e.getSource() == btnCancelar) do_btnCancelar_actionPerformed(e);
    }

    // ── Lógica del botón GUARDAR ──
    protected void do_btnGuardar_actionPerformed(ActionEvent e) {
        String codigo   = txtCodigo.getText().trim().toUpperCase();
        String nombre   = txtNombre.getText().trim().toUpperCase();
        String ruc      = txtRuc.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // ── Validar código ──
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el código.");
            txtCodigo.requestFocus(); return;
        }
        // Agrega guion automáticamente si no lo tiene
        if (codigo.length() > 3 && !codigo.contains("-"))
            codigo = codigo.substring(0, 3) + "-" + codigo.substring(3);
        if (!codigo.matches("[A-Z]{3}-\\d{3}")) {
            JOptionPane.showMessageDialog(null, "El código debe tener el formato PRV-001.");
            txtCodigo.setText(""); txtCodigo.requestFocus(); return;
        }
        if (gestor.Gestionproveedor(codigo) != -1) {
            JOptionPane.showMessageDialog(null, "Ese código ya existe.");
            txtCodigo.setText(""); txtCodigo.requestFocus(); return;
        }

        // ── Validar nombre ──
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre.");
            txtNombre.requestFocus(); return;
        }
        if (!nombre.matches("[A-ZÁÉÍÓÚÑ. ]+")) {
            JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.");
            txtNombre.setText(""); txtNombre.requestFocus(); return;
        }

        // ── Validar RUC ──
        if (ruc.isEmpty() || !ruc.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(null, "El RUC debe tener 11 dígitos numéricos.");
            txtRuc.setText(""); txtRuc.requestFocus(); return;
        }
        if (!ruc.startsWith("10") && !ruc.startsWith("20")) {
            JOptionPane.showMessageDialog(null, "El RUC debe iniciar con 10 o 20.");
            txtRuc.setText(""); txtRuc.requestFocus(); return;
        }
        // Verifica RUC duplicado en memoria
        for (modelo.Proveedor pv : gestor.getProveedores()) {
            if (pv.getRuc().equals(ruc)) {
                JOptionPane.showMessageDialog(null, "Ese RUC ya está registrado.");
                txtRuc.setText(""); txtRuc.requestFocus(); return;
            }
        }

        // ── Validar teléfono ──
        if (telefono.isEmpty() || !telefono.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener 9 dígitos numéricos.");
            txtTelefono.setText(""); txtTelefono.requestFocus(); return;
        }
        // Verifica teléfono duplicado en memoria
        for (modelo.Proveedor pv : gestor.getProveedores()) {
            if (pv.getTelefono().equals(telefono)) {
                JOptionPane.showMessageDialog(null, "Ese teléfono ya está registrado.");
                txtTelefono.setText(""); txtTelefono.requestFocus(); return;
            }
        }

        // ── Guardar en MySQL primero ──
        modelo.Proveedor p = new modelo.Proveedor(codigo, nombre, ruc, telefono);
        boolean ok = dao.ProveedorDAO.insertar(p);
        if (!ok) return; // el DAO ya mostró el error

        // ── Si MySQL confirmó, agrega a memoria y tabla ──
        gestor.Gestionproveedor(codigo, nombre, ruc, telefono);
        modeloTabla.addRow(new Object[]{ codigo, nombre, ruc, telefono });

        JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente.");
        dispose();
    }

    // ── Lógica del botón CANCELAR ──
    protected void do_btnCancelar_actionPerformed(ActionEvent e) {
        dispose();
    }
}
