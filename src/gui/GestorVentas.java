package gui;

import logica.InventarioLogica;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestorVentas extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtBuscador;
	private JTable tablaProductosCatalogo;
	private JTable tablaCarrito;
	private DefaultTableModel modeloCatalogo;
	private DefaultTableModel modeloCarrito;
	private JLabel lblMontoTotal;
	private JButton btnCatTodas;
	private InventarioLogica gestor;
	private JButton btnCatPantalones;
	private JButton btnCatPolos;
	private JButton btnCatFaldas;
	private JButton btnCatVestidos;
	private JButton btnCatPoleras;
	private JButton btnCatCasacas;
	private double totalAcumulado = 0.0;
	private String categoriaSeleccionada = "TODAS";
	private JButton btnBuscar;
	private JButton btnAgregarCarrito;
	private JButton btnEliminarArticulo;
	
	private JLabel lblOpGravadaNum; 
	private JLabel lblIgvNum;       
	private JButton btnProcesarPago;

	/**
	 * Create the panel.
	 */ 
	public GestorVentas(logica.InventarioLogica gestor) {
		this.gestor = gestor;
		setBackground(new Color(245, 242, 225)); 
		setSize(842, 625);
		setLayout(null);
		
		// =========================================================================
		// 1. COMPONENTE SUPERIOR: BUSCADOR DE PRENDAS
		// =========================================================================
		JPanel panelBuscador = new JPanel();
		panelBuscador.setBackground(Color.WHITE);
		panelBuscador.setBounds(10, 11, 822, 50);
		panelBuscador.setBorder(new LineBorder(new Color(220, 220, 220)));
		add(panelBuscador);
		panelBuscador.setLayout(null);
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Ingresa código o nombre de la prenda...");
		txtBuscador.setForeground(Color.GRAY);
		txtBuscador.setFont(new Font("Arial", Font.PLAIN, 13));
		txtBuscador.setBounds(15, 10, 680, 30);
		txtBuscador.setBorder(new LineBorder(new Color(200, 200, 200)));
		panelBuscador.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		txtBuscador.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtBuscador.getText().equals("Ingresa código o nombre de la prenda...")) {
					txtBuscador.setText("");
					txtBuscador.setForeground(Color.BLACK); 
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtBuscador.getText().trim().isEmpty()) {
					txtBuscador.setText("Ingresa código o nombre de la prenda...");
					txtBuscador.setForeground(Color.GRAY); 
				}
			}
		});
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setBackground(new Color(219, 178, 185)); 
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		btnBuscar.setBounds(705, 9, 105, 31);
		btnBuscar.setFocusable(false);
		btnBuscar.setBorder(new LineBorder(new Color(200, 200, 200)));
		panelBuscador.add(btnBuscar);

		// =========================================================================
		// 2. COMPONENTE IZQUIERDO: CATEGORÍAS
		// =========================================================================
		JPanel panelCategorias = new JPanel();
		panelCategorias.setBackground(Color.WHITE);
		panelCategorias.setBounds(10, 72, 180, 547);
		panelCategorias.setBorder(new LineBorder(new Color(220, 220, 220)));
		add(panelCategorias);
		panelCategorias.setLayout(null);
		
		JLabel lblCategoriasTitulo = new JLabel("CATEGORÍAS");
		lblCategoriasTitulo.setFont(new Font("Arial", Font.BOLD, 13));
		lblCategoriasTitulo.setBounds(15, 15, 150, 20);
		panelCategorias.add(lblCategoriasTitulo);
		
		btnCatTodas = new JButton("TODAS");
		btnCatTodas.addActionListener(this);
		btnCatTodas.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatTodas.setBackground(new Color(219, 178, 185)); 
		btnCatTodas.setForeground(new Color(0, 0, 0));
		btnCatTodas.setBounds(15, 50, 150, 38);
		btnCatTodas.setFocusable(false);
		btnCatTodas.setBorder(new LineBorder(new Color(200, 200, 200)));
		panelCategorias.add(btnCatTodas);
		
		btnCatPantalones = new JButton("PANTALONES & JEANS");
		btnCatPantalones.addActionListener(this);
		btnCatPantalones.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatPantalones.setBackground(Color.WHITE);
		btnCatPantalones.setForeground(Color.DARK_GRAY);
		btnCatPantalones.setBounds(15, 95, 150, 38);
		btnCatPantalones.setFocusable(false);
		btnCatPantalones.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatPantalones);
		
		btnCatPolos = new JButton("POLOS");
		btnCatPolos.addActionListener(this);
		btnCatPolos.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatPolos.setBackground(Color.WHITE);
		btnCatPolos.setForeground(Color.DARK_GRAY);
		btnCatPolos.setBounds(15, 140, 150, 38);
		btnCatPolos.setFocusable(false);
		btnCatPolos.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatPolos);
		
		btnCatFaldas = new JButton("FALDAS & SHORTS");
		btnCatFaldas.addActionListener(this);
		btnCatFaldas.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatFaldas.setBackground(Color.WHITE);
		btnCatFaldas.setForeground(Color.DARK_GRAY);
		btnCatFaldas.setBounds(15, 185, 150, 38);
		btnCatFaldas.setFocusable(false);
		btnCatFaldas.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatFaldas);
		
		btnCatVestidos = new JButton("VESTIDOS");
		btnCatVestidos.addActionListener(this);
		btnCatVestidos.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatVestidos.setBackground(Color.WHITE);
		btnCatVestidos.setForeground(Color.DARK_GRAY);
		btnCatVestidos.setBounds(15, 230, 150, 38);
		btnCatVestidos.setFocusable(false);
		btnCatVestidos.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatVestidos);
		
		btnCatPoleras = new JButton("POLERAS");
		btnCatPoleras.addActionListener(this);
		btnCatPoleras.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatPoleras.setBackground(Color.WHITE);
		btnCatPoleras.setForeground(Color.DARK_GRAY);
		btnCatPoleras.setBounds(15, 275, 150, 38);
		btnCatPoleras.setFocusable(false);
		btnCatPoleras.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatPoleras);
		
		btnCatCasacas = new JButton("CASACAS");
		btnCatCasacas.addActionListener(this);
		btnCatCasacas.setFont(new Font("Arial", Font.BOLD, 10));
		btnCatCasacas.setBackground(Color.WHITE);
		btnCatCasacas.setForeground(Color.DARK_GRAY);
		btnCatCasacas.setBounds(15, 320, 150, 38);
		btnCatCasacas.setFocusable(false);
		btnCatCasacas.setBorder(new LineBorder(new Color(220, 220, 220)));
		panelCategorias.add(btnCatCasacas);

		// =========================================================================
		// 3. COMPONENTE CENTRAL: CATÁLOGO DE PRODUCTOS
		// =========================================================================
		JPanel panelCatalogo = new JPanel();
		panelCatalogo.setBackground(Color.WHITE);
		panelCatalogo.setBounds(200, 72, 340, 547);
		panelCatalogo.setBorder(new LineBorder(new Color(220, 220, 220)));
		add(panelCatalogo);
		panelCatalogo.setLayout(null);
		
		JLabel lblProductosTitulo = new JLabel("PRODUCTOS ");
		lblProductosTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblProductosTitulo.setBounds(15, 15, 310, 20);
		panelCatalogo.add(lblProductosTitulo);
		
		JScrollPane scrollCatalogo = new JScrollPane();
		scrollCatalogo.setBounds(10, 48, 320, 426);
		panelCatalogo.add(scrollCatalogo);
		
		modeloCatalogo = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Código", "Prenda", "Precio", "Stock" }
		);
		tablaProductosCatalogo = new JTable(modeloCatalogo);
		scrollCatalogo.setViewportView(tablaProductosCatalogo);
		
		btnAgregarCarrito = new JButton("[ + Agregar al Carrito ]");
		btnAgregarCarrito.addActionListener(this);
		btnAgregarCarrito.setForeground(new Color(0, 0, 0));
		btnAgregarCarrito.setBackground(new Color(23, 165, 165)); 
		btnAgregarCarrito.setFont(new Font("Arial", Font.BOLD, 12));
		btnAgregarCarrito.setBounds(15, 484, 320, 35);
		btnAgregarCarrito.setFocusable(false);
		panelCatalogo.add(btnAgregarCarrito);

		// =========================================================================
		// 4. COMPONENTE DERECHO: CARRITO DE COMPRAS
		// =========================================================================
		JPanel panelCarrito = new JPanel();
		panelCarrito.setBackground(Color.WHITE);
		panelCarrito.setBounds(550, 72, 282, 547);
		panelCarrito.setBorder(new LineBorder(new Color(220, 220, 220)));
		add(panelCarrito);
		panelCarrito.setLayout(null);
		
		JLabel lblCarritoTitulo = new JLabel("🛒 CARRITO DE COMPRAS");
		lblCarritoTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblCarritoTitulo.setBounds(15, 15, 250, 20);
		panelCarrito.add(lblCarritoTitulo);
		
		JScrollPane scrollCarrito = new JScrollPane();
		scrollCarrito.setBounds(10, 48, 262, 260);
		panelCarrito.add(scrollCarrito);
		
		modeloCarrito = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Prenda", "Precio Unit.", "Precio Total", "Cant." }
		) {
			@Override
			public boolean isCellEditable(int row, int col) { 
				return false; 
			}
		};
		tablaCarrito = new JTable(modeloCarrito);
		scrollCarrito.setViewportView(tablaCarrito);
		JLabel lblOpGravadaTxt = new JLabel("OP. GRAVADA:");
		lblOpGravadaTxt.setFont(new Font("Arial", Font.BOLD, 12));
		lblOpGravadaTxt.setBounds(15, 318, 100, 20); // Posicionado perfectamente encima
		panelCarrito.add(lblOpGravadaTxt);
		
		lblOpGravadaNum = new JLabel("S/. 0.00", SwingConstants.RIGHT);
		lblOpGravadaNum.setForeground(new Color(139, 0, 55));
		lblOpGravadaNum.setFont(new Font("Arial", Font.BOLD, 13));
		lblOpGravadaNum.setBounds(122, 318, 150, 20);
		panelCarrito.add(lblOpGravadaNum);
		
		JLabel lblIgvTxt = new JLabel("IGV (18%):");
		lblIgvTxt.setFont(new Font("Arial", Font.BOLD, 12));
		lblIgvTxt.setBounds(15, 343, 100, 20); // Posicionado justo en el medio
		panelCarrito.add(lblIgvTxt);
		
		lblIgvNum = new JLabel("S/. 0.00", SwingConstants.RIGHT);
		lblIgvNum.setForeground(new Color(139, 0, 55));
		lblIgvNum.setFont(new Font("Arial", Font.BOLD, 13));
		lblIgvNum.setBounds(122, 343, 150, 20);
		panelCarrito.add(lblIgvNum);
		JLabel lblTotalTxt = new JLabel("TOTAL:");
		lblTotalTxt.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotalTxt.setBounds(15, 370, 80, 20);
		panelCarrito.add(lblTotalTxt);
		
		lblMontoTotal = new JLabel("S/. 0.00.", SwingConstants.RIGHT);
		lblMontoTotal.setForeground(new Color(139, 0, 55)); 
		lblMontoTotal.setFont(new Font("Arial", Font.BOLD, 16));
		lblMontoTotal.setBounds(122, 370, 150, 20);
		panelCarrito.add(lblMontoTotal);
		
		btnEliminarArticulo = new JButton("Quitar artículo");
		btnEliminarArticulo.addActionListener(this);
		btnEliminarArticulo.setBackground(new Color(255, 128, 192));
		btnEliminarArticulo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEliminarArticulo.setBounds(15, 400, 262, 28);
		btnEliminarArticulo.setFocusable(false);
		btnEliminarArticulo.setBorder(null);
		panelCarrito.add(btnEliminarArticulo);
			
		btnProcesarPago = new JButton("[  PROCESAR PAGO  ]");
		btnProcesarPago.addActionListener(this);
		btnProcesarPago.setForeground(new Color(0, 0, 0));
		btnProcesarPago.setBackground(new Color(40, 167, 69)); 
		btnProcesarPago.setFont(new Font("Arial", Font.BOLD, 12));
		btnProcesarPago.setBounds(10, 438, 262, 45);
		btnProcesarPago.setFocusable(false);
		panelCarrito.add(btnProcesarPago);
		
		refrescarCatalogoFiltrado("", "TODAS");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnProcesarPago) {
			do_btnProcesarPago_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarArticulo) {
			do_btnEliminarArticulo_actionPerformed(e);
		}
		if (e.getSource() == btnAgregarCarrito) {
			do_btnAgregarCarrito_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnCatCasacas) {
			do_btnCatCasacas_actionPerformed(e);	
		}
		if (e.getSource() == btnCatPoleras) {
			do_btnCatPoleras_actionPerformed(e);
		}
		if (e.getSource() == btnCatVestidos) {
			do_btnCatVestidos_actionPerformed(e);
		}
		if (e.getSource() == btnCatFaldas) {
			do_btnCatFaldas_actionPerformed(e);
		}
		if (e.getSource() == btnCatPolos) {
			do_btnCatPolos_actionPerformed(e);
		}
		if (e.getSource() == btnCatPantalones) {
			do_btnCatPantalones_actionPerformed(e);
		}
		if (e.getSource() == btnCatTodas) {
			do_btnCatTodas_actionPerformed(e);
		}
	}
	
	protected void do_btnCatTodas_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "TODAS";
		resetearColoresBotones(btnCatTodas);
		refrescarCatalogoFiltrado("", "TODAS");
	}
	
	// =========================================================================
	// MÉTODO LÓGICO FILTRADO: REPARADO
	// =========================================================================
	public void refrescarCatalogoFiltrado(String TextoBuscado, String Categoria) {
	    if (modeloCatalogo != null) {
	        modeloCatalogo.setRowCount(0);
	    }
	    
	    if (TextoBuscado == null || TextoBuscado.equals("Ingresa código o nombre de la prenda...")) {
	        TextoBuscado = "";
	    }
	    TextoBuscado = TextoBuscado.trim().toLowerCase();
	    
	    java.util.List<modelo.Prenda> lista = dao.PrendaDAO.listar(); 
	    if (lista == null) return;

	    for (modelo.Prenda p : lista) {
	        // ── CORRECCIÓN AQUÍ: Ahora evalúa de forma segura si el filtro es TODOS o TODAS ──
	        boolean filtroGeneral = Categoria == null || 
	                                Categoria.equalsIgnoreCase("todos") || 
	                                Categoria.equalsIgnoreCase("todas");
	                                
	        if (filtroGeneral || p.getCategoria().equalsIgnoreCase(Categoria)) {
	            
	            String nombre = p.getNombre() != null ? p.getNombre().toLowerCase() : "";
	            String codigo = p.getCodigo() != null ? p.getCodigo().toLowerCase() : "";
	            
	            if (TextoBuscado.isEmpty() || nombre.contains(TextoBuscado) || codigo.contains(TextoBuscado)) {
	                modeloCatalogo.addRow(new Object[] {
	                    p.getCodigo(),
	                    p.getNombre(),
	                    p.getPrecio(),
	                    p.getStockTotal()
	                });
	            }
	        }
	    }
	}

	public void resetearColoresBotones(JButton botonActivo) {
		JButton[] botones = {btnCatTodas, btnCatPantalones, btnCatPolos, btnCatFaldas, btnCatVestidos, btnCatPoleras, btnCatCasacas};
		for (JButton btn : botones) {
			if (btn == botonActivo) {
				btn.setBackground(new Color(219, 178, 185)); 
			} else {
				btn.setBackground(Color.WHITE); 
			}
		}
	}
	
	protected void do_btnCatPantalones_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "PANTALONES & JEANS";
		resetearColoresBotones(btnCatPantalones);
		refrescarCatalogoFiltrado("", "PANTALONES & JEANS");
	}
	protected void do_btnCatPolos_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "POLOS";
		resetearColoresBotones(btnCatPolos);
		refrescarCatalogoFiltrado("", "POLOS");
	}
	protected void do_btnCatFaldas_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "FALDAS & SHORTS";
		resetearColoresBotones(btnCatFaldas);
		refrescarCatalogoFiltrado("", "FALDAS & SHORTS");
	}
	protected void do_btnCatVestidos_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "VESTIDOS";
		resetearColoresBotones(btnCatVestidos);
		refrescarCatalogoFiltrado("", "VESTIDOS");
	}
	protected void do_btnCatPoleras_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "POLERAS";
		resetearColoresBotones(btnCatPoleras);
		refrescarCatalogoFiltrado("", "POLERAS");
	}
	protected void do_btnCatCasacas_actionPerformed(ActionEvent e) {
		categoriaSeleccionada = "CASACAS";
		resetearColoresBotones(btnCatCasacas);
		refrescarCatalogoFiltrado("", "CASACAS");
	}
	
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String texto = txtBuscador.getText().trim();
		if (texto.equals("Ingresa código o nombre de la prenda...")) {
			texto = "";
		}
		refrescarCatalogoFiltrado(texto, categoriaSeleccionada);
	}

	protected void do_btnAgregarCarrito_actionPerformed(ActionEvent e) {
		// 1. Validamos que el cajero haya seleccionado un producto de la tabla del medio
	    int filaSeleccionada = tablaProductosCatalogo.getSelectedRow();

	    if (filaSeleccionada == -1) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto del catálogo primero.");
	        return;
	    }

	    // 2. Extraemos los datos básicos incluyendo el STOCK ACTUAL de la tabla (Columna índice 3)
	    String nombrePrenda = modeloCatalogo.getValueAt(filaSeleccionada, 1).toString();
	    double precioUnitario = Double.parseDouble(modeloCatalogo.getValueAt(filaSeleccionada, 2).toString());
	    int stockDisponible = Integer.parseInt(modeloCatalogo.getValueAt(filaSeleccionada, 3).toString()); 

	    // CONTROL: Si el stock total en base de datos ya es 0, no lo dejamos avanzar
	    if (stockDisponible <= 0) {
	        javax.swing.JOptionPane.showMessageDialog(null, 
	            "¡Error! No queda stock disponible para '" + nombrePrenda + "'. Genere una entrada primero.", 
	            "Sin Inventario", 
	            javax.swing.JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // 3. Le pedimos al cajero que ingrese la cantidad deseada (Con bucle de insistencia si lo deja vacío)
	    String cantidadStr = "";
	    boolean entradaValida = false;

	    while (!entradaValida) {
	        cantidadStr = javax.swing.JOptionPane.showInputDialog(null, 
	            "¿Qué cantidad de '" + nombrePrenda + "' desea agregar?\n(Stock disponible: " + stockDisponible + " uds)", 
	            "Ingresar Cantidad", 
	            javax.swing.JOptionPane.QUESTION_MESSAGE);

	        // Si el usuario presiona "Cancelar" o cierra la ventanita con la 'X', salimos pacíficamente
	        if (cantidadStr == null) {
	            return; 
	        }

	        // ──> ¡AQUÍ ESTÁ EL MENSAJITO QUE PEDÍAS! <──
	        // Si no ingresó nada o solo puso espacios en blanco:
	        if (cantidadStr.trim().isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(null, 
	                "¡Campo obligatorio! Por favor, ingrese una cantidad para continuar.", 
	                "Advertencia", 
	                javax.swing.JOptionPane.WARNING_MESSAGE);
	            // El bucle continuará para volver a pedirle la cantidad
	        } else {
	            // Si el texto tiene contenido, rompemos el bucle para procesar el número
	            entradaValida = true;
	        }
	    }

	    int cantidadIngresada = 0;
	    try {
	        cantidadIngresada = Integer.parseInt(cantidadStr.trim());
	        
	        if (cantidadIngresada <= 0) {
	            javax.swing.JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero mayor a 0.");
	            return;
	        }
	        
	        // ¡LA BARRERA CLAVE!: Bloqueo directo si lo digitado supera el stock físico de la prenda
	        if (cantidadIngresada > stockDisponible) {
	            javax.swing.JOptionPane.showMessageDialog(null, 
	                "No puedes agregar " + cantidadIngresada + " unidades.\nEl stock actual en tienda es de solo " + stockDisponible + " unidades.", 
	                "Stock Insuficiente", 
	                javax.swing.JOptionPane.ERROR_MESSAGE);
	            return; 
	        }

	    } catch (NumberFormatException ex) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido.");
	        return;
	    }

	    // 4. Revisamos si esa prenda ya estaba agregada previamente en el carrito
	    boolean existeEnCarrito = false;
	    int filasCarrito = modeloCarrito.getRowCount();

	    for (int i = 0; i < filasCarrito; i++) {
	        String nombreEnCarrito = modeloCarrito.getValueAt(i, 0).toString();
	        
	        if (nombreEnCarrito.equals(nombrePrenda)) {
	            int cantidadActual = Integer.parseInt(modeloCarrito.getValueAt(i, 3).toString());
	            int nuevaCantidadTotal = cantidadActual + cantidadIngresada;
	            
	            // Validamos que la suma acumulada en el carrito tampoco pase el stock
	            if (nuevaCantidadTotal > stockDisponible) {
	                javax.swing.JOptionPane.showMessageDialog(null, 
	                    "No puedes añadir más unidades.\nYa tienes " + cantidadActual + " en el carrito, y el stock máximo disponible es " + stockDisponible + ".", 
	                    "Excede el inventario", 
	                    javax.swing.JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            double nuevoPrecioTotal = precioUnitario * nuevaCantidadTotal;
	            modeloCarrito.setValueAt(nuevoPrecioTotal, i, 2); 
	            modeloCarrito.setValueAt(nuevaCantidadTotal, i, 3); 
	            
	            existeEnCarrito = true;
	            break;
	        }
	    }

	    // 5. Si es una prenda nueva en el carrito, la agregamos
	    if (!existeEnCarrito) {
	        double precioTotalFila = precioUnitario * cantidadIngresada;
	        
	        modeloCarrito.addRow(new Object[] {
	            nombrePrenda,
	            precioUnitario,
	            precioTotalFila,  
	            cantidadIngresada 
	        });
	    }

	    // 6. Refrescamos el total general
	    calcularTotalGeneral();
	}

	private void calcularTotalGeneral() {
		double totalGeneral = 0.0;
		int filas = modeloCarrito.getRowCount();
		
		// 1. Recorremos el carrito sumando los subtotales
		for (int i = 0; i < filas; i++) {
			totalGeneral += Double.parseDouble(modeloCarrito.getValueAt(i, 2).toString());
		}
		
		// 2. Extraemos la matemática del IGV (Total / 1.18)
		double opGravada = totalGeneral / 1.18;
		double igv = totalGeneral - opGravada;
		
		// 3. Pintamos las 3 etiquetas de manera simultánea en el carrito
		lblOpGravadaNum.setText(String.format("S/. %.2f", opGravada));
		lblIgvNum.setText(String.format("S/. %.2f", igv));
		lblMontoTotal.setText(String.format("S/. %.2f", totalGeneral));
	}
	
	//boton quitar del carrito
	protected void do_btnEliminarArticulo_actionPerformed(ActionEvent e) {
		// 1. Validar que el usuario haya seleccionado una fila del CARRITO (la tabla de la derecha)
	    int filaSeleccionada = tablaCarrito.getSelectedRow(); // <── Revisa si tu JTable se llama así

	    if (filaSeleccionada == -1) {
	        javax.swing.JOptionPane.showMessageDialog(null, 
	            "Por favor, selecciona el artículo que deseas quitar del carrito.", 
	            "Aviso", 
	            javax.swing.JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // 2. Capturar el nombre del producto para personalizar el mensaje
	    String nombrePrenda = modeloCarrito.getValueAt(filaSeleccionada, 0).toString();

	    // 3. Pedir confirmación al cajero para evitar errores por un clic casual
	    int confirmar = javax.swing.JOptionPane.showConfirmDialog(null,
	        "¿Estás seguro de que deseas quitar '" + nombrePrenda + "' del carrito?", 
	        "Confirmar eliminación", 
	        javax.swing.JOptionPane.YES_NO_OPTION,
	        javax.swing.JOptionPane.QUESTION_MESSAGE);

	    if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
	        // 4. Borrar la fila del modelo de la tabla
	        modeloCarrito.removeRow(filaSeleccionada);
	        
	        // 5. ¡CRUCIAL!: Recalcular el total general inmediatamente
	        // Si no llamamos a esto, el artículo se borra pero el precio total se queda congelado arriba.
	        calcularTotalGeneral();
	        
	        javax.swing.JOptionPane.showMessageDialog(null, "Artículo removido con éxito.");
	    }
		
	}
	protected void do_btnProcesarPago_actionPerformed(ActionEvent e) {
		// 1. Validar que el carrito tenga al menos un producto
				if (modeloCarrito.getRowCount() == 0) {
					javax.swing.JOptionPane.showMessageDialog(this, 
						"El carrito está vacío. Agrega prendas antes de cobrar.", 
						"Carrito Vacío", javax.swing.JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 2. Calculamos cuánto suma el carrito en este momento
				double totalVenta = 0.0;
				for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
					totalVenta += Double.parseDouble(modeloCarrito.getValueAt(i, 2).toString());
				}
				    
				// 3. Abrimos tu formulario de clientes
				Formularios.FormProcesarPago frmCliente = new Formularios.FormProcesarPago();
				    
				// 4. Le inyectamos el total para que cambie el S/. 0.00 de arriba
				frmCliente.setTotalFactura(totalVenta); 
				    
				// 5. Lo mostramos en pantalla
				frmCliente.setVisible(true);
	}
}