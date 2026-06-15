package gui;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JToggleButton;

public class GestorVentas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txtBuscarPrenda;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable tablaVenta;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane_1;
	private JTable tablaCarrito;
	private JLabel lblNewLabel_2;
	private JPanel panel_2;
	private JLabel lblNewLabel_3;
	private JToggleButton tbBoleta;
	private JToggleButton tglbtnBoletaElect;
	private JToggleButton tglbtnFactura;
	private JPanel panel_3;
	private JLabel lblDniruc;
	private JTextField txtDocumento;
	private JButton btnLupa;
	private JLabel lblTexto;
	private JLabel lblNombre;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GestorVentas() {
		setLayout(null);
		{
			panel = new JPanel();
			panel.setBackground(new Color(249, 221, 181));
			panel.setBorder(new LineBorder(new Color(128, 128, 128)));
			panel.setBounds(10, 11, 450, 406);
			add(panel);
			panel.setLayout(null);
			{
				lblNewLabel = new JLabel("BUSCAR PRENDA");
				lblNewLabel.setBounds(10, 10, 98, 14);
				panel.add(lblNewLabel);
			}
			{
				txtBuscarPrenda = new JTextField();
				txtBuscarPrenda.setBorder(new LineBorder(new Color(255, 255, 255), 20, true));
				txtBuscarPrenda.setBounds(10, 31, 168, 29);
				panel.add(txtBuscarPrenda);
				txtBuscarPrenda.setColumns(10);
			}
			{
				btnNewButton = new JButton("Buscar");
				btnNewButton.setBounds(188, 31, 98, 29);
				panel.add(btnNewButton);
			}
			{
				btnNewButton_1 = new JButton("+ Agregar al Carrito");
				btnNewButton_1.setBorderPainted(false);
				btnNewButton_1.setBackground(new Color(128, 206, 130));
				btnNewButton_1.setBounds(296, 31, 144, 29);
				panel.add(btnNewButton_1);
			}
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 82, 430, 313);
				panel.add(scrollPane);
				{
					tablaVenta = new JTable();
					scrollPane.setViewportView(tablaVenta);
				}
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(249, 230, 166));
			panel_1.setBounds(500, 11, 326, 220);
			add(panel_1);
			panel_1.setLayout(null);
			{
				lblNewLabel_1 = new JLabel("CARRITO");
				lblNewLabel_1.setBounds(10, 11, 46, 14);
				panel_1.add(lblNewLabel_1);
			}
			{
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 36, 306, 133);
				panel_1.add(scrollPane_1);
				{
					tablaCarrito = new JTable();
					scrollPane_1.setViewportView(tablaCarrito);
				}
			}
			{
				lblNewLabel_2 = new JLabel("TOTAL: S/. 0.00");
				lblNewLabel_2.setForeground(new Color(97, 97, 97));
				lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
				lblNewLabel_2.setBounds(197, 195, 154, 14);
				panel_1.add(lblNewLabel_2);
			}
		}
		{
			panel_2 = new JPanel();
			panel_2.setBackground(new Color(248, 232, 177));
			panel_2.setBounds(470, 242, 356, 263);
			add(panel_2);
			panel_2.setLayout(null);
			{
				lblNewLabel_3 = new JLabel("COMPROBANTE DE PAGO");
				lblNewLabel_3.setBounds(10, 11, 162, 14);
				panel_2.add(lblNewLabel_3);
			}
			{
				tbBoleta = new JToggleButton("Boleta Simple");
				tbBoleta.setBounds(10, 33, 95, 23);
				panel_2.add(tbBoleta);
			}
			{
				tglbtnBoletaElect = new JToggleButton("Boleta Electrónica");
				tglbtnBoletaElect.setBounds(106, 33, 117, 23);
				panel_2.add(tglbtnBoletaElect);
			}
			{
				tglbtnFactura = new JToggleButton("Factura");
				tglbtnFactura.setBounds(227, 33, 89, 23);
				panel_2.add(tglbtnFactura);
			}
			{
				panel_3 = new JPanel();
				panel_3.setBounds(10, 67, 323, 185);
				panel_2.add(panel_3);
				panel_3.setLayout(null);
				{
					lblDniruc = new JLabel("DNI");
					lblDniruc.setBounds(10, 11, 46, 14);
					panel_3.add(lblDniruc);
				}
				{
					txtDocumento = new JTextField();
					txtDocumento.setBounds(10, 36, 107, 20);
					panel_3.add(txtDocumento);
					txtDocumento.setColumns(10);
				}
				{
					btnLupa = new JButton("🔍");
					btnLupa.setBounds(119, 36, 43, 20);
					panel_3.add(btnLupa);
				}
				{
					lblTexto = new JLabel("");
					lblTexto.setBounds(10, 65, 288, 14);
					panel_3.add(lblTexto);
				}
				{
					lblNombre = new JLabel("Nombres y Apellidos");
					lblNombre.setBounds(10, 79, 107, 14);
					panel_3.add(lblNombre);
				}
				{
					textField = new JTextField();
					textField.setBounds(10, 98, 134, 20);
					panel_3.add(textField);
					textField.setColumns(10);
				}
			}
		}
	
		configurarTabla();
		
	

	}
	private void configurarTabla() {
	    // Tabla de productos (búsqueda)
	    tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
	        new Object[][]{},
	        new String[]{"CÓDIGO", "NOMBRE", "TALLA", "PRECIO", "STOCK"}
	    ) {
	        public boolean isCellEditable(int r, int c) { return false; }
	    });

	     //Tabla del carrito
	   tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
	       new Object[][]{},
	       new String[]{"PRENDA", "TALLA", "CANTIDAD", "SUBTOTAL"}
	    ) {
	       public boolean isCellEditable(int r, int c) { return false; }
	    });
	}
}
