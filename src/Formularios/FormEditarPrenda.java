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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

public class FormEditarPrenda extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTextField txtPrecio;
	private JButton btnGuardarCambios;
	private JButton btnCancelar;
	private JButton btnImagen;
	
	// Ruta de la imagen seleccionada — vacía si no eligió ninguna
	private String rutaImagen = "";
	// Label que muestra el nombre del archivo de imagen
	private JLabel lblRuta;
	// ComboBox de categoría — necesita ser campo para leerlo en guardar
	private JComboBox<String> cboCategoria;
	// Datos recibidos desde PanelGestion
	private int fila;
	private javax.swing.table.DefaultTableModel modeloTabla;
	private logica.InventarioLogica gestor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Datos vacíos de prueba para previsualizar en Eclipse
			FormEditarPrenda dialog = new FormEditarPrenda(
				0,
				new modelo.Prenda("", "", 0, "", 0, "", 0, new java.util.ArrayList<>()),
				new javax.swing.table.DefaultTableModel(),
				new logica.InventarioLogica()
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
	// Recibe la fila, prenda, tabla y lógica desde PanelGestion
	public FormEditarPrenda(int fila, modelo.Prenda prenda,
	        javax.swing.table.DefaultTableModel modeloTabla,
	        logica.InventarioLogica gestor) {
	    this.fila = fila;
	    this.modeloTabla = modeloTabla;
	    this.gestor = gestor;
		setTitle("Formulario Editar Prenda");
		setBounds(100, 100, 376, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNombre.setBounds(10, 77, 64, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(10, 102, 261, 28);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblCodigo = new JLabel("Código (no editable):");
			lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
			lblCodigo.setBounds(10, 11, 150, 14);
			contentPanel.add(lblCodigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setText((String) null);
			txtCodigo.setEnabled(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(10, 36, 306, 30);
			contentPanel.add(txtCodigo);
		}
		{
			JLabel lblPrecio = new JLabel("Precio:");
			lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPrecio.setBounds(10, 145, 64, 14);
			contentPanel.add(lblPrecio);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setColumns(10);
			txtPrecio.setBounds(10, 170, 261, 28);
			contentPanel.add(txtPrecio);
		}
		{
			JLabel lblCategoria = new JLabel("Categoría:");
			lblCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
			lblCategoria.setBounds(10, 209, 100, 14);
			contentPanel.add(lblCategoria);
		}
		{
			cboCategoria = new JComboBox<String>();
			cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"PANTALONES & JEANS", "POLOS", "FALDAS & SHORTS", "VESTIDOS", "POLERAS", "CASACAS"}));
			cboCategoria.setBounds(10, 234, 231, 22);
			contentPanel.add(cboCategoria);
		}
		{
			btnImagen = new JButton("Cambiar Imagen");
			btnImagen.addActionListener(this);
			btnImagen.setForeground(Color.WHITE);
			btnImagen.setFocusPainted(false);
			btnImagen.setBorderPainted(false);
			btnImagen.setBackground(new Color(77, 160, 210));
			btnImagen.setBounds(10, 291, 140, 26);
			contentPanel.add(btnImagen);
		}
		{
			lblRuta = new JLabel("Sin imagen seleccionada");
			lblRuta.setForeground(new Color(150, 150, 150));
			lblRuta.setFont(new Font("Arial", Font.ITALIC, 11));
			lblRuta.setBounds(166, 303, 180, 14);
			contentPanel.add(lblRuta);
		}
		{
			btnGuardarCambios = new JButton("GUARDAR CAMBIOS");
			btnGuardarCambios.addActionListener(this);
			btnGuardarCambios.setForeground(Color.WHITE);
			btnGuardarCambios.setFocusPainted(false);
			btnGuardarCambios.setBorderPainted(false);
			btnGuardarCambios.setBackground(new Color(130, 190, 140));
			btnGuardarCambios.setBounds(10, 344, 160, 36);
			contentPanel.add(btnGuardarCambios);
		}
		{
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.addActionListener(this);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setBorderPainted(false);
			btnCancelar.setBackground(new Color(220, 100, 100));
			btnCancelar.setBounds(197, 344, 137, 36);
			contentPanel.add(btnCancelar);
		}
		// Pre-llenamos los campos con los datos actuales de la prenda
		txtNombre.setText(prenda.getNombre());
		txtCodigo.setText(prenda.getCodigo());
		txtPrecio.setText(String.valueOf(prenda.getPrecio()));
		cboCategoria.setSelectedItem(prenda.getCategoria());
		if (prenda.getImagen() != null && !prenda.getImagen().isEmpty()) {
		    lblRuta.setText(prenda.getImagen());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnImagen) {
			do_btnImagen_actionPerformed(e);
		}
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnGuardarCambios) {
			do_btnGuardarCambios_actionPerformed(e);
		}
	}
	protected void do_btnGuardarCambios_actionPerformed(ActionEvent e) {
		// Leer campos
	    String nombre      = txtNombre.getText().trim().toUpperCase();
	    String precioTexto = txtPrecio.getText().trim();
	    String categoria   = cboCategoria.getSelectedItem().toString();
	    String codigo      = txtCodigo.getText().trim();

	    // ===== VALIDAR NOMBRE =====
	    try {
	        if (nombre.isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
	            txtNombre.requestFocus();
	            return;
	        }
	        if (!nombre.matches("[A-ZÁÉÍÓÚÑ. ]+")) {
	            javax.swing.JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.");
	            txtNombre.setText(""); txtNombre.requestFocus(); return;
	        }
	        // Verificar que el nombre no exista en otra prenda diferente a la que se edita
	        for (int i = 0; i < gestor.getPrendas().size(); i++) {
	            if (i != fila && gestor.getPrendas().get(i).getNombre().equals(nombre)) {
	                javax.swing.JOptionPane.showMessageDialog(null, "Ese nombre ya existe en otra prenda.");
	                txtNombre.setText(""); txtNombre.requestFocus(); return;
	            }
	        }
	    } catch (Exception ex) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Error al validar nombre.");
	        return;
	    }

	    // ===== VALIDAR PRECIO =====
	    double precio = 0;
	    try {
	        if (precioTexto.isEmpty() || !precioTexto.matches("\\d+(\\.\\d+)?")) {
	            javax.swing.JOptionPane.showMessageDialog(null, "Precio inválido. Solo números.");
	            txtPrecio.setText(""); txtPrecio.requestFocus(); return;
	        }
	        // Convertir a double para guardarlo
	        precio = Double.parseDouble(precioTexto);
	        if (precio <= 0) {
	            javax.swing.JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0.");
	            txtPrecio.setText(""); txtPrecio.requestFocus(); return;
	        }
	    } catch (Exception ex) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Error al validar precio.");
	        return;
	    }

	    // ===== GUARDAR CAMBIOS =====
	    // Actualiza en la lógica en memoria
	    gestor.editarPrenda(fila, codigo, nombre, precioTexto, categoria, new java.util.ArrayList<>());

	    // Actualizar imagen si eligió una nueva
	    if (!rutaImagen.isEmpty()) {
	        gestor.getPrendas().get(fila).setImagen(rutaImagen);
	        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
	            new javax.swing.ImageIcon(rutaImagen)
	                .getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	        modeloTabla.setValueAt(icon, fila, 1);
	    }

	    // Persiste los cambios en MySQL
	    modelo.Prenda pActualizada = gestor.getPrendas().get(fila);
	    boolean okDB = dao.PrendaDAO.editar(pActualizada);
	    if (!okDB) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos.");
	        return;
	    }

	    // Actualizar fila en la tabla visual
	    modeloTabla.setValueAt(codigo,          fila, 0);
	    modeloTabla.setValueAt(nombre,          fila, 2);
	    modeloTabla.setValueAt("S/. " + precio, fila, 4);
	    modeloTabla.setValueAt(categoria,       fila, 5);

	    javax.swing.JOptionPane.showMessageDialog(null, "Prenda actualizada correctamente.");
	    dispose();
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		 // Cierra el formulario sin guardar nada
	    dispose();
	}
	protected void do_btnImagen_actionPerformed(ActionEvent e) {
		// Abre el selector de archivos directo en la carpeta imagenes del proyecto
	    JFileChooser fc = new JFileChooser("imagenes");
	    
	    // Solo muestra jpg, jpeg y png
	    fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
	        "Imágenes", "jpg", "jpeg", "png"));
	    
	    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        // Guarda solo el nombre relativo
	        rutaImagen = "imagenes/" + fc.getSelectedFile().getName();
	        // Muestra solo el nombre del archivo
	        lblRuta.setText(fc.getSelectedFile().getName());
	    }
	}
}
