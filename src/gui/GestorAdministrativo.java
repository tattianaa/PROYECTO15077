package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Prenda;
import modelo.Variante;
import logica.InventarioLogica;

public class GestorAdministrativo extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    // gridPanel eliminado — ya no se usa el catálogo de tarjetas
    private DefaultTableModel modeloTabla;

    // Instancia de la lógica de inventario
    private InventarioLogica gestor = new InventarioLogica();

    // Instancia de la lógica de proveedores — compartida entre GestorProveedores y GestorEntradas
    private logica.ProveedorLogica gestorProveedores = new logica.ProveedorLogica();

    // Guarda los iconos de imagen por índice para mostrarlos en la tabla y detalles
    private java.util.Map<Integer, ImageIcon[]> iconosPorFila = new java.util.HashMap<>();

    // Botones del sidebar — declarados como campos para conectarlos al CardLayout

    private JButton btnInicio;
    private JButton btnPedidos;
    private JButton btnProveedores;
    private JButton btnInventarios;
    private JButton btnCerrar;
    private JButton btnGestion;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try { new GestorAdministrativo().setVisible(true); }
            catch (Exception e) { e.printStackTrace(); }
        });
    }

    public GestorAdministrativo() {
        setTitle("Proyecto Final - Speakers Moda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 242, 225));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        construirSidebar();
        construirPanelPrincipal();
    }

    // CONSTRUCCIÓN DE LA INTERFAZ
 
    //Construye el sidebar con los botones de navegación
    private JPanel construirSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 240, 650);
        contentPane.add(sidebar);

        JLabel lblLogo = new JLabel("🔊 SPEAKERS");
        lblLogo.setFont(new Font("Arial Black", Font.BOLD, 15));
        lblLogo.setBounds(15, 15, 175, 40);
        sidebar.add(lblLogo);

        JSeparator sep = new JSeparator();
        sep.setBounds(10, 58, 180, 2);
        sidebar.add(sep);

        // Botones de navegación del sidebar — declarados individualmente
        // para que el diseñador visual de Eclipse los reconozca

        JButton btnGestion = new JButton("📋  PANEL DE GESTIÓN");
        btnGestion.setFont(new Font("Arial", Font.PLAIN, 12));
        btnGestion.setForeground(new Color(60, 60, 60));
        btnGestion.setBackground(Color.WHITE);
        btnGestion.setBorderPainted(false);
        btnGestion.setFocusPainted(false);
        btnGestion.setOpaque(true);
        btnGestion.setHorizontalAlignment(SwingConstants.LEFT);
        btnGestion.setBounds(5, 65, 228, 30);
        sidebar.add(btnGestion);

        JButton btnPedidos = new JButton("📦  GESTIÓN DE PEDIDOS");
        btnPedidos.setFont(new Font("Arial", Font.PLAIN, 12));
        btnPedidos.setForeground(new Color(60, 60, 60));
        btnPedidos.setBackground(Color.WHITE);
        btnPedidos.setBorderPainted(false);
        btnPedidos.setFocusPainted(false);
        btnPedidos.setOpaque(true);
        btnPedidos.setHorizontalAlignment(SwingConstants.LEFT);
        btnPedidos.setBounds(5, 100, 228, 30);
        sidebar.add(btnPedidos);

        JButton btnProveedores = new JButton("🏭  GESTIÓN DE PROVEEDORES");
        btnProveedores.setFont(new Font("Arial", Font.PLAIN, 12));
        btnProveedores.setForeground(new Color(60, 60, 60));
        btnProveedores.setBackground(Color.WHITE);
        btnProveedores.setBorderPainted(false);
        btnProveedores.setFocusPainted(false);
        btnProveedores.setOpaque(true);
        btnProveedores.setHorizontalAlignment(SwingConstants.LEFT);
        btnProveedores.setBounds(5, 135, 228, 30);
        sidebar.add(btnProveedores);

        JButton btnInventarios = new JButton("📥  GESTIÓN DE INVENTARIOS");
        btnInventarios.setFont(new Font("Arial", Font.PLAIN, 12));
        btnInventarios.setForeground(new Color(60, 60, 60));
        btnInventarios.setBackground(Color.WHITE);
        btnInventarios.setBorderPainted(false);
        btnInventarios.setFocusPainted(false);
        btnInventarios.setOpaque(true);
        btnInventarios.setHorizontalAlignment(SwingConstants.LEFT);
        btnInventarios.setBounds(5, 170, 228, 30);
        sidebar.add(btnInventarios);

        JButton btnConfig = new JButton("⚙️  CONFIGURACIÓN");
        btnConfig.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConfig.setForeground(new Color(60, 60, 60));
        btnConfig.setBackground(Color.WHITE);
        btnConfig.setBorderPainted(false);
        btnConfig.setFocusPainted(false);
        btnConfig.setOpaque(true);
        btnConfig.setHorizontalAlignment(SwingConstants.LEFT);
        btnConfig.setBounds(5, 205, 228, 30);
        sidebar.add(btnConfig);

        JButton btnCerrar = new JButton("🚪  CERRAR SESIÓN");
        btnCerrar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCerrar.setForeground(new Color(60, 60, 60));
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrar.setBounds(5, 240, 228, 30);
        sidebar.add(btnCerrar);

        // Guardar referencia a los botones para conectarlos en construirPanelPrincipal
        this.btnPedidos     = btnPedidos;
        this.btnProveedores = btnProveedores;
        this.btnInventarios = btnInventarios;
        this.btnCerrar      = btnCerrar;
        this.btnGestion     = btnGestion;

        return sidebar;
    }


    //Construye el panel principal con CardLayout (pedidos, gestión, proveedores, entradas)
    private void construirPanelPrincipal() {
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBounds(242, 0, 858, 650);
        contentPane.add(mainPanel);

        // Panel de gestión es el principal
        mainPanel.add(construirPanelGestion(),                       "gestion");
        mainPanel.add(new GestorPedidos(),                           "pedidos");
        mainPanel.add(new GestorProveedores(gestorProveedores),      "proveedores");
        mainPanel.add(new GestorEntradas(gestor, gestorProveedores), "entradas");
        mainPanel.add(new GestorPedidos(),                           "pedidos");
        mainPanel.add(new GestorProveedores(gestorProveedores),      "proveedores");
        mainPanel.add(new GestorEntradas(gestor, gestorProveedores), "entradas");

        // Navegación entre paneles usando CardLayout
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        // Mostrar panel de gestión al abrir
        cl.show(mainPanel, "gestion");

        // Conectar cada botón del sidebar directamente a su panel
        btnPedidos.addActionListener(e     -> cl.show(mainPanel, "pedidos"));
        btnProveedores.addActionListener(e -> cl.show(mainPanel, "proveedores"));
        btnInventarios.addActionListener(e -> cl.show(mainPanel, "entradas"));
        btnGestion.addActionListener(e     -> cl.show(mainPanel, "gestion"));
        btnCerrar.addActionListener(e      -> { new Login().setVisible(true); dispose(); });
    }

    // PANEL GESTIÓN (tabla con EDITAR/ELIMINAR/DETALLES y buscador)

    private JPanel construirPanelGestion() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245, 242, 225));

        // Campo para buscar por nombre o código
        JTextField txtBuscarG = new JTextField("Ingresa código o nombre de la prenda...");
        txtBuscarG.setFont(new Font("Arial", Font.PLAIN, 13));
        txtBuscarG.setForeground(new Color(150, 150, 150));
        txtBuscarG.setBackground(Color.WHITE);
        txtBuscarG.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtBuscarG.setBounds(10, 15, 250, 32);
        panel.add(txtBuscarG);

        // Al hacer clic se borra el placeholder
        txtBuscarG.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txtBuscarG.getText().equals("Ingresa código o nombre de la prenda...")) {
                    txtBuscarG.setText("");
                    txtBuscarG.setForeground(Color.BLACK);
                }
            }
            // Si queda vacío al perder foco, vuelve el placeholder
            public void focusLost(FocusEvent e) {
                if (txtBuscarG.getText().isEmpty()) {
                    txtBuscarG.setText("Ingresa código o nombre de la prenda...");
                    txtBuscarG.setForeground(new Color(150, 150, 150));
                }
            }
        });

        // Botón para ejecutar la búsqueda
        JButton btnBuscarG = new JButton("BUSCAR");
        btnBuscarG.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscarG.setBackground(new Color(220, 190, 195));
        btnBuscarG.setForeground(new Color(50, 50, 50));
        btnBuscarG.setBorderPainted(false);
        btnBuscarG.setFocusPainted(false);
        btnBuscarG.setOpaque(true);
        btnBuscarG.setBounds(270, 15, 80, 32);
        panel.add(btnBuscarG);

        // Etiqueta y combo para filtrar por categoría
        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCat.setBounds(365, 20, 70, 20);
        panel.add(lblCat);

        String[] cats = {"TODAS", "NEW DROP", "COLECCIONES", "PANTALONES & JEANS",
                         "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
        JComboBox<String> comboCatG = new JComboBox<>(cats);
        comboCatG.setBounds(440, 15, 180, 32);
        panel.add(comboCatG);

        // Botón para agregar nueva prenda
        JButton btnNuevaG = new JButton("+ NUEVA PRENDA");
        btnNuevaG.setFont(new Font("Arial", Font.BOLD, 12));
        btnNuevaG.setBackground(new Color(130, 190, 140));
        btnNuevaG.setForeground(Color.WHITE);
        btnNuevaG.setBorderPainted(false);
        btnNuevaG.setFocusPainted(false);
        btnNuevaG.setOpaque(true);
        btnNuevaG.setBounds(680, 12, 165, 36);
        panel.add(btnNuevaG);
        btnNuevaG.addActionListener(e -> mostrarFormularioNueva());

        // Definición de columnas — DETALLES es una columna separada de ACCIONES
        String[] colsG = {"ID", "IMAGEN", "NOMBRE", "STOCK", "PRECIO", "CATEGORÍA", "ACCIONES", "DETALLES"};
        modeloTabla = new DefaultTableModel(colsG, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
            public Class<?> getColumnClass(int col) {
                return col == 1 ? ImageIcon.class : String.class;
            }
        };

        JTable tablaGestion = new JTable(modeloTabla);
        tablaGestion.setRowHeight(55);
        tablaGestion.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaGestion.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaGestion.getTableHeader().setBackground(new Color(240, 235, 225));
        tablaGestion.setBackground(Color.WHITE);
        tablaGestion.setGridColor(new Color(220, 220, 220));
        tablaGestion.setSelectionBackground(new Color(220, 190, 195));

        // Renderer: muestra la imagen en la columna 1
        tablaGestion.getColumnModel().getColumn(1).setCellRenderer(
            new javax.swing.table.DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable t, Object v,
                        boolean sel, boolean foc, int r, int c) {
                    JLabel lbl = new JLabel();
                    lbl.setHorizontalAlignment(SwingConstants.CENTER);
                    if (v instanceof ImageIcon) lbl.setIcon((ImageIcon) v);
                    return lbl;
                }
            });

        // Renderer: muestra botones EDITAR y ELIMINAR en la columna 6
        tablaGestion.getColumnModel().getColumn(6).setCellRenderer(
            (t, v, sel, foc, r, c) -> {
                JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 10));
                p.setBackground(Color.WHITE);
                JButton bE  = crearBotonTabla("EDITAR",   new Color(220, 190, 195));
                JButton bEl = crearBotonTabla("ELIMINAR", new Color(180, 180, 185));
                p.add(bE); p.add(bEl);
                return p;
            });

        // Renderer: muestra botón VER DETALLES grande en la columna 7
        tablaGestion.getColumnModel().getColumn(7).setCellRenderer(
            (t, v, sel, foc, r, c) -> {
                JPanel p = new JPanel(new BorderLayout());
                p.setBackground(Color.WHITE);
                p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                JButton bD = new JButton("<html><center>VER<br>DETALLES</center></html>");
                bD.setBackground(new Color(100, 160, 220));
                bD.setForeground(Color.WHITE);
                bD.setBorderPainted(false);
                bD.setFocusPainted(false);
                bD.setFont(new Font("Arial", Font.BOLD, 11));
                p.add(bD, BorderLayout.CENTER);
                return p;
            });

        // Anchos de columna
        int[] anchos = {70, 65, 140, 55, 75, 130, 160, 110};
        for (int i = 0; i < anchos.length; i++)
            tablaGestion.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        // Detecta clic en columna ACCIONES (6) o DETALLES (7)
        tablaGestion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaGestion.rowAtPoint(e.getPoint());
                int col = tablaGestion.columnAtPoint(e.getPoint());
                if (row < 0) return;
                if (col == 6) {
                    // Detecta si es EDITAR o ELIMINAR por posición X
                    int x = e.getX() - tablaGestion.getCellRect(row, col, true).x;
                    if (x < 80) {
                        // EDITAR: abrir formulario con datos actuales
                        Prenda p = gestor.getPrendas().get(row);
                        mostrarFormularioEditar(row, p.getNombre(),
                            String.valueOf(p.getPrecio()),
                            String.valueOf(p.getStock()),
                            p.getCodigo(),
                            p.getCategoria(), null, null, null, null);
                    } else {
                        // ELIMINAR: confirmar y eliminar
                        int ok = JOptionPane.showConfirmDialog(null,
                            "¿Eliminar esta prenda?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (ok == JOptionPane.YES_OPTION) {
                            gestor.gestionar(row);
                            iconosPorFila.remove(row);
                            refrescarVista();
                            JOptionPane.showMessageDialog(null, "Prenda eliminada correctamente.");
                        }
                    }
                } else if (col == 7) {
                    // VER DETALLES: abrir ventana con info completa de la prenda
                    Prenda p = gestor.getPrendas().get(row);
                    ImageIcon[] iconos = iconosPorFila.get(row);
                    ImageIcon img = (iconos != null) ? iconos[0] : null;
                    verDetallesPrenda(p, img);
                }
            }
        });

        JScrollPane scrollG = new JScrollPane(tablaGestion);
        scrollG.setBounds(10, 60, 848, 570);
        scrollG.setBorder(new LineBorder(new Color(220, 220, 220)));
        panel.add(scrollG);

        // Lógica del buscador — filtra la tabla por texto y categoría
        Runnable filtrar = () -> {
            // Leer texto — si tiene placeholder se trata como vacío
            String texto = txtBuscarG.getText().trim().equals("Ingresa código o nombre de la prenda...") ? "" : txtBuscarG.getText().trim();
            // Leer categoría seleccionada
            String categoria = (String) comboCatG.getSelectedItem();

            // Llamar al método sobrecargado que filtra por texto y categoría
            java.util.List<Prenda> resultado = gestor.gestionar(texto, categoria);

            // Si no hay resultados mostrar ventanita y limpiar campo
            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron prendas con ese criterio.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                txtBuscarG.setText("Ingresa código o nombre de la prenda...");
                txtBuscarG.setForeground(new Color(150, 150, 150));
            } else {
                // Limpiar tabla y mostrar solo las prendas que coinciden
                modeloTabla.setRowCount(0);
                for (Prenda p : resultado) {
                    int idx = gestor.getPrendas().indexOf(p);
                    ImageIcon[] iconos = iconosPorFila.get(idx);
                    ImageIcon iconTabla = (iconos != null) ? iconos[0] : null;
                    modeloTabla.addRow(new Object[]{
                        p.getCodigo(), iconTabla, p.getNombre(),
                        String.valueOf(p.getStock()), "S/. " + p.getPrecio(),
                        p.getCategoria(), "", ""
                    });
                }
            }
        };

        // Botón BUSCAR ejecuta el filtro
        btnBuscarG.addActionListener(e -> filtrar.run());

        // El combo filtra automáticamente al cambiar la categoría
        comboCatG.addActionListener(e -> filtrar.run());

        return panel;
    }

    //Crea un botón pequeño para usar dentro de las celdas de la tabla
    private JButton crearBotonTabla(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(new Color(50, 50, 50));
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        return btn;
    }
    // FORMULARIO: NUEVA PRENDA

    // Abre el diálogo para registrar una nueva prenda — sin stock, ese se genera por entradas
    private void mostrarFormularioNueva() {
        JDialog dialog = new JDialog(this, "Nueva Prenda", true);
        dialog.setSize(480, 380);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dialog.setContentPane(panel);

        JTextField txtNombre = agregarCampo(panel, "Nombre:", 20, 20);
        JTextField txtCodigo = agregarCampo(panel, "Código (ej: ABC123):", 20, 80);
        JTextField txtPrecio = agregarCampo(panel, "Precio:", 20, 140);
        txtPrecio.setBounds(20, 162, 350, 30);

        // Convertir a mayúsculas mientras escribe
        txtNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { txtNombre.setText(txtNombre.getText().toUpperCase()); }
        });
        txtCodigo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { txtCodigo.setText(txtCodigo.getText().toUpperCase()); }
        });

        JLabel lCat = new JLabel("Categoría:");
        lCat.setBounds(20, 200, 100, 20);
        panel.add(lCat);
        String[] categorias = {"NEW DROP", "COLECCIONES", "PANTALONES & JEANS",
                               "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
        JComboBox<String> comboCat = new JComboBox<>(categorias);
        comboCat.setBounds(20, 220, 350, 30);
        panel.add(comboCat);

        // Selector de imagen
        JLabel lblRuta = new JLabel("Sin imagen seleccionada");
        lblRuta.setFont(new Font("Arial", Font.ITALIC, 11));
        lblRuta.setForeground(new Color(150, 150, 150));
        lblRuta.setBounds(20, 265, 230, 20);
        panel.add(lblRuta);

        final File[] imagenSeleccionada = {null};
        JButton btnImg = new JButton("Seleccionar imagen");
        btnImg.setBounds(260, 261, 150, 26);
        btnImg.setFocusPainted(false);
        btnImg.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
            if (fc.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                imagenSeleccionada[0] = fc.getSelectedFile();
                lblRuta.setText(fc.getSelectedFile().getName());
            }
        });
        panel.add(btnImg);

        JButton btnGuardar  = crearBotonFormulario("GUARDAR",  new Color(130, 190, 140), 20,  300);
        JButton btnCancelar = crearBotonFormulario("CANCELAR", new Color(220, 100, 100), 210, 300);
        panel.add(btnGuardar);
        panel.add(btnCancelar);

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnGuardar.addActionListener(e -> {
            String nombre    = txtNombre.getText().trim().toUpperCase();
            String codigo    = txtCodigo.getText().trim().toUpperCase();
            String precio    = txtPrecio.getText().trim();
            String categoria = (String) comboCat.getSelectedItem();

            // Validar datos básicos
            String resultado = gestor.validarTodo(nombre, codigo, precio);
            if (!resultado.equals("OK")) {
                JOptionPane.showMessageDialog(dialog, resultado);
                return;
            }

            // Agrega la prenda sin variantes — el stock se generará por entradas de mercadería
            gestor.gestionar(codigo, nombre, Double.parseDouble(precio), categoria, new java.util.ArrayList<>());

            // Guardar iconos para esta prenda
            int nuevaFila = gestor.getPrendas().size() - 1;
            iconosPorFila.put(nuevaFila, new ImageIcon[]{
                cargarIcono(imagenSeleccionada[0], 50, 50, dialog),
                cargarIcono(imagenSeleccionada[0], 183, 140, dialog)
            });

            refrescarVista();
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    // FORMULARIO: EDITAR PRENDA

    // Abre el formulario para editar una prenda existente
    private void mostrarFormularioEditar(int fila, String nombreActual, String precioActual,
            String stockActual, String codigoActual, String categoriaActual,
            JLabel lblNombre, JLabel lblPrecio, JLabel lblInfo, JLabel imgLabel) {

        JDialog dlg = new JDialog(this, "Editar Prenda", true);
        dlg.setSize(480, 580);
        dlg.setLocationRelativeTo(this);
        dlg.setResizable(false);

        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        dlg.setContentPane(p);

        JTextField tNombre = new JTextField(nombreActual);
        tNombre.setBounds(20, 40, 430, 30);
        JLabel lNombre = new JLabel("Nombre:");
        lNombre.setBounds(20, 20, 100, 20);
        p.add(lNombre);
        p.add(tNombre);

        JTextField tPrecio = new JTextField(precioActual);
        tPrecio.setBounds(20, 100, 430, 30);
        JLabel lPrecio = new JLabel("Precio:");
        lPrecio.setBounds(20, 80, 100, 20);
        p.add(lPrecio);
        p.add(tPrecio);

        // El código no se puede editar (es el identificador único)
        JTextField tId = new JTextField(codigoActual);
        tId.setEnabled(false);
        tId.setBackground(new Color(230, 230, 230));
        tId.setBounds(20, 160, 430, 30);
        JLabel lId = new JLabel("Código (no editable):");
        lId.setBounds(20, 140, 180, 20);
        p.add(lId);
        p.add(tId);

        String[] categorias = {"NEW DROP", "COLECCIONES", "PANTALONES & JEANS",
                               "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
        JComboBox<String> combo = new JComboBox<>(categorias);
        combo.setSelectedItem(categoriaActual);
        combo.setBounds(20, 220, 430, 30);
        JLabel lCat2 = new JLabel("Categoría:");
        lCat2.setBounds(20, 200, 100, 20);
        p.add(lCat2);
        p.add(combo);

        // Panel de variantes con los valores actuales de la prenda
        JLabel lStock = new JLabel("Stock por talla:");
        lStock.setFont(new Font("Arial", Font.BOLD, 12));
        lStock.setBounds(20, 258, 200, 20);
        p.add(lStock);

        JPanel panelVariantes = new JPanel(null);
        panelVariantes.setBackground(new Color(245, 245, 245));
        panelVariantes.setBounds(20, 278, 430, 160);
        p.add(panelVariantes);

        // Cargar variantes actuales de la prenda para mostrarlas en el formulario
        List<Variante> variantesActuales = gestor.getPrendas().get(fila).getVariantes();
        construirCamposVariantes(panelVariantes, categoriaActual, variantesActuales);

        // Cuando cambia la categoría, regenerar los campos de talla
        combo.addActionListener(e ->
            construirCamposVariantes(panelVariantes, (String) combo.getSelectedItem(), null));

        final File[] nuevaImg = {null};
        JButton btnCambiarImg = new JButton("Cambiar Imagen");
        btnCambiarImg.setBounds(20, 448, 150, 28);
        btnCambiarImg.setFocusPainted(false);
        btnCambiarImg.addActionListener(ev -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
            if (fc.showOpenDialog(dlg) == JFileChooser.APPROVE_OPTION)
                nuevaImg[0] = fc.getSelectedFile();
        });
        p.add(btnCambiarImg);

        JButton btnGuardar = crearBotonFormulario("GUARDAR CAMBIOS", new Color(130, 190, 140), 20,  490);
        JButton btnCancel  = crearBotonFormulario("CANCELAR",        new Color(220, 100, 100), 210, 490);
        p.add(btnGuardar);
        p.add(btnCancel);

        btnCancel.addActionListener(ev -> dlg.dispose());

        btnGuardar.addActionListener(ev -> {
            String nombre    = tNombre.getText().trim();
            String precio    = tPrecio.getText().trim();
            String categoria = combo.getSelectedItem().toString();
            String codigo    = tId.getText().trim();

            // Validar nombre y precio (sin stock, ese viene en variantes)
            String validacion = gestor.validarEdicion(nombre, precio);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(dlg, validacion);
                return;
            }

            // Leer variantes del panel
            List<Variante> variantes = leerVariantesDelPanel(panelVariantes);
            // Delegar validación de variantes a la lógica
            String resVariantes = gestor.validarVariantes(variantes);
            if (!resVariantes.equals("OK")) {
                JOptionPane.showMessageDialog(dlg, resVariantes);
                return;
            }

            // Delegar edición a la lógica
            boolean ok = gestor.editarPrenda(fila, codigo, nombre, precio, categoria, variantes);
            if (!ok) {
                JOptionPane.showMessageDialog(dlg, "Ese código ya existe.");
                return;
            }

            // Actualizar imagen si el usuario eligió una nueva
            if (nuevaImg[0] != null) {
                iconosPorFila.put(fila, new ImageIcon[]{
                    cargarIcono(nuevaImg[0], 50, 50, dlg),
                    cargarIcono(nuevaImg[0], 183, 140, dlg)
                });
            }

            // Refrescar toda la vista
            refrescarVista();
            JOptionPane.showMessageDialog(dlg, "Prenda actualizada correctamente.");
            dlg.dispose();
        });

        dlg.setVisible(true);
    }

   
    // ─────────────────────────────────────────
    // REFRESCO DE VISTA
    // ─────────────────────────────────────────

    // Reconstruye la tabla de gestión leyendo la lista desde InventarioLogica
    private void refrescarVista() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Leer la lista actualizada desde la lógica
        java.util.List<Prenda> prendas = gestor.getPrendas();

        for (int i = 0; i < prendas.size(); i++) {
            Prenda p = prendas.get(i);

            // Recuperar icono guardado para la tabla (puede ser null si no tiene imagen)
            ImageIcon[] iconos = iconosPorFila.get(i);
            ImageIcon iconTabla = (iconos != null) ? iconos[0] : null;

            // Agregar fila a la tabla de gestión — columna DETALLES vacía, el renderer la pinta
            modeloTabla.addRow(new Object[]{
                p.getCodigo(), iconTabla, p.getNombre(),
                String.valueOf(p.getStock()),
                "S/. " + p.getPrecio(),
                p.getCategoria(), "", ""
            });
        }
    }

    // ─────────────────────────────────────────
    // VER DETALLES DE PRENDA
    // ─────────────────────────────────────────

    // Abre un diálogo con toda la info de la prenda, filtro por talla/color,
    // y tablas de entradas y salidas
    private void verDetallesPrenda(Prenda prenda, ImageIcon imagen) {
        JDialog dlg = new JDialog(this, "Detalles de Prenda", true);
        dlg.setSize(520, 650);
        dlg.setLocationRelativeTo(this);
        dlg.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dlg.setContentPane(panel);

        // ── Imagen arriba ──
        JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
        lblImagen.setBackground(new Color(230, 230, 230));
        lblImagen.setOpaque(true);
        if (imagen != null) {
            Image img = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        }
        lblImagen.setBounds(200, 10, 100, 100);
        panel.add(lblImagen);

        // ── Nombre y datos básicos ──
        JLabel lblTitulo = new JLabel(prenda.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitulo.setBounds(20, 115, 470, 22);
        panel.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código: " + prenda.getCodigo() + "   |   Categoría: " + prenda.getCategoria() + "   |   Precio: S/. " + prenda.getPrecio());
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblCodigo.setBounds(20, 140, 470, 18);
        panel.add(lblCodigo);

        // Stock total — se actualiza al filtrar
        JLabel lblStockTotal = new JLabel("Stock total: " + prenda.getStock() + " uds");
        lblStockTotal.setFont(new Font("Arial", Font.BOLD, 12));
        lblStockTotal.setForeground(new Color(80, 140, 80));
        lblStockTotal.setBounds(20, 160, 200, 18);
        panel.add(lblStockTotal);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 183, 470, 2);
        panel.add(sep);

        // ── Filtros talla y color ──
        JLabel lFiltro = new JLabel("Filtrar por:");
        lFiltro.setFont(new Font("Arial", Font.BOLD, 12));
        lFiltro.setBounds(20, 190, 80, 20);
        panel.add(lFiltro);

        // Combo de tallas — "TODAS" más las tallas de las variantes
        java.util.Set<String> tallasSet = new java.util.LinkedHashSet<>();
        tallasSet.add("TODAS");
        for (Variante v : prenda.getVariantes()) tallasSet.add(v.getTalla());
        JComboBox<String> comboTalla = new JComboBox<>(tallasSet.toArray(new String[0]));
        comboTalla.setBounds(105, 188, 100, 26);
        panel.add(comboTalla);

        // Combo de colores — "TODOS" más los colores de las variantes
        java.util.Set<String> coloresSet = new java.util.LinkedHashSet<>();
        coloresSet.add("TODOS");
        for (Variante v : prenda.getVariantes()) coloresSet.add(v.getColor());
        JComboBox<String> comboColor = new JComboBox<>(coloresSet.toArray(new String[0]));
        comboColor.setBounds(215, 188, 120, 26);
        panel.add(comboColor);

        // ── Tabla de stock por variante ──
        JLabel lblStock = new JLabel("Stock por variante:");
        lblStock.setFont(new Font("Arial", Font.BOLD, 12));
        lblStock.setBounds(20, 225, 200, 18);
        panel.add(lblStock);

        String[] colsStock = {"Talla", "Color", "Stock"};
        DefaultTableModel modeloStock = new DefaultTableModel(colsStock, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaStock = new JTable(modeloStock);
        tablaStock.setRowHeight(24);
        tablaStock.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaStock.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaStock.getTableHeader().setBackground(new Color(240, 235, 225));
        tablaStock.setBackground(Color.WHITE);
        tablaStock.setGridColor(new Color(220, 220, 220));
        JScrollPane scrollStock = new JScrollPane(tablaStock);
        scrollStock.setBounds(20, 245, 470, 90);
        panel.add(scrollStock);

        // ── Tabla de entradas (verde) ──
        JLabel lblEntradas = new JLabel("Entradas de mercadería:");
        lblEntradas.setFont(new Font("Arial", Font.BOLD, 12));
        lblEntradas.setForeground(new Color(60, 130, 60));
        lblEntradas.setBounds(20, 345, 220, 18);
        panel.add(lblEntradas);

        String[] colsEntradas = {"Proveedor", "Cantidad", "Fecha"};
        DefaultTableModel modeloEntradas = new DefaultTableModel(colsEntradas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaEntradas = new JTable(modeloEntradas);
        tablaEntradas.setRowHeight(24);
        tablaEntradas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEntradas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEntradas.getTableHeader().setBackground(new Color(220, 240, 220));
        tablaEntradas.setBackground(Color.WHITE);
        tablaEntradas.setGridColor(new Color(200, 230, 200));
        JScrollPane scrollEntradas = new JScrollPane(tablaEntradas);
        scrollEntradas.setBounds(20, 365, 470, 80);
        panel.add(scrollEntradas);

        // ── Tabla de salidas (rojo) ──
        JLabel lblSalidas = new JLabel("Salidas (ventas):");
        lblSalidas.setFont(new Font("Arial", Font.BOLD, 12));
        lblSalidas.setForeground(new Color(180, 60, 60));
        lblSalidas.setBounds(20, 453, 200, 18);
        panel.add(lblSalidas);

        String[] colsSalidas = {"Cliente", "Cantidad", "Fecha"};
        DefaultTableModel modeloSalidas = new DefaultTableModel(colsSalidas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaSalidas = new JTable(modeloSalidas);
        tablaSalidas.setRowHeight(24);
        tablaSalidas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaSalidas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaSalidas.getTableHeader().setBackground(new Color(240, 220, 220));
        tablaSalidas.setBackground(Color.WHITE);
        tablaSalidas.setGridColor(new Color(230, 200, 200));
        JScrollPane scrollSalidas = new JScrollPane(tablaSalidas);
        scrollSalidas.setBounds(20, 473, 470, 80);
        panel.add(scrollSalidas);

        // ── Botón cerrar ──
        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setBackground(new Color(220, 190, 195));
        btnCerrar.setForeground(new Color(50, 50, 50));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setBounds(185, 565, 120, 30);
        btnCerrar.addActionListener(e -> dlg.dispose());
        panel.add(btnCerrar);

        // ── Lógica del filtro ──
        // Rellena la tabla de stock según talla y color seleccionados
        Runnable filtrarVariantes = () -> {
            String talla = (String) comboTalla.getSelectedItem();
            String color = (String) comboColor.getSelectedItem();
            modeloStock.setRowCount(0);
            int stockFiltrado = 0;
            for (Variante v : prenda.getVariantes()) {
                boolean coincideTalla = talla.equals("TODAS") || v.getTalla().equals(talla);
                boolean coincideColor = color.equals("TODOS") || v.getColor().equalsIgnoreCase(color);
                if (coincideTalla && coincideColor) {
                    modeloStock.addRow(new Object[]{v.getTalla(), v.getColor(), v.getStock()});
                    stockFiltrado += v.getStock();
                }
            }
            if (modeloStock.getRowCount() == 0)
                modeloStock.addRow(new Object[]{"—", "Sin variantes", "—"});
            // Actualizar label de stock según filtro
            lblStockTotal.setText(talla.equals("TODAS") && color.equals("TODOS")
                ? "Stock total: " + prenda.getStock() + " uds"
                : "Stock filtrado: " + stockFiltrado + " uds");

            // TODO: filtrar entradas y salidas por talla+color cuando se conecte la lógica
        };

        // Aplicar filtro al cambiar combos
        comboTalla.addActionListener(e -> filtrarVariantes.run());
        comboColor.addActionListener(e -> filtrarVariantes.run());

        // Cargar datos iniciales
        filtrarVariantes.run();

        dlg.setVisible(true);
    }

    // ─────────────────────────────────────────
    // AUXILIARES DE VARIANTES
    // ─────────────────────────────────────────

    // Devuelve las tallas según la categoría — pantalones usan números, accesorios talla única
    private String[] tallasParaCategoria(String categoria) {
        if (categoria.contains("PANTALONES")) {
            return new String[]{"26", "28", "30", "32", "34"};
        } else if (categoria.equals("ACCESORIOS")) {
            return new String[]{"ÚNICA"};
        } else {
            return new String[]{"XS", "S", "M", "L", "XL"};
        }
    }

    // Construye los campos de talla+color+stock en el panel de variantes
    // Si se pasan variantes actuales (al editar), precarga los valores
    private void construirCamposVariantes(JPanel panelVariantes, String categoria,
                                          List<Variante> variantesActuales) {
        panelVariantes.removeAll();  // limpiar campos anteriores

        String[] tallas = tallasParaCategoria(categoria);
        int y = 5;

        for (int i = 0; i < tallas.length; i++) {
            String talla = tallas[i];

            // Etiqueta de talla
            JLabel lblTalla = new JLabel(talla);
            lblTalla.setFont(new Font("Arial", Font.BOLD, 11));
            lblTalla.setBounds(5, y, 35, 25);
            panelVariantes.add(lblTalla);

            // Campo de color
            JTextField txtColor = new JTextField("Color");
            txtColor.setForeground(new Color(150, 150, 150));
            txtColor.setBounds(45, y, 120, 25);
            txtColor.setName("color_" + i);  // nombre para identificarlo al leer

            // Campo de stock
            JTextField txtStock = new JTextField("0");
            txtStock.setBounds(175, y, 50, 25);
            txtStock.setName("stock_" + i);  // nombre para identificarlo al leer

            // Si hay variantes actuales (modo editar), precargar valores
            if (variantesActuales != null && i < variantesActuales.size()) {
                Variante v = variantesActuales.get(i);
                txtColor.setText(v.getColor());
                txtColor.setForeground(Color.BLACK);
                txtStock.setText(String.valueOf(v.getStock()));
            }

            // Limpiar placeholder al hacer clic
            txtColor.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (txtColor.getText().equals("Color")) {
                        txtColor.setText("");
                        txtColor.setForeground(Color.BLACK);
                    }
                }
            });

            panelVariantes.add(txtColor);
            panelVariantes.add(txtStock);
            y += 30;
        }

        panelVariantes.revalidate();
        panelVariantes.repaint();
    }

    // Lee los campos del panel de variantes y arma la lista de Variante
    private List<Variante> leerVariantesDelPanel(JPanel panelVariantes) {
        List<Variante> variantes = new java.util.ArrayList<>();
        Component[] componentes = panelVariantes.getComponents();

        // Los componentes están en orden: JLabel(talla), JTextField(color), JTextField(stock)
        for (int i = 0; i + 2 < componentes.length; i += 3) {
            String talla    = ((JLabel)     componentes[i]).getText();
            String color    = ((JTextField) componentes[i + 1]).getText().trim();
            String stockStr = ((JTextField) componentes[i + 2]).getText().trim();

            // Saltar filas completamente vacías (placeholder sin tocar)
            if (color.equals("Color") && stockStr.equals("0")) continue;

            // Convertir stock — si no es número válido se pone -1 para que la lógica lo detecte
            int stock;
            try {
                stock = Integer.parseInt(stockStr);
            } catch (NumberFormatException e) {
                stock = -1; // valor inválido, la lógica lo rechazará
            }

            variantes.add(new Variante(talla, color.equals("Color") ? "" : color, stock));
        }
        return variantes;
    }

    // ─────────────────────────────────────────
    // UTILIDADES DE GUI
    // ─────────────────────────────────────────

    // Carga y escala una imagen desde un archivo — retorna null si falla
    private ImageIcon cargarIcono(File archivo, int w, int h, Component parent) {
        if (archivo == null) return null;
        try {
            ImageIcon ic = new ImageIcon(archivo.toURI().toURL());
            MediaTracker mt = new MediaTracker(parent);
            mt.addImage(ic.getImage(), 0);
            mt.waitForAll();
            return new ImageIcon(ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //noseques
    private JTextField agregarCampo(JPanel panel, String etiqueta, int x, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(x, y, 150, 20);
        panel.add(lbl);
        JTextField txt = new JTextField();
        txt.setBounds(x, y + 22, 350, 30);
        panel.add(txt);
        return txt;
    }

    //formulario (guardar/cancelar)
    private JButton crearBotonFormulario(String texto, Color color, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBounds(x, y, 160, 36);
        return btn;
    }
}
