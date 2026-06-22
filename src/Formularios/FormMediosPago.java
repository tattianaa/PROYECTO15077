package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// 1. Agregamos el "implements ActionListener" para que escuche el cambio del ComboBox
public class FormMediosPago extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private double totalPagar = 0.0;
	
	public JLabel lblTotal;
	public JComboBox<String> cboMediosPago;
	public JButton btnConfirmar;
	public JButton btnCancelar;
	
	public JLabel lblPagaCon;
	public JTextField txtPagaCon;
	public JLabel lblVueltoTxt;
	public JLabel lblVueltoNum;
	public JLabel lblMensajeTarjeta;
	public JLabel lblMensajeQR;

	public static void main(String[] args) {
		try {
			FormMediosPago dialog = new FormMediosPago(null); 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FormMediosPago(JDialog padre) {
		super(padre, true); 
		
		setTitle("Seleccionar Medio de Pago - Speakers Moda");
		setBounds(100, 100, 380, 370); 
		if (padre != null) {
			setLocationRelativeTo(padre); 
		} else {
			setLocationRelativeTo(null); 
		}
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(245, 242, 225)); 

		JPanel panelBlanco = new JPanel();
		panelBlanco.setBackground(Color.WHITE);
		panelBlanco.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
		panelBlanco.setBounds(15, 15, 335, 300);
		getContentPane().add(panelBlanco);
		panelBlanco.setLayout(null);

		lblTotal = new JLabel("TOTAL A COBRAR: S/. 0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(139, 0, 55)); 
		lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
		lblTotal.setBounds(10, 20, 315, 25);
		panelBlanco.add(lblTotal);

		JLabel lblSeleccione = new JLabel("Seleccione la modalidad de pago:");
		lblSeleccione.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSeleccione.setBounds(25, 65, 250, 20);
		panelBlanco.add(lblSeleccione);

		String[] opcionesPago = { "💵  EFECTIVO", "💳  TARJETA", "📱  CÓDIGO QR" };
		cboMediosPago = new JComboBox<>(opcionesPago);
		cboMediosPago.setFont(new Font("Arial", Font.BOLD, 13));
		cboMediosPago.setBackground(Color.WHITE);
		cboMediosPago.setBounds(25, 95, 285, 35);
		// 2. Le decimos al ComboBox que avise cuando lo presionen
		cboMediosPago.addActionListener(this); 
		panelBlanco.add(cboMediosPago);

		lblPagaCon = new JLabel("¿Con cuánto paga?");
		lblPagaCon.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPagaCon.setBounds(25, 145, 120, 20);
		lblPagaCon.setVisible(false); // Nace oculto
		panelBlanco.add(lblPagaCon);

		txtPagaCon = new JTextField();
		txtPagaCon.setFont(new Font("Arial", Font.BOLD, 13));
		txtPagaCon.setBounds(25, 170, 120, 30);
		txtPagaCon.setVisible(false); // Nace oculto
		panelBlanco.add(txtPagaCon);
		txtPagaCon.setColumns(10);

		lblVueltoTxt = new JLabel("VUELTO:");
		lblVueltoTxt.setFont(new Font("Arial", Font.BOLD, 12));
		lblVueltoTxt.setBounds(180, 145, 100, 20);
		lblVueltoTxt.setVisible(false); // Nace oculto
		panelBlanco.add(lblVueltoTxt);

		lblVueltoNum = new JLabel("S/. 0.00");
		lblVueltoNum.setForeground(new Color(40, 167, 69)); 
		lblVueltoNum.setFont(new Font("Arial", Font.BOLD, 16));
		lblVueltoNum.setBounds(180, 170, 130, 25);
		lblVueltoNum.setVisible(false); // Nace oculto
		panelBlanco.add(lblVueltoNum);

		btnConfirmar = new JButton("CONFIRMAR COBRO");
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 12));
		btnConfirmar.setForeground(new Color(0, 0, 0));
		btnConfirmar.setBackground(new Color(40, 167, 69)); 
		btnConfirmar.setFocusable(false);
		btnConfirmar.setBounds(160, 245, 165, 35);
		panelBlanco.add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBackground(new Color(255, 128, 128));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(25, 245, 110, 35);
		panelBlanco.add(btnCancelar);
		// Mensaje dinámico para Tarjeta
		lblMensajeTarjeta = new JLabel("💳 Esperando respuesta del POS electrónico...");
		lblMensajeTarjeta.setFont(new Font("Arial", Font.ITALIC, 13));
		lblMensajeTarjeta.setForeground(new Color(0, 102, 204)); // Azul informativo
		lblMensajeTarjeta.setBounds(25, 160, 285, 25);
		lblMensajeTarjeta.setVisible(false); // Nace oculto
		panelBlanco.add(lblMensajeTarjeta);

				// Mensaje dinámico para QR
		lblMensajeQR = new JLabel("📱 Escanee el QR dinámico de Speakers Moda...");
		lblMensajeQR.setFont(new Font("Arial", Font.ITALIC, 13));
		lblMensajeQR.setForeground(new Color(102, 0, 153)); // Morado QR
		lblMensajeQR.setBounds(25, 160, 285, 25);
		lblMensajeQR.setVisible(false); // Nace oculto
		panelBlanco.add(lblMensajeQR);

		
		// 3. Forzamos a que verifique la opción inicial al abrirse
		evaluarMedioPago();
	}

	// 4. Este evento detecta cuando cambias de opción en el ComboBox
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboMediosPago) {
			evaluarMedioPago();
		}
	}

	// 5. La magia que los vuelve visibles o invisibles en pantalla
	private void evaluarMedioPago() {
		String seleccion = cboMediosPago.getSelectedItem().toString();
		
		if (seleccion.contains("EFECTIVO")) {
			// 💵 MOSTRAMOS EFECTIVO
			lblPagaCon.setVisible(true);
			txtPagaCon.setVisible(true);
			lblVueltoTxt.setVisible(true);
			lblVueltoNum.setVisible(true);
			
			// OCULTAMOS TARJETA Y QR
			lblMensajeTarjeta.setVisible(false);
			lblMensajeQR.setVisible(false);
			
			txtPagaCon.requestFocus(); // Pone el cursor para escribir rápido
		} 
		else if (seleccion.contains("TARJETA")) {
			// OCULTAMOS EFECTIVO
			lblPagaCon.setVisible(false);
			txtPagaCon.setVisible(false);
			lblVueltoTxt.setVisible(false);
			lblVueltoNum.setVisible(false);
			txtPagaCon.setText(""); 
			
			// 💳 MOSTRAMOS TARJETA
			lblMensajeTarjeta.setVisible(true);
			lblMensajeQR.setVisible(false);
		} 
		else if (seleccion.contains("QR")) {
			// OCULTAMOS EFECTIVO
			lblPagaCon.setVisible(false);
			txtPagaCon.setVisible(false);
			lblVueltoTxt.setVisible(false);
			lblVueltoNum.setVisible(false);
			txtPagaCon.setText(""); 
			
			// 📱 MOSTRAMOS QR
			lblMensajeTarjeta.setVisible(false);
			lblMensajeQR.setVisible(true);
		}
	
	}

	public void setTotalFactura(double total) {
		this.totalPagar = total;
		this.lblTotal.setText("TOTAL A COBRAR: S/. " + String.format("%.2f", total));
	}
	/**
	 * Realiza el cálculo matemático en tiempo real evaluando la coherencia del monto
	 */
	private void calcularVuelto() {
		try {
			String texto = txtPagaCon.getText().trim();
			if (texto.isEmpty()) {
				lblVueltoNum.setText("S/. 0.00");
				lblVueltoNum.setForeground(new Color(40, 167, 69));
				return;
			}
			
			double montoCliente = Double.parseDouble(texto);
			double vuelto = montoCliente - totalPagar;
			
			if (vuelto < 0) {
				lblVueltoNum.setText("Falta dinero");
				lblVueltoNum.setForeground(Color.RED); // Alerta en rojo
			} else {
				lblVueltoNum.setText(String.format("S/. %.2f", vuelto));
				lblVueltoNum.setForeground(new Color(40, 167, 69)); // Éxito en verde
			}
		} catch (NumberFormatException ex) {
			lblVueltoNum.setText("Monto inválido");
			lblVueltoNum.setForeground(Color.RED);
		}
	}
}