package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GestorAdministrativo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gridPanel;
	private JPanel panelPrendas;
	private JPanel panelPedidos;
	private javax.swing.table.DefaultTableModel modeloTabla;
	
	//podrán usar a la "lógica" para borrar o editar.
	private InventarioLogica logica = new InventarioLogica();

	// Lista compartida de prendas
	private java.util.List<Object[]> listaPrendas = new java.util.ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GestorAdministrativo().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestorAdministrativo() {
		setTitle("Proyecto Final - Speakers Moda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 242, 225));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// ===== SIDEBAR =====
		JPanel sidebar = new JPanel();
		sidebar.setBackground(Color.WHITE);
		sidebar.setLayout(null);
		sidebar.setBounds(0, 0, 200, 650);
		contentPane.add(sidebar);

		JLabel lblLogo = new JLabel("🔊 SPEAKERS");
		lblLogo.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblLogo.setBounds(15, 15, 175, 40);
		sidebar.add(lblLogo);

		JSeparator sep = new JSeparator();
		sep.setBounds(10, 58, 180, 2);
		sidebar.add(sep);

		JButton btnInicio = new JButton("🏠  Inicio");
		btnInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		btnInicio.setForeground(new Color(60, 60, 60));
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBorderPainted(false);
		btnInicio.setFocusPainted(false);
		btnInicio.setOpaque(true);
		btnInicio.setHorizontalAlignment(SwingConstants.LEFT);
		btnInicio.setBounds(5, 65, 190, 30);
		sidebar.add(btnInicio);

		JButton btnNewDrop = new JButton("💋  NEW DROP");
		btnNewDrop.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewDrop.setForeground(new Color(60, 60, 60));
		btnNewDrop.setBackground(Color.WHITE);
		btnNewDrop.setBorderPainted(false);
		btnNewDrop.setFocusPainted(false);
		btnNewDrop.setOpaque(true);
		btnNewDrop.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewDrop.setBounds(5, 100, 190, 30);
		sidebar.add(btnNewDrop);

		JButton btnColecciones = new JButton("✨  COLECCIONES");
		btnColecciones.setFont(new Font("Arial", Font.PLAIN, 12));
		btnColecciones.setForeground(new Color(60, 60, 60));
		btnColecciones.setBackground(Color.WHITE);
		btnColecciones.setBorderPainted(false);
		btnColecciones.setFocusPainted(false);
		btnColecciones.setOpaque(true);
		btnColecciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnColecciones.setBounds(5, 135, 190, 30);
		sidebar.add(btnColecciones);

		JButton btnPantalones = new JButton("👖  PANTALONES & JEANS");
		btnPantalones.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPantalones.setForeground(new Color(60, 60, 60));
		btnPantalones.setBackground(Color.WHITE);
		btnPantalones.setBorderPainted(false);
		btnPantalones.setFocusPainted(false);
		btnPantalones.setOpaque(true);
		btnPantalones.setHorizontalAlignment(SwingConstants.LEFT);
		btnPantalones.setBounds(5, 170, 190, 30);
		sidebar.add(btnPantalones);

		JButton btnTops = new JButton("👕  TOPS & BODIES");
		btnTops.setFont(new Font("Arial", Font.PLAIN, 12));
		btnTops.setForeground(new Color(60, 60, 60));
		btnTops.setBackground(Color.WHITE);
		btnTops.setBorderPainted(false);
		btnTops.setFocusPainted(false);
		btnTops.setOpaque(true);
		btnTops.setHorizontalAlignment(SwingConstants.LEFT);
		btnTops.setBounds(5, 205, 190, 30);
		sidebar.add(btnTops);

		JButton btnFaldas = new JButton("👗  FALDAS & SHORTS");
		btnFaldas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFaldas.setForeground(new Color(60, 60, 60));
		btnFaldas.setBackground(Color.WHITE);
		btnFaldas.setBorderPainted(false);
		btnFaldas.setFocusPainted(false);
		btnFaldas.setOpaque(true);
		btnFaldas.setHorizontalAlignment(SwingConstants.LEFT);
		btnFaldas.setBounds(5, 240, 190, 30);
		sidebar.add(btnFaldas);

		JButton btnVestidos = new JButton("👗  VESTIDOS");
		btnVestidos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVestidos.setForeground(new Color(60, 60, 60));
		btnVestidos.setBackground(Color.WHITE);
		btnVestidos.setBorderPainted(false);
		btnVestidos.setFocusPainted(false);
		btnVestidos.setOpaque(true);
		btnVestidos.setHorizontalAlignment(SwingConstants.LEFT);
		btnVestidos.setBounds(5, 275, 190, 30);
		sidebar.add(btnVestidos);

		JButton btnAccesorios = new JButton("💍  ACCESORIOS");
		btnAccesorios.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAccesorios.setForeground(new Color(60, 60, 60));
		btnAccesorios.setBackground(Color.WHITE);
		btnAccesorios.setBorderPainted(false);
		btnAccesorios.setFocusPainted(false);
		btnAccesorios.setOpaque(true);
		btnAccesorios.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccesorios.setBounds(5, 310, 190, 30);
		sidebar.add(btnAccesorios);

		JButton btnPedidos = new JButton("📦  GESTIÓN DE PEDIDOS");
		btnPedidos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPedidos.setForeground(new Color(60, 60, 60));
		btnPedidos.setBackground(Color.WHITE);
		btnPedidos.setBorderPainted(false);
		btnPedidos.setFocusPainted(false);
		btnPedidos.setOpaque(true);
		btnPedidos.setHorizontalAlignment(SwingConstants.LEFT);
		btnPedidos.setBounds(5, 345, 190, 30);
		sidebar.add(btnPedidos);

		JButton btnConfig = new JButton("⚙️  Configuración");
		btnConfig.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfig.setForeground(new Color(60, 60, 60));
		btnConfig.setBackground(Color.WHITE);
		btnConfig.setBorderPainted(false);
		btnConfig.setFocusPainted(false);
		btnConfig.setOpaque(true);
		btnConfig.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfig.setBounds(5, 380, 190, 30);
		sidebar.add(btnConfig);

		JButton btnCerrar = new JButton("🚪  Cerrar Sesión");
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCerrar.setForeground(new Color(60, 60, 60));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setFocusPainted(false);
		btnCerrar.setOpaque(true);
		btnCerrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrar.setBounds(5, 415, 190, 30);
		sidebar.add(btnCerrar);

		JButton btnPanelGestion = new JButton("📋  Panel de Gestión");
		btnPanelGestion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPanelGestion.setForeground(new Color(60, 60, 60));
		btnPanelGestion.setBackground(Color.WHITE);
		btnPanelGestion.setBorderPainted(false);
		btnPanelGestion.setFocusPainted(false);
		btnPanelGestion.setOpaque(true);
		btnPanelGestion.setHorizontalAlignment(SwingConstants.LEFT);
		btnPanelGestion.setBounds(5, 450, 190, 30);
		sidebar.add(btnPanelGestion);

		// ===== PANEL PRINCIPAL con CardLayout =====
		JPanel mainPanel = new JPanel(new CardLayout());
		mainPanel.setBounds(202, 0, 798, 650);
		contentPane.add(mainPanel);

		// --- Panel Prendas ---
		panelPrendas = new JPanel(null);
		panelPrendas.setBackground(new Color(245, 242, 225));

		JTextField txtBuscar = new JTextField("Buscar...");
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 13));
		txtBuscar.setForeground(new Color(150, 150, 150));
		txtBuscar.setBackground(Color.WHITE);
		txtBuscar.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		txtBuscar.setBounds(10, 15, 280, 32);
		panelPrendas.add(txtBuscar);

		JButton btnNueva = new JButton("+ NUEVA PRENDA");
		btnNueva.setFont(new Font("Arial", Font.BOLD, 13));
		btnNueva.setBackground(new Color(130, 190, 140));
		btnNueva.setForeground(Color.WHITE);
		btnNueva.setBorderPainted(false);
		btnNueva.setFocusPainted(false);
		btnNueva.setOpaque(true);
		btnNueva.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNueva.setBounds(620, 12, 165, 36);
		panelPrendas.add(btnNueva);

		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
		gridPanel.setBackground(new Color(245, 242, 225));
		JScrollPane scroll = new JScrollPane(gridPanel);
		scroll.setBounds(10, 60, 778, 575);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(new Color(245, 242, 225));
		panelPrendas.add(scroll);

		// --- Panel Pedidos ---
		panelPedidos = new JPanel(null);
		panelPedidos.setBackground(new Color(245, 242, 225));

		JTextField txtIdPedido = new JTextField("ID Pedido");
		txtIdPedido.setFont(new Font("Arial", Font.PLAIN, 13));
		txtIdPedido.setForeground(new Color(150, 150, 150));
		txtIdPedido.setBackground(Color.WHITE);
		txtIdPedido.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		txtIdPedido.setBounds(10, 15, 220, 32);
		panelPedidos.add(txtIdPedido);

		JTextField txtIdCliente = new JTextField("ID/Cliente");
		txtIdCliente.setFont(new Font("Arial", Font.PLAIN, 13));
		txtIdCliente.setForeground(new Color(150, 150, 150));
		txtIdCliente.setBackground(Color.WHITE);
		txtIdCliente.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		txtIdCliente.setBounds(240, 15, 220, 32);
		panelPedidos.add(txtIdCliente);

		// Tabla de pedidos
		String[] columnas = {"ID Pedido", "Fecha", "Cliente", "Total", "Estado"};
		Object[][] datos = {
			{"#101", "05/04/2026", "Juan Pérez", "S/. 152.10", "Pendiente"},
			{"#102", "05/04/2026", "Juan Pérez", "S/. 152.10", "Enviado"},
			{"#103", "05/04/2026", "Juan Pérez", "S/. 152.10", "Entregado"}
		};
		JTable tabla = new JTable(datos, columnas);
		tabla.setRowHeight(28);
		tabla.setFont(new Font("Arial", Font.PLAIN, 12));
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tabla.setBackground(Color.WHITE);
		tabla.setGridColor(new Color(220, 220, 220));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		scrollTabla.setBounds(10, 60, 580, 400);
		scrollTabla.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelPedidos.add(scrollTabla);

		// Botones accion
		JButton btnVerDetalles = new JButton("VER DETALLES");
		btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerDetalles.setBackground(new Color(220, 190, 195));
		btnVerDetalles.setForeground(new Color(50, 50, 50));
		btnVerDetalles.setBorderPainted(false);
		btnVerDetalles.setFocusPainted(false);
		btnVerDetalles.setOpaque(true);
		btnVerDetalles.setBounds(605, 60, 170, 36);
		panelPedidos.add(btnVerDetalles);

		JButton btnActualizar = new JButton("ACTUALIZAR ESTADO");
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
		btnActualizar.setBackground(new Color(220, 190, 195));
		btnActualizar.setForeground(new Color(50, 50, 50));
		btnActualizar.setBorderPainted(false);
		btnActualizar.setFocusPainted(false);
		btnActualizar.setOpaque(true);
		btnActualizar.setBounds(605, 110, 170, 36);
		panelPedidos.add(btnActualizar);

		JButton btnReporte = new JButton("GENERAR REPORTE");
		btnReporte.setFont(new Font("Arial", Font.BOLD, 12));
		btnReporte.setBackground(new Color(220, 190, 195));
		btnReporte.setForeground(new Color(50, 50, 50));
		btnReporte.setBorderPainted(false);
		btnReporte.setFocusPainted(false);
		btnReporte.setOpaque(true);
		btnReporte.setBounds(605, 160, 170, 36);
		panelPedidos.add(btnReporte);

		mainPanel.add(panelPrendas, "prendas");
		mainPanel.add(panelPedidos, "pedidos");

		// --- Panel Gestion (tabla) ---
		JPanel panelGestion = new JPanel(null);
		panelGestion.setBackground(new Color(245, 242, 225));

		JTextField txtBuscarG = new JTextField("Buscar");
		txtBuscarG.setFont(new Font("Arial", Font.PLAIN, 13));
		txtBuscarG.setBackground(Color.WHITE);
		txtBuscarG.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		txtBuscarG.setBounds(10, 15, 200, 32);
		panelGestion.add(txtBuscarG);

		JButton btnBuscarG = new JButton("Buscar");
		btnBuscarG.setBackground(new Color(220, 190, 195));
		btnBuscarG.setBorderPainted(false);
		btnBuscarG.setFocusPainted(false);
		btnBuscarG.setOpaque(true);
		btnBuscarG.setBounds(215, 15, 80, 32);
		panelGestion.add(btnBuscarG);

		JLabel lblCat = new JLabel("Categoría:");
		lblCat.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCat.setBounds(310, 20, 70, 20);
		panelGestion.add(lblCat);

		String[] cats = {"TODAS", "NEW DROP", "COLECCIONES", "PANTALONES & JEANS", "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
		JComboBox<String> comboCatG = new JComboBox<>(cats);
		comboCatG.setBounds(385, 15, 180, 32);
		panelGestion.add(comboCatG);

		JButton btnNuevaG = new JButton("+ NUEVA PRENDA");
		btnNuevaG.setFont(new Font("Arial", Font.BOLD, 12));
		btnNuevaG.setBackground(new Color(130, 190, 140));
		btnNuevaG.setForeground(Color.WHITE);
		btnNuevaG.setBorderPainted(false);
		btnNuevaG.setFocusPainted(false);
		btnNuevaG.setOpaque(true);
		btnNuevaG.setBounds(600, 12, 165, 36);
		panelGestion.add(btnNuevaG);

		// Tabla
		String[] colsG = {"ID", "IMAGEN", "NOMBRE", "STOCK", "PRECIO", "CATEGORÍA", "ACCIONES"};
		modeloTabla = new javax.swing.table.DefaultTableModel(colsG, 0) {
			public boolean isCellEditable(int row, int col) { return false; }
			public Class<?> getColumnClass(int col) {
				if (col == 1) return ImageIcon.class;
				return String.class;
			}
		};
		JTable tablaGestion = new JTable(modeloTabla);
		tablaGestion.setRowHeight(55);
		tablaGestion.setFont(new Font("Arial", Font.PLAIN, 12));
		tablaGestion.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tablaGestion.getTableHeader().setBackground(new Color(240, 235, 225));
		tablaGestion.setBackground(Color.WHITE);
		tablaGestion.setGridColor(new Color(220, 220, 220));
		tablaGestion.setSelectionBackground(new Color(220, 190, 195));

		// Renderer para imagen
		tablaGestion.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			public java.awt.Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int r, int c) {
				JLabel lbl = new JLabel();
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				if (v instanceof ImageIcon) lbl.setIcon((ImageIcon) v);
				return lbl;
			}
		});

		// Renderer para botones EDITAR/ELIMINAR
		tablaGestion.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.TableCellRenderer() {
			public java.awt.Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int r, int c) {
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 10));
				p.setBackground(Color.WHITE);
				JButton bE = new JButton("EDITAR");
				bE.setBackground(new Color(220, 190, 195));
				bE.setForeground(new Color(50,50,50));
				bE.setBorderPainted(false);
				bE.setFont(new Font("Arial", Font.BOLD, 10));
				JButton bEl = new JButton("ELIMINAR");
				bEl.setBackground(new Color(180, 180, 185));
				bEl.setForeground(new Color(50,50,50));
				bEl.setBorderPainted(false);
				bEl.setFont(new Font("Arial", Font.BOLD, 10));
				p.add(bE); p.add(bEl);
				return p;
			}
		});

		// Ancho columnas
		tablaGestion.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaGestion.getColumnModel().getColumn(1).setPreferredWidth(70);
		tablaGestion.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablaGestion.getColumnModel().getColumn(3).setPreferredWidth(60);
		tablaGestion.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablaGestion.getColumnModel().getColumn(5).setPreferredWidth(140);
		tablaGestion.getColumnModel().getColumn(6).setPreferredWidth(160);

		// Click en botones de la tabla
		tablaGestion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tablaGestion.rowAtPoint(e.getPoint());
				int col = tablaGestion.columnAtPoint(e.getPoint());
				if (col == 6 && row >= 0) {
					int x = e.getX() - tablaGestion.getCellRect(row, col, true).x;
					if (x < 80) {
						// EDITAR
						String nombreActual = (String) modeloTabla.getValueAt(row, 2);
						String stockActual = (String) modeloTabla.getValueAt(row, 3);
						String precioActual = ((String) modeloTabla.getValueAt(row, 4)).replace("S/. ", "");
						String idActual = (String) modeloTabla.getValueAt(row, 0);
						String catActual = (String) modeloTabla.getValueAt(row, 5);

						JDialog dlg = new JDialog(GestorAdministrativo.this, "Editar Prenda", true);
						dlg.setSize(400, 420);
						dlg.setLocationRelativeTo(GestorAdministrativo.this);
						dlg.setResizable(false);

						JPanel p = new JPanel(null);
						p.setBackground(Color.WHITE);
						dlg.setContentPane(p);

						JLabel lN = new JLabel("Nombre:");
						lN.setBounds(20, 20, 100, 20);
						p.add(lN);
						JTextField tNombre = new JTextField(nombreActual);
						tNombre.setBounds(20, 42, 350, 30);
						p.add(tNombre);

						JLabel lP = new JLabel("Precio:");
						lP.setBounds(20, 80, 100, 20);
						p.add(lP);
						JTextField tPrecio = new JTextField(precioActual);
						tPrecio.setBounds(20, 102, 160, 30);
						p.add(tPrecio);

						JLabel lS = new JLabel("Stock:");
						lS.setBounds(200, 80, 100, 20);
						p.add(lS);
						JTextField tStock = new JTextField(stockActual);
						tStock.setBounds(200, 102, 170, 30);
						p.add(tStock);

						JLabel lId = new JLabel("ID:");
						lId.setBounds(20, 140, 100, 20);
						p.add(lId);
						JTextField tId = new JTextField(idActual);
						tId.setEnabled(false);
						tId.setBackground(new Color(230, 230, 230));
						tId.setBounds(20, 162, 350, 30);
						p.add(tId);

						JLabel lCat = new JLabel("Categoría:");
						lCat.setBounds(20, 200, 100, 20);
						p.add(lCat);
						String[] categorias = {"NEW DROP", "COLECCIONES", "PANTALONES & JEANS", "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
						JComboBox<String> combo = new JComboBox<>(categorias);
						combo.setSelectedItem(catActual);
						combo.setBounds(20, 222, 350, 30);
						p.add(combo);

						final File[] nuevaImg = {null};
						JButton btnCambiarImg = new JButton("Cambiar Imagen");
						btnCambiarImg.setBounds(20, 265, 150, 28);
						btnCambiarImg.setFocusPainted(false);
						btnCambiarImg.addActionListener(ev -> {
							JFileChooser fc = new JFileChooser();
							fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
							if (fc.showOpenDialog(dlg) == JFileChooser.APPROVE_OPTION) {
								nuevaImg[0] = fc.getSelectedFile();
							}
						});
						p.add(btnCambiarImg);

						JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
						btnGuardar.setBackground(new Color(130, 190, 140));
						btnGuardar.setForeground(Color.WHITE);
						btnGuardar.setBorderPainted(false);
						btnGuardar.setFocusPainted(false);
						btnGuardar.setOpaque(true);
						btnGuardar.setBounds(20, 310, 170, 36);
						p.add(btnGuardar);

						JButton btnCancel = new JButton("CANCELAR");
						btnCancel.setBackground(new Color(220, 100, 100));
						btnCancel.setForeground(Color.WHITE);
						btnCancel.setBorderPainted(false);
						btnCancel.setFocusPainted(false);
						btnCancel.setOpaque(true);
						btnCancel.setBounds(210, 310, 160, 36);
						p.add(btnCancel);

						btnCancel.addActionListener(ev -> dlg.dispose());
						btnGuardar.addActionListener(ev -> {
							String nombre = tNombre.getText().trim();
							String precio = tPrecio.getText().trim();
							String stock = tStock.getText().trim();
							String categoria = combo.getSelectedItem().toString();
							String codigo = tId.getText().trim();

							logica.editarPrenda(row, modeloTabla, codigo, nombre, stock, precio, categoria);
							// Sincronizar con la tarjeta del grid
							if (row < gridPanel.getComponentCount()) {
								JPanel card = (JPanel) gridPanel.getComponent(row);
								((JLabel) card.getComponent(1)).setText(tNombre.getText().trim());
								((JLabel) card.getComponent(2)).setText("S/. " + tPrecio.getText().trim());
								String id2 = ((JLabel) card.getComponent(3)).getText();
								id2 = id2.contains("ID:") ? id2.split("ID:")[1].split(" ")[1].trim() : "";
								((JLabel) card.getComponent(3)).setText("ID: " + id2 + "   Stock: " + tStock.getText().trim());
								if (nuevaImg[0] != null) {
									try {
										ImageIcon ic = new ImageIcon(nuevaImg[0].toURI().toURL());
										MediaTracker mt = new MediaTracker(dlg);
										mt.addImage(ic.getImage(), 0);
										mt.waitForAll();
										modeloTabla.setValueAt(new ImageIcon(ic.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)), row, 1);
										((JLabel) card.getComponent(0)).setIcon(new ImageIcon(ic.getImage().getScaledInstance(183, 140, Image.SCALE_SMOOTH)));
									} catch (Exception ex) { ex.printStackTrace(); }
								}
								card.repaint();
							}
							dlg.dispose();
						});

						dlg.setVisible(true);
					} else {
						// ELIMINAR
						int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta prenda?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							logica.eliminarPrenda(row, modeloTabla, gridPanel);
							}						
					}
				}
			}
		});

		JScrollPane scrollG = new JScrollPane(tablaGestion);
		scrollG.setBounds(10, 60, 778, 570);
		scrollG.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelGestion.add(scrollG);

		mainPanel.add(panelGestion, "gestion");

		CardLayout cl = (CardLayout) mainPanel.getLayout();
		cl.show(mainPanel, "prendas");

		btnNueva.addActionListener(e -> mostrarFormulario());
		btnNuevaG.addActionListener(e -> mostrarFormulario());
		btnPedidos.addActionListener(e -> cl.show(mainPanel, "pedidos"));
		btnInicio.addActionListener(e -> cl.show(mainPanel, "prendas"));
		btnPanelGestion.addActionListener(e -> cl.show(mainPanel, "gestion"));
	}

	private void mostrarFormulario() {
		JDialog dialog = new JDialog(this, "Nueva Prenda", true);
		dialog.setSize(400, 450);
		dialog.setLocationRelativeTo(this);
		dialog.setResizable(false);

		JPanel panel = new JPanel(null);
		panel.setBackground(Color.WHITE);
		dialog.setContentPane(panel);

		JLabel lNombre = new JLabel("Nombre:");
		lNombre.setBounds(20, 20, 100, 20);
		panel.add(lNombre);
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(20, 42, 350, 30);
		panel.add(txtNombre);
		

		JLabel lCodigo = new JLabel("Código:");
		lCodigo.setBounds(20, 80, 100, 20);
		panel.add(lCodigo);
		JTextField txtCodigo = new JTextField();
		txtCodigo.setBounds(20, 102, 350, 30);
		panel.add(txtCodigo);
		
		//mientras escribe se convierta a mayusculas
		txtNombre.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
		        txtNombre.setText(txtNombre.getText().toUpperCase());
		    }
		});

		txtCodigo.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
		        txtCodigo.setText(txtCodigo.getText().toUpperCase());
		    }
		});

		JLabel lPrecio = new JLabel("Precio:");
		lPrecio.setBounds(20, 140, 100, 20);
		panel.add(lPrecio);
		JTextField txtPrecio = new JTextField();
		txtPrecio.setBounds(20, 162, 160, 30);
		panel.add(txtPrecio);

		JLabel lStock = new JLabel("Stock:");
		lStock.setBounds(200, 140, 100, 20);
		panel.add(lStock);
		JTextField txtStock = new JTextField();
		txtStock.setBounds(200, 162, 170, 30);
		panel.add(txtStock);

		JLabel lCat = new JLabel("Categoría:");
		lCat.setBounds(20, 200, 100, 20);
		panel.add(lCat);
		String[] categorias = {"NEW DROP", "COLECCIONES", "PANTALONES & JEANS", "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
		JComboBox<String> comboCat = new JComboBox<>(categorias);
		comboCat.setBounds(20, 222, 350, 30);
		panel.add(comboCat);

		JLabel lImg = new JLabel("Imagen:");
		lImg.setBounds(20, 262, 100, 20);
		panel.add(lImg);
		JLabel lblRuta = new JLabel("Sin imagen seleccionada");
		lblRuta.setFont(new Font("Arial", Font.ITALIC, 11));
		lblRuta.setForeground(new Color(150, 150, 150));
		lblRuta.setBounds(20, 284, 230, 20);
		panel.add(lblRuta);

		final File[] imagenSeleccionada = {null};
		JButton btnImg = new JButton("Seleccionar");
		btnImg.setBounds(260, 280, 110, 26);
		btnImg.setFocusPainted(false);
		btnImg.addActionListener(e -> {
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
			if (fc.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
				imagenSeleccionada[0] = fc.getSelectedFile();
				lblRuta.setText(fc.getSelectedFile().getName());
			}
		});
		panel.add(btnImg);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBackground(new Color(130, 190, 140));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setOpaque(true);
		btnGuardar.setBounds(20, 340, 160, 36);
		panel.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBackground(new Color(220, 100, 100));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setOpaque(true);
		btnCancelar.setBounds(210, 340, 160, 36);
		panel.add(btnCancelar);

		btnCancelar.addActionListener(e -> dialog.dispose());

		btnGuardar.addActionListener(e -> {
			String nombre = txtNombre.getText().trim().toUpperCase();
			String codigo = txtCodigo.getText().trim().toUpperCase();
			String precio = txtPrecio.getText().trim();
			String stock = txtStock.getText().trim();
			String categoria = (String) comboCat.getSelectedItem();
			// VALIDAR 
			String resultado = logica.validarTodo(nombre, codigo, precio, stock, modeloTabla);

			if (!resultado.equals("OK")) {
			    JOptionPane.showMessageDialog(dialog, resultado);
			    return;
			}
			// Imagen para la tabla
			ImageIcon iconTabla = null;
			if (imagenSeleccionada[0] != null) {
				try {
					ImageIcon ic = new ImageIcon(imagenSeleccionada[0].toURI().toURL());
					MediaTracker mt = new MediaTracker(dialog);
					mt.addImage(ic.getImage(), 0);
					mt.waitForAll();
					iconTabla = new ImageIcon(ic.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
				} catch (Exception ex) { ex.printStackTrace(); }
			}
			// Agregar tarjeta al grid
			final int indice = modeloTabla.getRowCount(); // índice antes de agregar
			gridPanel.add(crearTarjeta(nombre, precio, codigo, stock, imagenSeleccionada[0], indice));
			gridPanel.revalidate();
			gridPanel.repaint();
			// Agregar fila a la tabla de gestión
			modeloTabla.addRow(new Object[]{codigo, iconTabla, nombre, stock, "S/. " + precio, categoria, ""});
			dialog.dispose();
		});

		dialog.setVisible(true);
	}

	private JPanel crearTarjeta(String nombre, String precio, String codigo, String stock, File imagen, int filaTabla) {
		JPanel card = new JPanel(null);
		card.setBackground(Color.WHITE);
		card.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
		card.setPreferredSize(new Dimension(185, 235));

		JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
		imgLabel.setBackground(new Color(230, 230, 230));
		imgLabel.setOpaque(true);
		if (imagen != null) {
			try {
				ImageIcon icon = new ImageIcon(imagen.toURI().toURL());
				MediaTracker tracker = new MediaTracker(imgLabel);
				tracker.addImage(icon.getImage(), 0);
				tracker.waitForAll();
				imgLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(183, 140, Image.SCALE_SMOOTH)));
			} catch (Exception ex) { ex.printStackTrace(); }
		}
		imgLabel.setBounds(1, 1, 183, 140);
		card.add(imgLabel);

		JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombre.setBounds(5, 145, 175, 18);
		card.add(lblNombre);

		JLabel lblPrecio = new JLabel("S/. " + precio, SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPrecio.setForeground(new Color(80, 80, 80));
		lblPrecio.setBounds(5, 163, 175, 16);
		card.add(lblPrecio);

		JLabel lblInfo = new JLabel("ID: " + codigo + "   Stock: " + stock, SwingConstants.CENTER);
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 10));
		lblInfo.setForeground(new Color(130, 130, 130));
		lblInfo.setBounds(5, 179, 175, 14);
		card.add(lblInfo);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 10));
		btnEditar.setBackground(new Color(100, 160, 220));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBorderPainted(false);
		btnEditar.setFocusPainted(false);
		btnEditar.setOpaque(true);
		btnEditar.setBounds(5, 200, 82, 24);
		card.add(btnEditar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 10));
		btnEliminar.setBackground(new Color(220, 100, 100));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setOpaque(true);
		btnEliminar.setBounds(95, 200, 85, 24);
		card.add(btnEliminar);

		btnEliminar.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(card, 
				"¿Deseas eliminar esta prenda?", "Confirmar", 
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				int idx = gridPanel.getComponentZOrder(card);
				logica.eliminarPrenda(idx, modeloTabla, gridPanel);
				}
		});

		btnEditar.addActionListener(e -> {
			JDialog dlg = new JDialog(this, "Editar Prenda", true);
			dlg.setSize(400, 420);
			dlg.setLocationRelativeTo(this);
			dlg.setResizable(false);

			JPanel p = new JPanel(null);
			p.setBackground(Color.WHITE);
			dlg.setContentPane(p);

			JLabel lN = new JLabel("Nombre:");
			lN.setBounds(20, 20, 100, 20);
			p.add(lN);
			JTextField tNombre = new JTextField(lblNombre.getText());
			tNombre.setBounds(20, 42, 350, 30);
			p.add(tNombre);

			JLabel lP = new JLabel("Precio:");
			lP.setBounds(20, 80, 100, 20);
			p.add(lP);
			JTextField tPrecio = new JTextField(lblPrecio.getText().replace("S/. ", ""));
			tPrecio.setBounds(20, 102, 160, 30);
			p.add(tPrecio);

			JLabel lS = new JLabel("Stock:");
			lS.setBounds(200, 80, 100, 20);
			p.add(lS);
			JTextField tStock = new JTextField(lblInfo.getText().contains("Stock:") ? lblInfo.getText().split("Stock:")[1].trim() : "");
			tStock.setBounds(200, 102, 170, 30);
			p.add(tStock);

			JLabel lId = new JLabel("ID:");
			lId.setBounds(20, 140, 100, 20);
			p.add(lId);
			JTextField tId = new JTextField(lblInfo.getText().contains("ID:") ? lblInfo.getText().split("ID:")[1].split(" ")[1].trim() : "");
			tId.setEnabled(false);
			tId.setBackground(new Color(230, 230, 230));
			tId.setBounds(20, 162, 350, 30);
			p.add(tId);

			JLabel lCat = new JLabel("Categoría:");
			lCat.setBounds(20, 200, 100, 20);
			p.add(lCat);
			String[] categorias = {"NEW DROP", "COLECCIONES", "PANTALONES & JEANS", "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
			JComboBox<String> combo = new JComboBox<>(categorias);
			combo.setBounds(20, 222, 350, 30);
			p.add(combo);

			final File[] nuevaImg = {imagen};
			JButton btnCambiarImg = new JButton("Cambiar Imagen");
			btnCambiarImg.setBounds(20, 265, 150, 28);
			btnCambiarImg.setFocusPainted(false);
			btnCambiarImg.addActionListener(ev -> {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
				if (fc.showOpenDialog(dlg) == JFileChooser.APPROVE_OPTION) {
					nuevaImg[0] = fc.getSelectedFile();
					try {
						ImageIcon ic = new ImageIcon(nuevaImg[0].toURI().toURL());
						MediaTracker mt = new MediaTracker(imgLabel);
						mt.addImage(ic.getImage(), 0);
						mt.waitForAll();
						imgLabel.setIcon(new ImageIcon(ic.getImage().getScaledInstance(183, 140, Image.SCALE_SMOOTH)));
					} catch (Exception ex) { ex.printStackTrace(); }
				}
			});
			p.add(btnCambiarImg);

			JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
			btnGuardar.setBackground(new Color(130, 190, 140));
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setBorderPainted(false);
			btnGuardar.setFocusPainted(false);
			btnGuardar.setOpaque(true);
			btnGuardar.setBounds(20, 310, 170, 36);
			p.add(btnGuardar);

			JButton btnCancel = new JButton("CANCELAR");
			btnCancel.setBackground(new Color(220, 100, 100));
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBorderPainted(false);
			btnCancel.setFocusPainted(false);
			btnCancel.setOpaque(true);
			btnCancel.setBounds(210, 310, 160, 36);
			p.add(btnCancel);

			btnCancel.addActionListener(ev -> dlg.dispose());
			btnGuardar.addActionListener(ev -> {
				lblNombre.setText(tNombre.getText().trim());
				lblPrecio.setText("S/. " + tPrecio.getText().trim());
				String id = lblInfo.getText().contains("ID:") ? lblInfo.getText().split("ID:")[1].split(" ")[1].trim() : "";
				lblInfo.setText("ID: " + id + "   Stock: " + tStock.getText().trim());
				// Sincronizar con la tabla
				int idx = gridPanel.getComponentZOrder(card);
				if (idx < modeloTabla.getRowCount()) {
					modeloTabla.setValueAt(tNombre.getText().trim(), idx, 2);
					modeloTabla.setValueAt(tStock.getText().trim(), idx, 3);
					modeloTabla.setValueAt("S/. " + tPrecio.getText().trim(), idx, 4);
					modeloTabla.setValueAt(combo.getSelectedItem(), idx, 5);
					if (nuevaImg[0] != null) {
						try {
							ImageIcon ic = new ImageIcon(nuevaImg[0].toURI().toURL());
							MediaTracker mt = new MediaTracker(dlg);
							mt.addImage(ic.getImage(), 0);
							mt.waitForAll();
							ImageIcon iconPeq = new ImageIcon(ic.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
							modeloTabla.setValueAt(iconPeq, idx, 1);
							imgLabel.setIcon(new ImageIcon(ic.getImage().getScaledInstance(183, 140, Image.SCALE_SMOOTH)));
						} catch (Exception ex) { ex.printStackTrace(); }
					}
				}
				card.repaint();
				dlg.dispose();
			});

			dlg.setVisible(true);
		});

		return card;
	}
}
