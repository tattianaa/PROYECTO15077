package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblRegresar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Acceso Administrativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(240, 230, 210),
                    getWidth(), getHeight(), new Color(210, 190, 200));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("ACCESO ADMINISTRATIVO");
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTitulo.setForeground(new Color(80, 80, 80));
        lblTitulo.setBounds(190, 80, 320, 30);
        contentPane.add(lblTitulo);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        panel.setLayout(null);
        panel.setBounds(150, 120, 380, 280);
        contentPane.add(panel);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
        lblUsuario.setBounds(20, 20, 100, 20);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBackground(new Color(240, 200, 200));
        txtUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsuario.setBounds(20, 45, 330, 35);
        panel.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
        lblPassword.setBounds(20, 95, 100, 20);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBackground(new Color(240, 200, 200));
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPassword.setBounds(20, 120, 330, 35);
        panel.add(txtPassword);

        btnLogin = new JButton("INICIAR SESIÓN");
        btnLogin.addActionListener(e -> do_btnLogin_actionPerformed());
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(220, 170, 175));
        btnLogin.setForeground(new Color(50, 50, 50));
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setOpaque(true);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(20, 175, 330, 50);
        panel.add(btnLogin);

        lblRegresar = new JLabel(
            "<html><u>Regresar a la Tienda</u></html>",
            SwingConstants.CENTER);
        lblRegresar.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRegresar.setForeground(new Color(100, 100, 100));
        lblRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblRegresar.setBounds(20, 235, 330, 20);
        lblRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
           
            
        });
        panel.add(lblRegresar);
    }

    protected void do_btnLogin_actionPerformed() {
    	 String usuario = txtUsuario.getText().trim();
    	    String password = new String(txtPassword.getPassword()).trim();

    	    if (usuario.isEmpty() || password.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Ingresa usuario y contraseña.");
    	        txtUsuario.requestFocus();

    	        
    	        return;
    	    }

    	    try {
    	    	// Obtiene la conexión a MySQL
    	    	java.sql.Connection con = BD.Conexion.getConexion();

    	    	// Pregunta a la BD: ¿existe un usuario con ese nombre y contraseña?
    	    	String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

    	    	// Prepara la consulta — los ? se reemplazan con lo que escribió el usuario
    	    	java.sql.PreparedStatement ps = con.prepareStatement(sql);
    	    	ps.setString(1, usuario);   // primer ? = usuario
    	    	ps.setString(2, password);  // segundo ? = contraseña

    	    	// Ejecuta la consulta y guarda el resultado
    	    	java.sql.ResultSet rs = ps.executeQuery();

    	    	// Si encontró un usuario con esos datos → abre el sistema
    	    	if (rs.next()) {
    	    	    new GestorAdministrativo().setVisible(true);
    	    	    dispose();
    	    	} else {
    	    	    // Si no encontró nada → credenciales incorrectas
    	    	    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
    	    	}

    	    	// Cierra la conexión
    	    	con.close();
    	    }catch (Exception e) {
    	        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    	    }
    }
}