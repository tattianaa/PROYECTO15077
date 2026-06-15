package Formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDetallesPrenda extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblInfo;
	private JLabel lblStock;
	private JLabel lblFiltrarPorTalla;
	private JComboBox<String> cboFiltroTalla;
	private JSeparator separator_1;
	private JSeparator separator;
	private JLabel lblVariantes;
	private JScrollPane scrollPane;
	private JTable tablaVarianteTalla;
	private JSeparator separator_2;
	private JLabel lblEntradas;
	private JScrollPane scrollPane_1;
	private JTable tablaEntradas;
	private JSeparator separator_3;
	private JLabel lblSalidas;
	private JScrollPane scrollPane_2;
	private JTable tablaSalidas;
	private JButton btnCerrar;
	
	// Lógica de entradas — recibida desde PanelGestion para mostrar el historial
	private logica.EntradaLogica entradas;


	
	 
	
	
	public static void main(String[] args) {
		try {
			java.util.List<modelo.Variante> variantesVacias = new java.util.ArrayList<>();
			FormDetallesPrenda dialog = new FormDetallesPrenda(
			    new modelo.Prenda("", "", 0, "", 0, "", 0, variantesVacias),
			    new logica.EntradaLogica());
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        dialog.setVisible(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
	}

	/**
	 * Create the dialog.
	 */
	
	// Recibe la prenda desde PanelGestion para mostrar sus detalles
	public FormDetallesPrenda(modelo.Prenda prenda,  logica.EntradaLogica entradas) {
	    this.entradas = entradas;
	    
		setBounds(100, 100, 475, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblImagen = new JLabel("");
			lblImagen.setOpaque(true);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagen.setBackground(new Color(220, 220, 220));
			lblImagen.setBounds(145, 11, 120, 120);
			contentPanel.add(lblImagen);
		}
		{
			lblNombre = new JLabel("New label");
			lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
			lblNombre.setBounds(168, 142, 110, 14);
			contentPanel.add(lblNombre);
		}
		{
			lblInfo = new JLabel("Código:                  | Categoría:                        |      Precio: S/. 0.0");
			lblInfo.setFont(new Font("Arial", Font.PLAIN, 11));
			lblInfo.setBounds(10, 167, 410, 14);
			contentPanel.add(lblInfo);
		}
		{
			lblStock = new JLabel("Stock total: 0 uds");
			lblStock.setForeground(new Color(81, 183, 145));
			lblStock.setFont(new Font("Arial", Font.BOLD, 12));
			lblStock.setBounds(10, 192, 200, 14);
			contentPanel.add(lblStock);
		}
		{
			lblFiltrarPorTalla = new JLabel("Filtrar por talla: ");
			lblFiltrarPorTalla.setFont(new Font("Arial", Font.BOLD, 12));
			lblFiltrarPorTalla.setBounds(10, 228, 99, 14);
			contentPanel.add(lblFiltrarPorTalla);
		}
		{
			cboFiltroTalla = new JComboBox<String>();
			cboFiltroTalla.setBounds(118, 220, 120, 22);
			contentPanel.add(cboFiltroTalla);
		}
		{
			separator_1 = new JSeparator();
			separator_1.setBounds(10, 253, 410, 7);
			contentPanel.add(separator_1);
		}
		{
			separator = new JSeparator();
			separator.setBounds(10, 210, 410, 7);
			contentPanel.add(separator);
		}
		{
			lblVariantes = new JLabel("Stock por variante");
			lblVariantes.setFont(new Font("Arial", Font.BOLD, 12));
			lblVariantes.setBounds(10, 263, 150, 14);
			contentPanel.add(lblVariantes);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 288, 439, 73);
			contentPanel.add(scrollPane);
			{
				tablaVarianteTalla = new JTable();
				scrollPane.setViewportView(tablaVarianteTalla);
			}
		}
		{
			separator_2 = new JSeparator();
			separator_2.setBounds(10, 370, 410, 7);
			contentPanel.add(separator_2);
		}
		{
			lblEntradas = new JLabel("Entradas de mercadería:");
			lblEntradas.setForeground(new Color(0, 150, 0));
			lblEntradas.setFont(new Font("Arial", Font.BOLD, 12));
			lblEntradas.setBounds(10, 380, 200, 14);
			contentPanel.add(lblEntradas);
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 405, 439, 73);
			contentPanel.add(scrollPane_1);
			{
				tablaEntradas = new JTable();
				scrollPane_1.setViewportView(tablaEntradas);
			}
		}
		{
			separator_3 = new JSeparator();
			separator_3.setBounds(10, 489, 410, 7);
			contentPanel.add(separator_3);
		}
		{
			lblSalidas = new JLabel("Salidas (ventas):");
			lblSalidas.setForeground(new Color(205, 27, 120));
			lblSalidas.setFont(new Font("Arial", Font.BOLD, 12));
			lblSalidas.setBounds(10, 499, 200, 14);
			contentPanel.add(lblSalidas);
		}
		{
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(10, 524, 439, 73);
			contentPanel.add(scrollPane_2);
			{
				tablaSalidas = new JTable();
				scrollPane_2.setViewportView(tablaSalidas);
			}
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.addActionListener(this);
			btnCerrar.setForeground(Color.WHITE);
			btnCerrar.setFocusPainted(false);
			btnCerrar.setBorderPainted(false);
			btnCerrar.setBackground(new Color(220, 100, 100));
			btnCerrar.setBounds(308, 608, 112, 38);
			contentPanel.add(btnCerrar);
		}
		// Configura columnas y carga datos de la prenda
		configurarTablas();
		cargarDatos(prenda);

	}
	// Configura las columnas de las tres tablas
	private void configurarTablas() {
	    // Tabla de variantes — muestra talla y stock
	    tablaVarianteTalla.setModel(new javax.swing.table.DefaultTableModel(
	        new Object[][]{},
	        new String[]{"TALLA", "STOCK"}
	    ) {
	        public boolean isCellEditable(int r, int c) { return false; }
	    });

	    // Tabla de entradas — muestra proveedor, cantidad y fecha
	    tablaEntradas.setModel(new javax.swing.table.DefaultTableModel(
	        new Object[][]{},
	        new String[]{"PROVEEDOR", "TALLA", "CANTIDAD", "FECHA"}
	    ) {
	        public boolean isCellEditable(int r, int c) { return false; }
	    });

	    // Tabla de salidas — muestra cliente, cantidad y fecha
	    tablaSalidas.setModel(new javax.swing.table.DefaultTableModel(
	        new Object[][]{},
	        new String[]{"CLIENTE", "CANTIDAD", "FECHA"}
	    ) {
	        public boolean isCellEditable(int r, int c) { return false; }
	    });
	}

	// Carga los datos de la prenda en los labels y la tabla de variantes
	private void cargarDatos(modelo.Prenda prenda) {
	    // Muestra el nombre de la prenda
	    lblNombre.setText(prenda.getNombre());
	    // Muestra código, categoría y precio en una línea
	    lblInfo.setText("Código: " + prenda.getCodigo()
	        + "   |   Categoría: " + prenda.getCategoria()
	        + "   |   Precio: S/. " + prenda.getPrecio());
	    // Muestra el stock total calculado desde las variantes
	    lblStock.setText("Stock total: " + prenda.stockPorVariante() + " uds");
	    // Carga imagen si tiene ruta válida
	    if (prenda.getImagen() != null && !prenda.getImagen().isEmpty()) {
	        // Construye la ruta completa desde la carpeta raíz del proyecto
	        // Ejemplo: C:/ProyectoFinal/imagenes/jean.jpg
	        String rutaCompleta = System.getProperty("user.dir") + "/" + prenda.getImagen();
	        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
	            new javax.swing.ImageIcon(rutaCompleta)
	                .getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH));
	        lblImagen.setIcon(icon);
	    }
	 // Tallas fijas — el administrador puede filtrar por cualquier talla
	 // Si esa talla no tiene stock, la tabla mostrará "Sin stock para esta talla"
	 cboFiltroTalla.removeAllItems();
	 cboFiltroTalla.addItem("-- Seleccionar --");
	 cboFiltroTalla.addItem("TODAS");
	 cboFiltroTalla.addItem("XS");
	 cboFiltroTalla.addItem("S");
	 cboFiltroTalla.addItem("M");
	 cboFiltroTalla.addItem("L");
	 cboFiltroTalla.addItem("XL");
	 cboFiltroTalla.addItem("26");
	 cboFiltroTalla.addItem("28");
	 cboFiltroTalla.addItem("30");
	 cboFiltroTalla.addItem("32");
	 cboFiltroTalla.addItem("34");
	 cboFiltroTalla.addItem("ÚNICA");

	    // Filtra cuando cambia la talla seleccionada
	    cboFiltroTalla.addActionListener(ev -> filtrarVariantes(prenda));
	    
	    
	 // Cargamos las entradas de esta prenda en la tabla
	    javax.swing.table.DefaultTableModel mEntradas = 
	        (javax.swing.table.DefaultTableModel) tablaEntradas.getModel();

	    // Limpiamos filas anteriores
	    mEntradas.setRowCount(0);

	    for (modelo.EntradaInventario e : dao.EntradasDAO.listar()) {
	        if (e.getCodigoPrenda().equals(prenda.getCodigo())) {
	            mEntradas.addRow(new Object[]{
	                e.getCodigoProveedor(),
	                e.getTalla(),
	                e.getCantidad(),
	                e.getFecha()
	            });
	        }
	    }
	}

	// Carga una lista de variantes en la tabla
	private void cargarVariantes(java.util.List<modelo.Variante> variantes) {
	    javax.swing.table.DefaultTableModel m =
	        (javax.swing.table.DefaultTableModel) tablaVarianteTalla.getModel();
	    m.setRowCount(0);
	    if (variantes.isEmpty()) {
	        m.addRow(new Object[]{"—", "Sin variantes"});
	    } else {
	        for (modelo.Variante v : variantes) {
	            m.addRow(new Object[]{v.getTalla(), v.getStock()});
	        }
	    }
	}
	
	private void filtrarVariantes(modelo.Prenda prenda) {
	    String talla = (String) cboFiltroTalla.getSelectedItem();
	    if (talla.equals("-- Seleccionar --")) return; // no filtra si no eligió talla
	    java.util.List<modelo.Variante> filtradas = new java.util.ArrayList<>();
	    for (modelo.Variante v : prenda.getVariantes()) {
	        if (talla.equals("TODAS") || v.getTalla().equalsIgnoreCase(talla)) {
	            filtradas.add(v);
	        }
	    }
	    // Si no hay variantes con esa talla muestra mensaje
	    if (filtradas.isEmpty() && !talla.equals("TODAS")) {
	        javax.swing.table.DefaultTableModel m =
	            (javax.swing.table.DefaultTableModel) tablaVarianteTalla.getModel();
	        m.setRowCount(0);
	        // Informa al administrador que no hay stock para esa talla
	        m.addRow(new Object[]{talla, "Sin stock"});
	        return;
	    }
	    cargarVariantes(filtradas);
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
}
