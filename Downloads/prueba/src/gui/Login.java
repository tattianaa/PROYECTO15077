package gui;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import gui.PanelInicio;
import gui.GestorAdministrativo;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JLabel lblRegresar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Acceso Administrativo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);

		// Fondo degradado
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(0, 0, new Color(240, 230, 210), getWidth(), getHeight(), new Color(210, 190, 200));
				g2.setPaint(gp);
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Titulo
		JLabel lblTitulo = new JLabel("ACCESO ADMINISTRATIVO");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTitulo.setForeground(new Color(80, 80, 80));
		lblTitulo.setBounds(190, 80, 320, 30);
		contentPane.add(lblTitulo);

		// Panel blanco central
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(20, 30, 20, 30));
		panel.setLayout(null);
		panel.setBounds(150, 120, 380, 280);
		contentPane.add(panel);

		// Label Usuario
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsuario.setBounds(20, 20, 100, 20);
		panel.add(lblUsuario);

		// Campo Usuario
		txtUsuario = new JTextField();
		txtUsuario.setBackground(new Color(240, 200, 200));
		txtUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		txtUsuario.setBounds(20, 45, 330, 35);
		panel.add(txtUsuario);

		// Label Contraseña
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(20, 95, 100, 20);
		panel.add(lblPassword);

		// Campo Contraseña
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(240, 200, 200));
		txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		txtPassword.setBounds(20, 120, 330, 35);
		panel.add(txtPassword);

		// Boton Iniciar Sesion
		btnLogin = new JButton("INICIAR SESIÓN");
		
		//crea la acción cuando haces clic
		btnLogin.addActionListener(e -> {
			// Obtener lo que el usuario escribió en el campo usuario
		    String usuario = txtUsuario.getText();
		    // Obtener la contraseña (se convierte de char[] a String)
		    //java devuelve en caracteres por seguridad- convertir a string
		    String password = new String(txtPassword.getPassword());
		    
		    // Validación simple del login (por ahora sin base de datos)
		    if (usuario.equals("admin") && password.equals("1234")) {
		        // Si los datos son correctos, abre el panel administrativo
		        new GestorAdministrativo().setVisible(true);
		        // Cierra la ventana de login
		        dispose();
		    } else {
		        // Si los datos son incorrectos, muestra error
		        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		    }
		});
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogin.setBackground(new Color(220, 170, 175));
		btnLogin.setForeground(new Color(50, 50, 50));
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setOpaque(true);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(20, 175, 330, 50);
		panel.add(btnLogin);

		// Link Regresar
		lblRegresar = new JLabel("Regresar a la Tienda", SwingConstants.CENTER);
		lblRegresar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRegresar.setForeground(new Color(100, 100, 100));
		lblRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegresar.setBounds(20, 235, 330, 20);
		// Hace que el texto parezca link (opcional)
		lblRegresar.setText("<html><u>Regresar a la Tienda</u></html>");
		panel.add(lblRegresar);
		// Permite regresar al panel de inicio cuando se hace clic en el texto
		lblRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        // Abre la ventana principal del sistema
		        new PanelInicio().setVisible(true);
		        // Cierra la ventana actual (Login)
		        dispose();
		    }
		});
	}
}
