package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FormProcesarPago extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// Variable global para retener el dinero que viene del carrito
	private double totalRecibido = 0.0;
	
	// Componentes de texto
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	
	// Los tres Radio Buttons en orden correcto
	private JRadioButton rbtnBoletaSimple;
	private JRadioButton rbtnBoletaElectronica;
	private JRadioButton rbtnFactura;
	private ButtonGroup grupoComprobantes;
	
	// Etiquetas dinámicas y botones
	private JLabel lblDocumento;
	private JLabel lblNombre;
	private JButton btnBuscar;
	private JButton btnContinuarPago;
	private JLabel lblMontoTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormProcesarPago dialog = new FormProcesarPago();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormProcesarPago() {
		// Configuración del JDialog Modal
		setTitle("Procesar Venta - Speakers Moda");
		setModal(true);
		setBounds(100, 100, 530, 500);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(245, 242, 225)); // Fondo Crema

		// Panel Blanco Contenedor
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBackground(Color.WHITE);
		panelBlanco.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
		panelBlanco.setBounds(15, 15, 485, 430);
		getContentPane().add(panelBlanco);
		panelBlanco.setLayout(null);

		// Monto de la Venta
		lblMontoTotal = new JLabel("TOTAL A PAGAR: S/. 0.00");
		lblMontoTotal.setForeground(new Color(139, 0, 55));
		lblMontoTotal.setFont(new Font("Arial", Font.BOLD, 15));
		lblMontoTotal.setBounds(20, 15, 445, 25);
		panelBlanco.add(lblMontoTotal);

		// Etiqueta Tipo Comprobante
		JLabel lblTipoComp = new JLabel("Comprobante:");
		lblTipoComp.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTipoComp.setBounds(20, 55, 90, 20);
		panelBlanco.add(lblTipoComp);

		// 1. BOLETA SIMPLE
		rbtnBoletaSimple = new JRadioButton("BOLETA SIMPLE");
		rbtnBoletaSimple.setBackground(Color.WHITE);
		rbtnBoletaSimple.setFont(new Font("Arial", Font.BOLD, 11));
		rbtnBoletaSimple.setBounds(115, 50, 125, 30);
		panelBlanco.add(rbtnBoletaSimple);

		// 2. BOLETA ELECTRÓNICA
		rbtnBoletaElectronica = new JRadioButton("BOLETA ELECTRÓNICA");
		rbtnBoletaElectronica.setBackground(Color.WHITE);
		rbtnBoletaElectronica.setFont(new Font("Arial", Font.BOLD, 11));
		rbtnBoletaElectronica.setBounds(245, 50, 160, 30);
		panelBlanco.add(rbtnBoletaElectronica);

		// 3. FACTURA
		rbtnFactura = new JRadioButton("FACTURA");
		rbtnFactura.setBackground(Color.WHITE);
		rbtnFactura.setFont(new Font("Arial", Font.BOLD, 11));
		rbtnFactura.setBounds(405, 50, 80, 30);
		panelBlanco.add(rbtnFactura);

		// Agruparlos para mutua exclusión
		grupoComprobantes = new ButtonGroup();
		grupoComprobantes.add(rbtnBoletaSimple);
		grupoComprobantes.add(rbtnBoletaElectronica);
		grupoComprobantes.add(rbtnFactura);

		// Componentes de entrada de datos
		lblDocumento = new JLabel("DNI/RUC:");
		lblDocumento.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDocumento.setBounds(20, 100, 120, 20);
		panelBlanco.add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDocumento.setBounds(150, 95, 130, 30);
		panelBlanco.add(txtDocumento);
		txtDocumento.setColumns(10);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 11));
		btnBuscar.setBackground(new Color(219, 178, 185));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(295, 95, 100, 30);
		panelBlanco.add(btnBuscar);

		lblNombre = new JLabel("Nombres y Apellidos / Razón Social:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(20, 145, 445, 20);
		panelBlanco.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setBounds(20, 165, 445, 30);
		panelBlanco.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelefono.setBounds(20, 210, 200, 20);
		panelBlanco.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefono.setBounds(20, 230, 200, 30);
		panelBlanco.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo Electrónico:");
		lblCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCorreo.setBounds(240, 210, 225, 20);
		panelBlanco.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCorreo.setBounds(240, 230, 225, 30);
		panelBlanco.add(txtCorreo);
		txtCorreo.setColumns(10);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDireccion.setBounds(20, 275, 445, 20);
		panelBlanco.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDireccion.setBounds(20, 295, 445, 30);
		panelBlanco.add(txtDireccion);
		txtDireccion.setColumns(10);

		btnContinuarPago = new JButton("CONTINUAR AL PAGO");
		btnContinuarPago.setFont(new Font("Arial", Font.BOLD, 13));
		btnContinuarPago.setBackground(new Color(40, 167, 69)); // Fondo Verde para resaltar
		btnContinuarPago.setForeground(new Color(0, 0, 0));
		btnContinuarPago.setFocusable(false);
		btnContinuarPago.setBounds(20, 365, 445, 45);
		panelBlanco.add(btnContinuarPago);

		// =========================================================================
		// EVENTOS DE LOS RADIO BUTTONS
		// =========================================================================
		
		rbtnBoletaSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDocumento.setText("DNI:");
				lblNombre.setText("Nombres y Apellidos:");
				
				txtDocumento.setText("00000000");
				txtNombre.setText("CLIENTE VARIOS");
				txtTelefono.setText("000000000");
				txtCorreo.setText("clientes@speakersmoda.com");
				txtDireccion.setText("TIENDA PRINCIPAL");
				
				txtDocumento.setEnabled(false);
				txtNombre.setEnabled(false);
				txtTelefono.setEnabled(false);
				txtCorreo.setEnabled(false);
				txtDireccion.setEnabled(false);
				btnBuscar.setEnabled(false);
			}
		});

		rbtnBoletaElectronica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDocumento.setText("DNI:");
				lblNombre.setText("Nombres y Apellidos:");
				
				txtDocumento.setText("");
				txtNombre.setText("");
				txtTelefono.setText("");
				txtCorreo.setText("");
				txtDireccion.setText("");
				
				txtDocumento.setEnabled(true);
				txtNombre.setEnabled(true);
				txtTelefono.setEnabled(true);
				txtCorreo.setEnabled(true);
				txtDireccion.setEnabled(true);
				btnBuscar.setEnabled(true);
				txtDocumento.requestFocus();
			}
		});

		rbtnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDocumento.setText("RUC:");
				lblNombre.setText("Razón Social:");
				
				txtDocumento.setText("");
				txtNombre.setText("");
				txtTelefono.setText("");
				txtCorreo.setText("");
				txtDireccion.setText("");
				
				txtDocumento.setEnabled(true);
				txtNombre.setEnabled(true);
				txtTelefono.setEnabled(true);
				txtCorreo.setEnabled(true);
				txtDireccion.setEnabled(true);
				btnBuscar.setEnabled(true);
				txtDocumento.requestFocus();
			}
		});

		// =========================================================================
		// EVENTO ACCIÓN: BOTÓN CONTINUAR AL PAGO (VALIDACIONES BLINDADAS)
		// =========================================================================
		btnContinuarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!rbtnBoletaSimple.isSelected() && !rbtnBoletaElectronica.isSelected() && !rbtnFactura.isSelected()) {
					javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
						"Por favor, seleccione el tipo de comprobante para continuar.", 
						"Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
					return;
				}

				String doc = txtDocumento.getText().trim();
				String nombre = txtNombre.getText().trim();
				String telefono = txtTelefono.getText().trim();
				String correo = txtCorreo.getText().trim();
				String direccion = txtDireccion.getText().trim();

				if (doc.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
						"¡Todos los campos son obligatorios! Por favor, complete todo el formulario.", 
						"Campos Incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validaciones DNI / RUC con try-catch
				if (!rbtnBoletaSimple.isSelected()) {
					try {
						Long.parseLong(doc);
						
						if (rbtnBoletaElectronica.isSelected() && doc.length() != 8) {
							javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
								"DNI inválido. El DNI debe tener exactamente 8 dígitos.", 
								"Error de Documento", javax.swing.JOptionPane.ERROR_MESSAGE);
							txtDocumento.requestFocus();
							return;
						}
						
						if (rbtnFactura.isSelected()) {
							if (doc.length() != 11) {
								javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
									"RUC inválido. El RUC debe tener exactamente 11 dígitos.", 
									"Error de Documento", javax.swing.JOptionPane.ERROR_MESSAGE);
								txtDocumento.requestFocus();
								return;
							}
							if (!doc.startsWith("10") && !doc.startsWith("20")) {
								javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
									"RUC inválido. Recuerde que un RUC válido en el Perú debe comenzar con 10 o 20.", 
									"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
								txtDocumento.requestFocus();
								return;
							}
						}
					} catch (NumberFormatException ex) {
						String tipoDoc = rbtnBoletaElectronica.isSelected() ? "DNI" : "RUC";
						javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
							"El " + tipoDoc + " ingresado es incorrecto. Debe contener únicamente números.", 
							"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
						txtDocumento.requestFocus();
						return;
					}
				}

				// Validar Nombre (solo letras)
				if (!rbtnBoletaSimple.isSelected()) {
					if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
						String campoErroneo = rbtnBoletaElectronica.isSelected() ? "Nombres y Apellidos" : "Razón Social";
						javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
							"El campo '" + campoErroneo + "' es inválido. No se permiten números ni caracteres especiales.", 
							"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
						txtNombre.requestFocus();
						return;
					}
				}

				// Validar Teléfono (exactamente 9 dígitos)
				if (!rbtnBoletaSimple.isSelected()) {
					try {
						Integer.parseInt(telefono);
						if (telefono.length() != 9) {
							javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
								"Número inválido. El teléfono debe tener exactamente 9 dígitos.", 
								"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
							txtTelefono.requestFocus();
							return;
						}
					} catch (NumberFormatException ex) {
						javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
							"El teléfono ingresado es incorrecto. Vuelva a intentarlo. Por favor.", 
							"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
						txtTelefono.requestFocus();
						return;
					}
				}

				// Validar Correo Electrónico sin aceptar "@."
				if (!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") > correo.lastIndexOf(".") 
					|| correo.contains("@.")) {
					
					javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
						"El formato del correo electrónico no es válido (Ejemplo: cliente@gmail.com o empresa@outlook.com).", 
						"Error de Correo", javax.swing.JOptionPane.ERROR_MESSAGE);
					txtCorreo.requestFocus();
					return;
				}
				
				// Registro físico en MySQL
				if (!rbtnBoletaSimple.isSelected()) {
					modelo.Cliente clienteExistente = dao.ClienteDAO.buscarPorDocumento(doc);
					String tipoDocStr = rbtnBoletaElectronica.isSelected() ? "DNI" : "RUC";

					if (clienteExistente == null) {
						modelo.Cliente nuevoCliente = new modelo.Cliente(tipoDocStr, doc, nombre, telefono, direccion, correo);
						
						boolean insertadoOk = dao.ClienteDAO.insertar(nuevoCliente);
						
						if (!insertadoOk) {
							javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
								"Error al registrar el nuevo cliente en la Base de Datos.", 
								"Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}	

				// Si todo está correcto, avanzamos
				javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
					"Validaciones aprobadas con éxito.\nProcediendo a los medios de pago...", 
					"Speakers Moda", javax.swing.JOptionPane.INFORMATION_MESSAGE);
				
				// 🚀 LLAMADO SEGURO E IMPORTADO: Evita el error en la línea 387
				Formularios.FormMediosPago frmPago = new Formularios.FormMediosPago(null);
				frmPago.setTotalFactura(totalRecibido); 
				frmPago.setVisible(true);
			}
		});
		
		// Iniciar con foco por defecto
		txtDocumento.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
	}
	
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String doc = txtDocumento.getText().trim();

		// 1. Validar que no esté vacío
		if (doc.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"Por favor, ingrese un número de documento para realizar la búsqueda.", 
				"Campo Vacío", javax.swing.JOptionPane.WARNING_MESSAGE);
			txtDocumento.requestFocus();
			return;
		}

		// 2. Validar que se haya seleccionado Boleta Electrónica o Factura
		if (!rbtnBoletaElectronica.isSelected() && !rbtnFactura.isSelected()) {
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"Por favor, seleccione primero BOLETA ELECTRÓNICA o FACTURA para validar.", 
				"Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 3. Validar longitudes correctas
		if (rbtnBoletaElectronica.isSelected() && doc.length() != 8) {
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"¡DNI Inválido! El DNI debe tener exactamente 8 dígitos.", 
				"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
			txtDocumento.requestFocus();
			return;
		}

		if (rbtnFactura.isSelected() && doc.length() != 11) {
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"¡RUC Inválido! El RUC debe tener exactamente 11 dígitos.", 
				"Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
			txtDocumento.requestFocus();
			return;
		}

		// 4. Consultamos a tu clase DAO
		modelo.Cliente c = dao.ClienteDAO.buscarPorDocumento(doc);

		if (c != null) {
			// El cliente ya existe -> AUTOLLENADO INMEDIATO
			txtNombre.setText(c.getNombre());
			txtTelefono.setText(c.getTelefono());
			txtDireccion.setText(c.getDireccion());
			txtCorreo.setText(c.getCorreo()); 
			
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"¡Cliente encontrado! Los datos se han cargado automáticamente.", 
				"Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		} else {
			// El cliente es Nuevo -> LIMPIAMOS TODO PARA QUE EL CAJERO ESCRIBA
			txtNombre.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtCorreo.setText(""); 	
			
			javax.swing.JOptionPane.showMessageDialog(FormProcesarPago.this, 
				"Cliente no encontrado. Por favor, regístrelo por primera vez.", 
				"Cliente Nuevo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
			
			txtNombre.requestFocus(); 
		}
	}
	
	public void setTotalFactura(double total) {
		this.totalRecibido = total; // Guardamos el valor de la venta en la variable de la clase
		this.lblMontoTotal.setText(String.format("TOTAL A PAGAR: S/. %.2f", total));
	}
}