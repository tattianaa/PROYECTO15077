package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import Formularios.FormNuevoProveedor;
import logica.ProveedorLogica;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestorProveedores extends JPanel {

    private static final long serialVersionUID = 1L;

    // ── Componentes visuales ──
    private JTextField txtBuscar;
    private JTable table;
    private JButton btnBuscar;
    private JButton btnNuevoProveedor;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JScrollPane scrollPane;

    // ── Lógica y modelo ──
    private ProveedorLogica gestor;
    private DefaultTableModel modeloTabla;

    // ── Constructor: solo diseño visual ──
    public GestorProveedores(ProveedorLogica gestor) {
        this.gestor = gestor;

        setBackground(new Color(245, 242, 225));
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Gestión de Proveedores");
        lblTitulo.setForeground(new Color(0, 64, 128));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 17));
        lblTitulo.setBounds(10, 27, 232, 14);
        add(lblTitulo);

        // Campo buscar
        txtBuscar = new JTextField();
        txtBuscar.setForeground(new Color(125, 125, 125));
        txtBuscar.setText("Ingresa código o nombre del proveedor..");
        txtBuscar.setBounds(10, 52, 232, 32);
        add(txtBuscar);

        // Botón buscar
        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setForeground(new Color(50, 50, 50));
        btnBuscar.setBackground(new Color(220, 190, 195));
        btnBuscar.setBounds(252, 52, 98, 32);
        add(btnBuscar);

        // Botón nuevo proveedor
        btnNuevoProveedor = new JButton("+ Nuevo Proveedor");
        btnNuevoProveedor.setBorderPainted(false);
        btnNuevoProveedor.setFocusPainted(false);
        btnNuevoProveedor.setForeground(Color.WHITE);
        btnNuevoProveedor.setBackground(new Color(130, 190, 140));
        btnNuevoProveedor.setBounds(360, 50, 196, 36);
        add(btnNuevoProveedor);

        // Tabla
        modeloTabla = new DefaultTableModel(
            new Object[][]{},
            new String[]{"CÓDIGO", "NOMBRE", "RUC", "TELÉFONO"}
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        table = new JTable(modeloTabla);
        table.setRowHeight(35);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 103, 557, 249);
        add(scrollPane);

        // Botón editar
        btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(220, 190, 195));
        btnEditar.setBorderPainted(false);
        btnEditar.setBounds(577, 105, 126, 32);
        add(btnEditar);

        // Botón eliminar
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(new Color(180, 180, 185));
        btnEliminar.setBounds(577, 148, 126, 32);
        add(btnEliminar);

        // Cargar datos y eventos
        cargarProveedoresDesdeDB();
        agregarEventos();
    }
    
  
    private void agregarEventos() {

        // Placeholder del campo buscar
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtBuscar.getText().equals("Ingresa código o nombre del proveedor..")) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(new Color(0, 0, 0));
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Ingresa código o nombre del proveedor..");
                    txtBuscar.setForeground(new Color(125, 125, 125));
                }
            }
        });

        // Botón BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = txtBuscar.getText().trim().toLowerCase();
                modeloTabla.setRowCount(0);
                boolean hayResultados = false;
                for (modelo.Proveedor p : gestor.getProveedores()) {
                    if (texto.isEmpty()
                        || texto.equals("ingresa código o nombre del proveedor..")
                        || p.getNombre().toLowerCase().contains(texto)
                        || p.getCodigo().toLowerCase().contains(texto)) {
                        modeloTabla.addRow(new Object[]{
                            p.getCodigo(), p.getNombre(), p.getRuc(), p.getTelefono()
                        });
                        hayResultados = true;
                    }
                }
                if (!hayResultados)
                    JOptionPane.showMessageDialog(null, "No se encontraron proveedores.");
                txtBuscar.setText("");
                txtBuscar.requestFocus();
            }
        });

        // Botón NUEVO PROVEEDOR
        btnNuevoProveedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recarga la lista antes de abrir para que las validaciones funcionen
                cargarProveedoresDesdeDB();
                FormNuevoProveedor form = new FormNuevoProveedor(modeloTabla, gestor);
                form.setLocationRelativeTo(null);
                form.setVisible(true);
            }
        });

        // Botón EDITAR
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un proveedor para editar.");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Editar: " + modeloTabla.getValueAt(fila, 1));
            }
        });

        // Botón ELIMINAR
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un proveedor para eliminar.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Eliminar este proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String codigo = (String) modeloTabla.getValueAt(fila, 0);
                    boolean ok = dao.ProveedorDAO.eliminar(codigo);
                    if (ok) {
                        gestor.Gestionproveedor(fila);
                        modeloTabla.removeRow(fila);
                        JOptionPane.showMessageDialog(null, "Proveedor eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar de la base de datos.");
                    }
                }
            }
        });

        // Recarga al hacerse visible
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                cargarProveedoresDesdeDB();
            }
        });
    }

    // ── Carga proveedores desde MySQL ──
    private void cargarProveedoresDesdeDB() {
        modeloTabla.setRowCount(0);
        // Limpia la lista en memoria antes de recargar para evitar duplicados
        gestor.limpiar();
        for (modelo.Proveedor p : dao.ProveedorDAO.listar()) {
            gestor.Gestionproveedor(p.getCodigo(), p.getNombre(), p.getRuc(), p.getTelefono());
            modeloTabla.addRow(new Object[]{
                p.getCodigo(), p.getNombre(), p.getRuc(), p.getTelefono()
            });
        }
    }
}
