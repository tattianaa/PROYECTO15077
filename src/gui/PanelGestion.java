package gui;

import Formularios.FormEditarPrenda;
import Formularios.FormDetallesPrenda;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import logica.InventarioLogica;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Prenda;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Formularios.FormNuevaPrenda;

public class PanelGestion extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtBuscar;
	private JTable tabladeGestión;
	private JComboBox<String> cboCategoria;
	private InventarioLogica gestor;
	private logica.EntradaLogica entradas;
	private DefaultTableModel modeloTabla;
	private JButton btnBuscar;
	private JButton btnNuevaPrenda;
	private JScrollPane scrollPane;

	/**
	 * Constructor: Crea el panel visual
	 */
	public PanelGestion(InventarioLogica gestor, logica.EntradaLogica entradas) {
		this.gestor = gestor;
		this.entradas = entradas;
		
		setBackground(new Color(245, 242, 225));
		setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 15, 232, 32);
		txtBuscar.setForeground(new Color(150, 150, 150));
		txtBuscar.setText("Ingresa código o nombre de la prenda..");
		add(txtBuscar);
		configurarPlaceholderBuscador();
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(252, 15, 98, 32);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setForeground(new Color(50, 50, 50));
		btnBuscar.setBackground(new Color(220, 190, 195));
		add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Categoría: ");
		lblNewLabel.setBounds(360, 27, 70, 20);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		add(lblNewLabel);
		
		cboCategoria = new JComboBox<>();
		cboCategoria.setModel(new DefaultComboBoxModel<>(new String[] {"TODAS", "PANTALONES & JEANS", "POLOS", "FALDAS & SHORTS", "VESTIDOS", "POLERAS", "CASACAS"}));
		cboCategoria.setBounds(440, 15, 125, 32);
		add(cboCategoria);
		cboCategoria.addActionListener(e -> filtrarPorCategoria());
		
		btnNuevaPrenda = new JButton("+ NUEVA PRENDA");
		btnNuevaPrenda.addActionListener(this);
		btnNuevaPrenda.setForeground(Color.WHITE);
		btnNuevaPrenda.setFocusPainted(false);
		btnNuevaPrenda.setBorderPainted(false);
		btnNuevaPrenda.setBackground(new Color(130, 190, 140));
		btnNuevaPrenda.setBounds(590, 13, 165, 36);
		add(btnNuevaPrenda);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 811, 459);
		add(scrollPane);
		
		modeloTabla = new DefaultTableModel(
			new Object[][]{}, 
			new String[]{"ID", "IMAGEN", "NOMBRE", "STOCK", "PRECIO", "CATEGORÍA", "ACCIONES", "DETALLES"}
		) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col) { return false; }
		};

		tabladeGestión = new JTable(modeloTabla);
		tabladeGestión.setRowHeight(50);
		
		tabladeGestión.getColumnModel().getColumn(1).setCellRenderer(
			new javax.swing.table.DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;
				public java.awt.Component getTableCellRendererComponent(
						JTable t, Object v, boolean sel, boolean foc, int r, int c) {
					JLabel lbl = new JLabel();
					lbl.setHorizontalAlignment(JLabel.CENTER);
					if (v instanceof ImageIcon) lbl.setIcon((ImageIcon) v);
					return lbl;
				}
			});

		scrollPane.setViewportView(tabladeGestión);

		tabladeGestión.getColumnModel().getColumn(6).setCellRenderer(
			(t, v, sel, foc, r, c) -> {
				JPanel p = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 4, 10));
				p.setBackground(Color.WHITE);
				JButton bEditar = new JButton("EDITAR");
				bEditar.setBackground(new Color(220, 190, 195));
				bEditar.setForeground(new Color(50, 50, 50));
				bEditar.setBorderPainted(false);
				bEditar.setFont(new Font("Arial", Font.BOLD, 10));
				
				JButton bEliminar = new JButton("ELIMINAR");
				bEliminar.setBackground(new Color(180, 180, 185));
				bEliminar.setForeground(new Color(50, 50, 50));
				bEliminar.setBorderPainted(false);
				bEliminar.setFont(new Font("Arial", Font.BOLD, 10));
				p.add(bEditar);
				p.add(bEliminar);
				return p;
			});

		tabladeGestión.getColumnModel().getColumn(7).setCellRenderer(
			(t, v, sel, foc, r, c) -> {
				JPanel p = new JPanel(new java.awt.BorderLayout());
				p.setBackground(Color.WHITE);
				p.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
				JButton bDetalle = new JButton("<html><center>VER<br>DETALLES</center></html>");
				bDetalle.setBackground(new Color(100, 160, 220));
				bDetalle.setForeground(Color.WHITE);
				bDetalle.setBorderPainted(false);
				bDetalle.setFont(new Font("Arial", Font.BOLD, 11));
				p.add(bDetalle, java.awt.BorderLayout.CENTER);
				return p;
			});
		
		tabladeGestión.getColumnModel().getColumn(0).setPreferredWidth(70);  
		tabladeGestión.getColumnModel().getColumn(1).setPreferredWidth(65);  
		tabladeGestión.getColumnModel().getColumn(2).setPreferredWidth(140); 
		tabladeGestión.getColumnModel().getColumn(3).setPreferredWidth(55);  
		tabladeGestión.getColumnModel().getColumn(4).setPreferredWidth(75);  
		tabladeGestión.getColumnModel().getColumn(5).setPreferredWidth(130); 
		tabladeGestión.getColumnModel().getColumn(6).setPreferredWidth(200); 
		tabladeGestión.getColumnModel().getColumn(7).setPreferredWidth(110); 
		
		tabladeGestión.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = tabladeGestión.rowAtPoint(e.getPoint()); 
				int col  = tabladeGestión.columnAtPoint(e.getPoint()); 
				if (fila < 0) return; 
				
				if (col == 6) {
					int x = e.getX() - tabladeGestión.getCellRect(fila, col, true).x;
					if (x < 90) {
						modelo.Prenda p = gestor.getPrendas().get(fila);
						FormEditarPrenda dlg = new FormEditarPrenda(fila, p, modeloTabla, gestor);
						dlg.setLocationRelativeTo(null);
						dlg.setVisible(true);
					} else {
						int confirm = JOptionPane.showConfirmDialog(null,
							"¿Eliminar esta prenda?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							String codigo = gestor.getPrendas().get(fila).getCodigo();
							boolean ok = dao.PrendaDAO.eliminar(codigo);
							if (ok) {
								gestor.gestionar(fila);
								modeloTabla.removeRow(fila);
								JOptionPane.showMessageDialog(null, "Prenda eliminada.");
							} else {
								JOptionPane.showMessageDialog(null, "No se pudo eliminar de la base de datos.");
							}
						}
					}
				} else if (col == 7) {
					modelo.Prenda p = gestor.getPrendas().get(fila);
					
					// ==============================================================
					// ──> CAMBIO 1: SE AGREGO ESTE BLOQUE AQUÍ EN DETALLES
					// Carga las variantes directo de MySQL antes de abrir la ventana flotante
					// ==============================================================
					java.util.List<modelo.Variante> frescas = dao.PrendaDAO.ListarVariantes(p.getCodigo());
					p.setVariantes(frescas);
					
					FormDetallesPrenda dlg = new FormDetallesPrenda(p, entradas);
					dlg.setLocationRelativeTo(null);
					dlg.setVisible(true);
				}
			}
		});

		cargarPrendasDesdeDB();
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent e) {
				cargarPrendasDesdeDB();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevaPrenda) {
			do_btnNuevaPrenda_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
	}

	// =========================================================================
	// METODOS LÓGICOS DE CARGA Y FILTRADO (AQUÍ SE MOVIÓ EL MÉTODO FILTRAR)
	// =========================================================================

	private void filtrarPorCategoria() {
		String categoria = cboCategoria.getSelectedItem().toString();
		modeloTabla.setRowCount(0);

		java.util.List<Prenda> listaFiltrada = gestor.gestionar("", categoria);

		for (Prenda p : listaFiltrada) {
			ImageIcon icon = null;
			if (p.getImagen() != null && !p.getImagen().isEmpty()) {
				icon = new ImageIcon(
						new ImageIcon(p.getImagen())
								.getImage()
								.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
			}

			modeloTabla.addRow(new Object[]{
					p.getCodigo(),
					icon,
					p.getNombre(),
					p.getStockTotal(), // ──> CAMBIO 2: Cambiado de p.stockPorVariante() a p.getStockTotal()
					"S/. " + p.getPrecio(),
					p.getCategoria(),
					"",
					""
			});
		}
	}

	private void cargarPrendasDesdeDB() {
		modeloTabla.setRowCount(0);
		gestor.limpiar();
		for (modelo.Prenda p : dao.PrendaDAO.listar()) {
			gestor.agregarPrenda(p);
			javax.swing.ImageIcon icon = null;
			if (p.getImagen() != null && !p.getImagen().isEmpty()) {
				icon = new javax.swing.ImageIcon(
					new javax.swing.ImageIcon(p.getImagen())
						.getImage()
						.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
			}
			modeloTabla.addRow(new Object[]{
				p.getCodigo(),
				icon,
				p.getNombre(),
				p.getStockTotal(), // ──> CAMBIO 3: Cambiado de p.stockPorVariante() a p.getStockTotal()
				"S/. " + p.getPrecio(),
				p.getCategoria()
			});
		}
	}

	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String textoBuscado = txtBuscar.getText().trim();
		if (textoBuscado.equals("Ingresa código o nombre de la prenda..")) {
			textoBuscado = "";
		}
		
		modeloTabla.setRowCount(0);
		String catActiva = cboCategoria.getSelectedItem().toString();
		java.util.List<Prenda> listaFiltrada = gestor.gestionar(textoBuscado, catActiva);
		
		for (Prenda p : listaFiltrada) {
			ImageIcon icon = null;
			if (p.getImagen() != null && !p.getImagen().isEmpty()) {
				icon = new ImageIcon(
					new ImageIcon(p.getImagen())
						.getImage()
						.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
				);
			}

			modeloTabla.addRow(new Object[]{
				p.getCodigo(),
				icon,
				p.getNombre(),
				p.getStockTotal(), // ──> CAMBIO 4: Cambiado de p.stockPorVariante() a p.getStockTotal()
				"S/. " + p.getPrecio(),
				p.getCategoria(),
				"",
				""
			});
		}
	}
	
	protected void do_btnNuevaPrenda_actionPerformed(ActionEvent e) {
		FormNuevaPrenda dialog = new FormNuevaPrenda(modeloTabla, gestor);
		dialog.setVisible(true);
	}

	public void configurarPlaceholderBuscador() {
		txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtBuscar.getText().equals("Ingresa código o nombre de la prenda..")) {
					txtBuscar.setText("");
					txtBuscar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtBuscar.getText().trim().isEmpty()) {
					txtBuscar.setText("Ingresa código o nombre de la prenda..");
					txtBuscar.setForeground(new Color(150, 150, 150));
				}
			}
		});
	
	}
}