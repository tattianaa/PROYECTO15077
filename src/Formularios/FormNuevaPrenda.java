package Formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.InventarioLogica;
import modelo.Prenda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormNuevaPrenda extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JComboBox<String> cboCategoria;
	private JLabel lblRuta;
	private JButton btnImagen;
	private JButton btnGuardar;
	private JButton btnCancelar;
    private String rutaImagen = "";
	private DefaultTableModel modeloTabla;
	private InventarioLogica gestor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
	        // Objetos vacíos de prueba para previsualizar en Eclipse
	        FormNuevaPrenda dialog = new FormNuevaPrenda(
	            new DefaultTableModel(),
	            new InventarioLogica()
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
	public FormNuevaPrenda(DefaultTableModel modeloTabla, InventarioLogica gestor) {
		// Guardamos las referencias para usarlas en el botón GUARDAR
	    this.modeloTabla = modeloTabla;
	    this.gestor = gestor;
	    
		setBounds(100, 100, 450, 441);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_1 = new JLabel("Código(Ej. ABC123)");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(10, 11, 180, 20);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(10, 33, 350, 30);
			contentPanel.add(txtCodigo);
		}
		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 74, 100, 20);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(10, 97, 350, 30);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio:");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(10, 138, 100, 20);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setColumns(10);
			txtPrecio.setBounds(10, 161, 350, 30);
			contentPanel.add(txtPrecio);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Categoría:");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(10, 202, 100, 20);
			contentPanel.add(lblNewLabel_1);
		}
		{
			cboCategoria = new JComboBox<String>();
			cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"PANTALONES & JEANS", "POLOS", "FALDAS & SHORTS", "VESTIDOS", "POLERAS", "CASACAS"}));
			cboCategoria.setBounds(10, 226, 350, 30);
			contentPanel.add(cboCategoria);
		}
		{
			lblRuta = new JLabel("Sin imagen seleccionada");
			lblRuta.setForeground(new Color(150, 150, 150));
			lblRuta.setFont(new Font("Arial", Font.ITALIC, 11));
			lblRuta.setBounds(10, 285, 230, 20);
			contentPanel.add(lblRuta);
		}
		{
			btnImagen = new JButton("Seleccionar imagen");
			btnImagen.addActionListener(this);
			btnImagen.setFocusPainted(false);
			btnImagen.setBorderPainted(false);
			btnImagen.setBackground(new Color(77, 160, 210));
			btnImagen.setBounds(250, 279, 150, 26);
			contentPanel.add(btnImagen);
		}
		{
			btnGuardar = new JButton("GUARDAR");
			btnGuardar.addActionListener(this);
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setFocusPainted(false);
			btnGuardar.setBorderPainted(false);
			btnGuardar.setBackground(new Color(130, 190, 140));
			btnGuardar.setBounds(10, 322, 160, 36);
			contentPanel.add(btnGuardar);
		}
		{
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.addActionListener(this);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setBorderPainted(false);
			btnCancelar.setBackground(new Color(220, 100, 100));
			btnCancelar.setBounds(223, 322, 160, 36);
			contentPanel.add(btnCancelar);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnGuardar) {
			do_btnGuardar_actionPerformed(e);
		}
		if (e.getSource() == btnImagen) {
			do_btnImagen_actionPerformed(e);
		}
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
	protected void do_btnGuardar_actionPerformed(ActionEvent e) {
		String nombre = txtNombre.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String categoria = cboCategoria.getSelectedItem().toString();
        String textoPrecio = txtPrecio.getText().trim();

        // Verifica que todos los campos estén llenos
        if (nombre.isEmpty() || codigo.isEmpty() || textoPrecio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos. Vuelva aintentarlo. Por favor.");
            return;
        }

        // Verifica que el código no exista ya en la lista
        if (gestor.gestionar(codigo) != -1) {
            JOptionPane.showMessageDialog(null, "Ese código ya existe. Vuelva a intentarlo. Por favor.");
            txtCodigo.setText("");
            txtCodigo.requestFocus();
            return;
        }

        double precio = 0;
        try {
            precio = Double.parseDouble(textoPrecio);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido. ");
            return;
        }

        Prenda p = new Prenda(codigo, nombre, precio, categoria, 0, rutaImagen, 0, new java.util.ArrayList<>());

        // Primero guarda en MySQL — si falla no agrega a la tabla
        boolean ok = dao.PrendaDAO.insertar(p);
        if (!ok) return;

        // Si MySQL confirmó, agrega a la lógica en memoria
        gestor.gestionar(codigo, nombre, precio, categoria, new java.util.ArrayList<>());

        // Agrega la fila a la tabla visual
        javax.swing.ImageIcon icon = null;
        if (!rutaImagen.isEmpty()) {
            icon = new javax.swing.ImageIcon(
                new javax.swing.ImageIcon(rutaImagen)
                    .getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
        }
        modeloTabla.addRow(new Object[]{
            p.getCodigo(),
            icon,
            p.getNombre(),
            p.stockPorVariante(),
            "S/. " + p.getPrecio(),
            p.getCategoria()
        });

        JOptionPane.showMessageDialog(null, "Prenda guardada correctamente.");
        dispose();
    }
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		
	dispose();
	}
}
