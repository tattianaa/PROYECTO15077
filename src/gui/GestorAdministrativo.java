package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestorAdministrativo extends JFrame implements ActionListener {
	// Instancia de la lógica de inventario — maneja la lista de prendas
	// Se declara aquí para que todos los métodos de la clase puedan usarla
	private logica.InventarioLogica gestor = new logica.InventarioLogica();

	// Instancia de la lógica de proveedores — maneja la lista de proveedores
	// Se comparte entre GestorProveedores y GestorEntradas para que usen la misma lista
	private logica.ProveedorLogica GestorProveedores = new logica.ProveedorLogica();
	
	// Instancia de la lógica de entradas — compartida entre GestorEntradas y FormDetallesPrenda
	private logica.EntradaLogica gestorEntradas = new logica.EntradaLogica();

	
	// Layout que controla qué panel se muestra en el área principal
	private CardLayout cl;

	// Panel principal donde se muestran los módulos (gestión, ventas, etc.)
	private JPanel mainPanel;


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPanelGestion;
	private JButton btnGestionProveedores;
	private JButton btnGestionInventarios;
	private JButton btnPuntoVenta;
	private JButton btnCerrarSesion_1;
	private GestorVentas panelVentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorAdministrativo frame = new GestorAdministrativo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestorAdministrativo() {
		setResizable(false);
		setSize(new Dimension(1100, 650));
		setTitle("Proyecto Final - Speakers Moda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 244, 574);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("🔊 SPEAKERS");
		lblNewLabel.setBounds(10, 11, 113, 14);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(new Rectangle(10, 58, 180, 2));
		separator.setBounds(10, 48, 228, 14);
		panel.add(separator);
		
		btnPanelGestion = new JButton("📋 PANEL DE GESTIÓN");
		btnPanelGestion.addActionListener(this);
		btnPanelGestion.setHorizontalAlignment(SwingConstants.LEFT);
		btnPanelGestion.setForeground(new Color(60, 60, 60));
		btnPanelGestion.setFocusPainted(false);
		btnPanelGestion.setBorderPainted(false);
		btnPanelGestion.setBackground(Color.WHITE);
		btnPanelGestion.setBounds(5, 65, 228, 30);
		panel.add(btnPanelGestion);
		
		btnGestionProveedores = new JButton("🏭 GESTIÓN DE PROVEEDORES");
		btnGestionProveedores.addActionListener(this);
		btnGestionProveedores.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionProveedores.setForeground(new Color(60, 60, 60));
		btnGestionProveedores.setFocusPainted(false);
		btnGestionProveedores.setBorderPainted(false);
		btnGestionProveedores.setBackground(Color.WHITE);
		btnGestionProveedores.setBounds(10, 100, 228, 30);
		panel.add(btnGestionProveedores);
		
		btnGestionInventarios = new JButton("📥 GESTIÓN DE INVENTARIOS");
		btnGestionInventarios.addActionListener(this);
		btnGestionInventarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionInventarios.setForeground(new Color(60, 60, 60));
		btnGestionInventarios.setFocusPainted(false);
		btnGestionInventarios.setBorderPainted(false);
		btnGestionInventarios.setBackground(Color.WHITE);
		btnGestionInventarios.setBounds(10, 140, 228, 30);
		panel.add(btnGestionInventarios);
		
		btnPuntoVenta = new JButton("🛒 PUNTO DE VENTA");
		btnPuntoVenta.addActionListener(this);
		btnPuntoVenta.setHorizontalAlignment(SwingConstants.LEFT);
		btnPuntoVenta.setForeground(new Color(60, 60, 60));
		btnPuntoVenta.setFocusPainted(false);
		btnPuntoVenta.setBorderPainted(false);
		btnPuntoVenta.setBackground(Color.WHITE);
		btnPuntoVenta.setBounds(10, 180, 228, 30);
		panel.add(btnPuntoVenta);
		
		btnCerrarSesion_1 = new JButton("🚪 CERRAR SESIÓN");
		btnCerrarSesion_1.addActionListener(this);
		btnCerrarSesion_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSesion_1.setForeground(new Color(60, 60, 60));
		btnCerrarSesion_1.setFocusPainted(false);
		btnCerrarSesion_1.setBorderPainted(false);
		btnCerrarSesion_1.setBackground(Color.WHITE);
		btnCerrarSesion_1.setBounds(10, 222, 228, 30);
		panel.add(btnCerrarSesion_1);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 242, 225));
		mainPanel.setBounds(258, 11, 842, 639);
		contentPane.add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		// Obtenemos el CardLayout del mainPanel para poder cambiar de panel
		cl = (CardLayout) mainPanel.getLayout();

		// Agregamos cada panel al CardLayout con un nombre clave
		// Cuando el usuario haga clic en un botón del sidebar, 
		// se mostrará el panel con ese nombre
		// Panel principal de gestión de prendas
		
		//mismalista
		PanelGestion panelGestion = new PanelGestion(gestor, gestorEntradas);
		mainPanel.add(panelGestion, "gestión");
		panelGestion.setLayout(null);
		mainPanel.add(new GestorProveedores(GestorProveedores), "proveedores");
		mainPanel.add(new GestorEntradas(gestor, GestorProveedores,  gestorEntradas), "entradas");
		
		// 1. Creamos la variable única para tu Punto de Venta
	 panelVentas = new GestorVentas(gestor);
		// 2. La agregamos al contenedor principal con su etiqueta
		mainPanel.add(panelVentas, "ventas");
		
		// Muestra el panel de gestión al iniciar la aplicación
		cl.show(mainPanel, "gestión");


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrarSesion_1) {
			do_btnCerrarSesion_1_actionPerformed(e);
		}
		if (e.getSource() == btnPuntoVenta) {
			do_btnPuntoVenta_actionPerformed(e);
		}
		if (e.getSource() == btnGestionInventarios) {
			do_btnGestionInventarios_actionPerformed(e);
		}
		if (e.getSource() == btnGestionProveedores) {
			do_btnGestionProveedores_1_actionPerformed(e);
		}
		if (e.getSource() == btnPanelGestion) {
			do_btnPanelGestion_1_actionPerformed(e);
		}
	}
	
	//botón PanelGestión
	protected void do_btnPanelGestion_1_actionPerformed(ActionEvent e) {
		cl.show(mainPanel, "gestión");
	}
	
	//botón proveedores
	protected void do_btnGestionProveedores_1_actionPerformed(ActionEvent e) {
		cl.show(mainPanel, "proveedores");
	}
	//bo´tón gestión de inventarios
	protected void do_btnGestionInventarios_actionPerformed(ActionEvent e) {
		cl.show(mainPanel,"entradas");
	}
	//botón punto deventa
	protected void do_btnPuntoVenta_actionPerformed(ActionEvent e) {
		panelVentas.refrescarCatalogoFiltrado("", "TODAS");
		cl.show(mainPanel, "ventas");
	}
	protected void do_btnCerrarSesion_1_actionPerformed(ActionEvent e) {
		
		new FrmLogin().setVisible(true);
		this.setVisible(false);
		

	}
}
