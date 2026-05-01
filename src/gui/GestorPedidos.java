package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// Panel de gestión de pedidos

// dentro de GestorAdministrativo usando CardLayout

// Se incrusta dentro de GestorAdministrativo usando CardLayout
public class GestorPedidos extends JPanel {

    public GestorPedidos() {
        setLayout(null);
        setBackground(new Color(245, 242, 225));

        // Campo para buscar por ID de pedido
        JTextField txtIdPedido = new JTextField("ID Pedido");
        txtIdPedido.setFont(new Font("Arial", Font.PLAIN, 13));
        txtIdPedido.setForeground(new Color(150, 150, 150));
        txtIdPedido.setBackground(Color.WHITE);
        txtIdPedido.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtIdPedido.setBounds(10, 15, 220, 32);
        add(txtIdPedido);

        // Campo para buscar por cliente
        JTextField txtIdCliente = new JTextField("ID/Cliente");
        txtIdCliente.setFont(new Font("Arial", Font.PLAIN, 13));
        txtIdCliente.setForeground(new Color(150, 150, 150));
        txtIdCliente.setBackground(Color.WHITE);
        txtIdCliente.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtIdCliente.setBounds(240, 15, 220, 32);
        add(txtIdCliente);

        // Datos de ejemplo — se reemplazarán cuando se conecte la base de datos
        String[] columnas = {"ID Pedido", "Fecha", "Cliente", "Total", "Estado"};
        Object[][] datos = {
            {"#101", "05/04/2026", "Juan Pérez", "S/. 152.10", "Pendiente"},
            {"#102", "05/04/2026", "Juan Pérez", "S/. 152.10", "Enviado"},
            {"#103", "05/04/2026", "Juan Pérez", "S/. 152.10", "Entregado"}
        };

        // Tabla que muestra la lista de pedidos
        JTable tabla = new JTable(datos, columnas);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.setBackground(Color.WHITE);
        tabla.setGridColor(new Color(220, 220, 220));

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(10, 58, 607, 402);
        scrollTabla.setBorder(new LineBorder(new Color(220, 220, 220)));
        add(scrollTabla);

        // Botones de acción del lado derecho
        agregarBoton("VER DETALLES",      605, 60);
        agregarBoton("ACTUALIZAR ESTADO", 605, 110);
        agregarBoton("GENERAR REPORTE",   605, 160);
    }

    // Crea un botón rosado y lo agrega al panel
    private void agregarBoton(String texto, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(new Color(220, 190, 195));
        btn.setForeground(new Color(50, 50, 50));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBounds(x, y, 170, 36);
        add(btn);
    }
}
