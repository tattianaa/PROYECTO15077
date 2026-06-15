package Formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormEditarProveedor extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTextField txtRuc;
	private JTextField txtTelefono;

	// Datos recibidos desde GestorProveedores
	private int fila;
	private logica.ProveedorLogica gestor;
	private javax.swing.table.DefaultTableModel modeloTabla;
	private JButton btnGuardarCambios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
	        // Datos vacíos de prueba para previsualizar en Eclipse
	        FormEditarProveedor dialog = new FormEditarProveedor(
	            0,
	            new modelo.Proveedor("", "", "", ""),
	            new javax.swing.table.DefaultTableModel(),
	            new logica.ProveedorLogica()
	        );
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        dialog.setVisible(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the dialog.
	 */
	// Recibe la fila, el proveedor a editar, la tabla y la lógica
	public FormEditarProveedor(int fila, modelo.Proveedor proveedor,
	        javax.swing.table.DefaultTableModel modeloTabla, logica.ProveedorLogica gestor) {
	    this.fila = fila;
	    this.modeloTabla = modeloTabla;
	    this.gestor = gestor;

		setTitle("Editar Proveedor");
		setBounds(100, 100, 450, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNombre.setBounds(10, 21, 64, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(10, 39, 306, 30);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblCodigo = new JLabel("Código (no editable):");
			lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
			lblCodigo.setBounds(10, 77, 150, 14);
			contentPanel.add(lblCodigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(10, 91, 306, 30);
			contentPanel.add(txtCodigo);
		}
		{
			JLabel lblRuc = new JLabel("RUC:");
			lblRuc.setFont(new Font("Arial", Font.PLAIN, 12));
			lblRuc.setBounds(10, 132, 64, 14);
			contentPanel.add(lblRuc);
		}
		{
			txtRuc = new JTextField();
			txtRuc.setColumns(10);
			txtRuc.setBounds(10, 148, 306, 30);
			contentPanel.add(txtRuc);
		}
		{
			JLabel lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setFont(new Font("Arial", Font.PLAIN, 12));
			lblTelfono.setBounds(10, 188, 64, 14);
			contentPanel.add(lblTelfono);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(10, 213, 306, 30);
			contentPanel.add(txtTelefono);
		}
		{
			btnGuardarCambios = new JButton("GUARDAR CAMBIOS");
			btnGuardarCambios.addActionListener(this);
			btnGuardarCambios.setForeground(Color.WHITE);
			btnGuardarCambios.setFocusPainted(false);
			btnGuardarCambios.setBorderPainted(false);
			btnGuardarCambios.setBackground(new Color(130, 190, 140));
			btnGuardarCambios.setBounds(10, 293, 160, 36);
			contentPanel.add(btnGuardarCambios);
		}
		{
			JButton btnCancelar = new JButton("CANCELAR");
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setBorderPainted(false);
			btnCancelar.setBackground(new Color(220, 100, 100));
			btnCancelar.setBounds(198, 293, 137, 36);
			contentPanel.add(btnCancelar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		// Pre-llenamos los campos con los datos actuales del proveedor
		txtNombre.setText(proveedor.getNombre());
		txtCodigo.setText(proveedor.getCodigo());
		txtRuc.setText(proveedor.getRuc());
		txtTelefono.setText(proveedor.getTelefono());

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardarCambios) {
			do_btnGuardarCambios_actionPerformed(e);
		}
	}
	protected void do_btnGuardarCambios_actionPerformed(ActionEvent e) {
	}
}
