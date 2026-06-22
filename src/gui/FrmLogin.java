package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnIngresar;
	private JLabel lblMensajeError;
	
	// Variables para el control de intentos (Pistas que el profe no verá en el diseño)
	private int intentos = 0;
	private final int MAX_INTENTOS = 3;
	private int segundosRestantes = 15;
	private Timer timerBloqueo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		// Ajustes del Frame principal
		setTitle("SPEAKERS - Geo-Inventario y Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// --- PANEL SUPERIOR DE COLOR (HEADER) ---
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(252, 177, 232)); // El color base que quieras por código, o cámbialo desde WindowBuilder
		panelHeader.setBounds(0, 0, 450, 180);
		contentPane.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("SPEAKERS");
		lblBienvenido.setBackground(new Color(0, 0, 0));
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 26));
		lblBienvenido.setForeground(new Color(0, 0, 0));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(0, 40, 450, 35);
		panelHeader.add(lblBienvenido);
		
		JLabel lblSubtitulo = new JLabel("Software de Geo-Inventario y Ventas");
		lblSubtitulo.setBackground(new Color(0, 64, 0));
		lblSubtitulo.setForeground(new Color(0, 0, 0));
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setBounds(0, 75, 450, 20);
		panelHeader.add(lblSubtitulo);
		
		// --- TARJETA BLANCA FLOTANTE (FORMULARIO) ---
		JPanel panelCard = new JPanel();
		panelCard.setBackground(Color.WHITE);
		panelCard.setBounds(50, 130, 350, 320);
		panelCard.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
		contentPane.add(panelCard);
		panelCard.setLayout(null);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Arial", Font.BOLD, 12));
		lblUser.setForeground(Color.GRAY);
		lblUser.setBounds(35, 75, 280, 20);
		panelCard.add(lblUser);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsuario.setBounds(35, 95, 280, 35);
		panelCard.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(new Font("Arial", Font.BOLD, 12));
		lblPass.setForeground(Color.GRAY);
		lblPass.setBounds(35, 145, 280, 20);
		panelCard.add(lblPass);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
		txtContrasena.setBounds(35, 165, 280, 35);
		panelCard.add(txtContrasena);
		
		btnIngresar = new JButton("LOG IN");
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresar.setBackground(new Color(0, 128, 128));
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setFocusPainted(false);
		btnIngresar.setBounds(35, 235, 280, 45);
		panelCard.add(btnIngresar);
		
		// --- ELEMENTOS DE ALERTA ABAJO DE LA TARJETA ---
		lblMensajeError = new JLabel("");
		lblMensajeError.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeError.setForeground(Color.RED);
		lblMensajeError.setFont(new Font("Arial", Font.BOLD, 13));
		lblMensajeError.setBounds(0, 465, 450, 30);
		contentPane.add(lblMensajeError);
		
		JPanel panelFondoGris = new JPanel();
		panelFondoGris.setBackground(new Color(248, 249, 250));
		panelFondoGris.setBounds(0, 180, 450, 370);
		contentPane.add(panelFondoGris);
		
		// --- EVENTO DEL BOTÓN (Aquí empieza la magia) ---
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procesarLogin();
			}
		});
	}
	
	// --- MÉTODOS DE LÓGICA (Ocultos a la vista del diseñador visual) ---
	private void procesarLogin() {
		String usuario = txtUsuario.getText().trim();
		String contrasena = new String(txtContrasena.getPassword());
		// 1. Validamos PRIMERO si los campos están vacíos
		if (usuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
		    
		    lblMensajeError.setText("Por favor, llena todos los campos.");
		    JOptionPane.showMessageDialog(this, "Debe ingresar un usuario y una contraseña.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);

		} else {
		    // 2. Si NO están vacíos, procedemos con tu lógica normal
		    try {
		        if (usuario.equals("admin") && contrasena.equals("1234")) {
		            lblMensajeError.setText("");
		            JOptionPane.showMessageDialog(this, "¡Bienvenido a SPEAKERS!", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
		            
		            GestorAdministrativo principal = new GestorAdministrativo();
		            principal.setVisible(true);
		            this.dispose();
		        } else {
		            intentos++;
		            if (intentos >= MAX_INTENTOS) {
		                ejecutarBloqueoSistema();
		            } else {
		                txtContrasena.setText("");
		                JOptionPane.showMessageDialog(this, "ERROR: Usuario o contraseña incorrectos.\nIntentos: " + intentos + " de " + MAX_INTENTOS, "Intento Fallido", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // Esto solo actuará si algo interno se rompe
		    }
		}
	}
	private void ejecutarBloqueoSistema() {
		// Congelar casillas y botón
		txtUsuario.setEnabled(false);
		txtContrasena.setEnabled(false);
		btnIngresar.setEnabled(false);
		
		segundosRestantes = 15;
		lblMensajeError.setText("VUELVA A INTENTARLO EN " + segundosRestantes + " SEGUNDOS");

		// Timer de cuenta regresiva
		timerBloqueo = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				segundosRestantes--;
				if (segundosRestantes > 0) {
					lblMensajeError.setText("VUELVA A INTENTARLO EN " + segundosRestantes + " SEGUNDOS");
				} else {
					timerBloqueo.stop();
					intentos = 0;
					txtUsuario.setEnabled(true);
					txtContrasena.setEnabled(true);
					btnIngresar.setEnabled(true);
					txtUsuario.setText("");
					txtContrasena.setText("");
					lblMensajeError.setText("");
					
					JOptionPane.showMessageDialog(null, "Sistema desbloqueado. Intente nuevamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		timerBloqueo.start();
	}
}
