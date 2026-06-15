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
	
	
	// Antes creaba su propia lista de prendas — ahora la recibe desde GestorAdministrativo
	// Así PanelGestion y GestorEntradas comparten la misma lista
	private InventarioLogica gestor;

	// Lógica de entradas — recibida desde GestorAdministrativo
	// Se pasa a FormDetallesPrenda para mostrar el historial de entradas de cada prenda
	private logica.EntradaLogica entradas;

	// Modelo de la tabla — permite agregar y quitar filas dinámicamente, fuera del constr
	private DefaultTableModel modeloTabla;
	private JButton btnBuscar;
	private JButton btnNuevaPrenda;
	private JScrollPane scrollPane;



	/**
	 * Create the panel.
	 */
	public PanelGestion(InventarioLogica gestor,  logica.EntradaLogica entradas) {
		this.gestor=gestor;
		this.entradas=entradas;
		
		setBackground(new Color(245, 242, 225));
		setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 15, 232, 32);
		txtBuscar.setForeground(new Color(150, 150, 150));
		txtBuscar.setText("Ingresa código o nombre de la prenda..");
		add(txtBuscar);
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
		
		JComboBox<String> cboCategoria = new JComboBox<>();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"TODAS", "PANTALONES & JEANS", "POLOS", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS", "POLERAS", "CASACAS"}));
		cboCategoria.setBounds(440, 15, 125, 32);
		add(cboCategoria);
		
		
		
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
		{
			// DefaultTableModel es el modelo que controla los datos de la tabla
			// Le decimos qué columnas tiene y que empiece sin filas
			modeloTabla = new DefaultTableModel(
			    new Object[][]{},  // sin filas al inicio
			    new String[]{"ID", "IMAGEN", "NOMBRE", "STOCK", "PRECIO", "CATEGORÍA", "ACCIONES", "DETALLES"}
			) {
			    // Sobreescribimos isCellEditable para que el usuario
			    // no pueda editar las celdas directamente haciendo doble clic
			    public boolean isCellEditable(int row, int col) { return false; }
			};

			// Le pasamos el modelo a la tabla para que muestre sus datos
			tabladeGestión = new JTable(modeloTabla);

			// Altura de cada fila — más alta para que se vea la imagen
			tabladeGestión.setRowHeight(50);
			
			// Le dice a la tabla que la columna 1 (IMAGEN) muestra ImageIcon, no texto
			tabladeGestión.getColumnModel().getColumn(1).setCellRenderer(
			    new javax.swing.table.DefaultTableCellRenderer() {
			        public java.awt.Component getTableCellRendererComponent(
			                JTable t, Object v, boolean sel, boolean foc, int r, int c) {
			            JLabel lbl = new JLabel();
			            lbl.setHorizontalAlignment(JLabel.CENTER);
			            if (v instanceof ImageIcon) lbl.setIcon((ImageIcon) v);
			            return lbl;
			        }
			    });

			// Ponemos la tabla dentro del scrollPane para que tenga scroll
			scrollPane.setViewportView(tabladeGestión);
		}
		// ── Renderer columna ACCIONES (índice 6) ──
				// En lugar de texto, muestra dos botones: EDITAR y ELIMINAR
				// El renderer se ejecuta cada vez que la tabla dibuja esa celda
				tabladeGestión.getColumnModel().getColumn(6).setCellRenderer(
				    (t, v, sel, foc, r, c) -> {
				        // Panel que contiene los dos botones
				        JPanel p = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 4, 10));
				        p.setBackground(Color.WHITE);
				        // Botón EDITAR — rosado
				        JButton bEditar = new JButton("EDITAR");
				        bEditar.setBackground(new Color(220, 190, 195));
				        bEditar.setForeground(new Color(50, 50, 50));
				        bEditar.setBorderPainted(false);
				        bEditar.setFont(new Font("Arial", Font.BOLD, 10));
				        // Botón ELIMINAR — gris
				        JButton bEliminar = new JButton("ELIMINAR");
				        bEliminar.setBackground(new Color(180, 180, 185));
				        bEliminar.setForeground(new Color(50, 50, 50));
				        bEliminar.setBorderPainted(false);
				        bEliminar.setFont(new Font("Arial", Font.BOLD, 10));
				        p.add(bEditar);
				        p.add(bEliminar);
				        return p;
				    });

				// ── Renderer columna DETALLES (índice 7) ──
				// Muestra un botón azul "VER DETALLES" en cada fila
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
				
				// Ajustar anchos de columnas para que todo se vea bien
				tabladeGestión.getColumnModel().getColumn(0).setPreferredWidth(70);  // ID
				tabladeGestión.getColumnModel().getColumn(1).setPreferredWidth(65);  // IMAGEN
				tabladeGestión.getColumnModel().getColumn(2).setPreferredWidth(140); // NOMBRE
				tabladeGestión.getColumnModel().getColumn(3).setPreferredWidth(55);  // STOCK
				tabladeGestión.getColumnModel().getColumn(4).setPreferredWidth(75);  // PRECIO
				tabladeGestión.getColumnModel().getColumn(5).setPreferredWidth(130); // CATEGORÍA
				tabladeGestión.getColumnModel().getColumn(6).setPreferredWidth(200); // ACCIONES
				tabladeGestión.getColumnModel().getColumn(7).setPreferredWidth(110); // DETALLES
				
				// MouseListener — detecta clics en la tabla
				// Según la columna donde se hizo clic, ejecuta una acción diferente
				tabladeGestión.addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseClicked(java.awt.event.MouseEvent e) {
				        int fila = tabladeGestión.rowAtPoint(e.getPoint()); // fila donde se hizo clic
				        int col  = tabladeGestión.columnAtPoint(e.getPoint()); // columna donde se hizo clic
				        if (fila < 0) return; // si no hay fila, no hace nada
				        
				        if (col == 6) {
				            // Columna ACCIONES — detecta si es EDITAR o ELIMINAR por posición X
				            int x = e.getX() - tabladeGestión.getCellRect(fila, col, true).x;
				            	if (x < 90) {
				            	    // EDITAR — abre el formulario con los datos de la prenda
				            	    modelo.Prenda p = gestor.getPrendas().get(fila);
				            	    FormEditarPrenda dlg = new FormEditarPrenda(fila, p, modeloTabla, gestor);
				            	    dlg.setLocationRelativeTo(null);
				            	    dlg.setVisible(true);
				            	}

				              
				             else {
				                // ELIMINAR
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
				        	// VER DETALLES — abre el dialog con los datos de la prenda
				            modelo.Prenda p = gestor.getPrendas().get(fila);
				           
				            FormDetallesPrenda dlg = new FormDetallesPrenda(p,entradas);
				            dlg.setLocationRelativeTo(null);
				            dlg.setVisible(true);
				           
				        }
				    }
				});
				// Carga las prendas de MySQL al iniciar
				cargarPrendasDesdeDB();
				
				// Escucha cuando el panel se hace visible en pantalla
				// Se dispara cuando el usuario hace clic en "PANEL DE GESTIÓN" en el sidebar
				addComponentListener(new java.awt.event.ComponentAdapter() {
				    
				    // Este método se ejecuta cada vez que el panel aparece en pantalla
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
	            p.stockPorVariante(),
	            "S/. " + p.getPrecio(),
	            p.getCategoria()
	        });
	    }
	}
	//botón buscar
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
	}
	
	//botón nueva prenda
	protected void do_btnNuevaPrenda_actionPerformed(ActionEvent e) {

		  // Abrir el formulario de registro de nueva prenda
      FormNuevaPrenda dialog =
          new FormNuevaPrenda(modeloTabla, gestor);

      // Mostrar la ventana
      dialog.setVisible(true);
	}
}
