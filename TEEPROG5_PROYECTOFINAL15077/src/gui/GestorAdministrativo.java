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
    private JPanel gridPanel;
    private DefaultTableModel modeloTabla;

    // Instancia
    private InventarioLogica gestor = new InventarioLogica();

    // Guarda los iconos de imagen por índice para poder refrescar la vista
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
        setSize(1000, 650);
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

        JButton btnInicio = new JButton("🏠  INICIO");
        btnInicio.setFont(new Font("Arial", Font.PLAIN, 12));
        btnInicio.setForeground(new Color(60, 60, 60));
        btnInicio.setBackground(Color.WHITE);
        btnInicio.setBorderPainted(false);
        btnInicio.setFocusPainted(false);
        btnInicio.setOpaque(true);
        btnInicio.setHorizontalAlignment(SwingConstants.LEFT);
        btnInicio.setBounds(5, 65, 228, 30);
        sidebar.add(btnInicio);

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

        JButton btnGestion = new JButton("📋  PANEL DE GESTIÓN");
        btnGestion.setFont(new Font("Arial", Font.PLAIN, 12));
        btnGestion.setForeground(new Color(60, 60, 60));
        btnGestion.setBackground(Color.WHITE);
        btnGestion.setBorderPainted(false);
        btnGestion.setFocusPainted(false);
        btnGestion.setOpaque(true);
        btnGestion.setHorizontalAlignment(SwingConstants.LEFT);
        btnGestion.setBounds(5, 275, 228, 30);
        sidebar.add(btnGestion);

        // Guardar referencia a los botones para conectarlos en construirPanelPrincipal
        this.btnInicio      = btnInicio;
        this.btnPedidos     = btnPedidos;
        this.btnProveedores = btnProveedores;
        this.btnInventarios = btnInventarios;
        this.btnCerrar      = btnCerrar;
        this.btnGestion     = btnGestion;

        return sidebar;
    }

    //Construye el panel principal con CardLayout (prendas, pedidos, gestión)
    private void construirPanelPrincipal() {
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBounds(242, 0, 758, 650);
        contentPane.add(mainPanel);

        mainPanel.add(construirPanelPrendas(),   "prendas");
        mainPanel.add(new GestorPedidos(),       "pedidos");
        mainPanel.add(construirPanelGestion(),   "gestion");
        mainPanel.add(new GestorProveedores(),   "proveedores");
        mainPanel.add(new GestorEntradas(),      "entradas");

        // Navegación entre paneles usando CardLayout
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "prendas");

        // Conectar cada botón del sidebar directamente a su panel
        btnInicio.addActionListener(e      -> cl.show(mainPanel, "prendas"));
        btnPedidos.addActionListener(e     -> cl.show(mainPanel, "pedidos"));
        btnProveedores.addActionListener(e -> cl.show(mainPanel, "proveedores"));
        btnInventarios.addActionListener(e -> cl.show(mainPanel, "entradas"));
        btnGestion.addActionListener(e     -> cl.show(mainPanel, "gestion"));
        btnCerrar.addActionListener(e      -> { new Login().setVisible(true); dispose(); });
    }

  
    // PANEL CATÁLOGO (grid de tarjetas)
  

    private JPanel construirPanelPrendas() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245, 242, 225));

        JTextField txtBuscar = new JTextField("Buscar...");
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 13));
        txtBuscar.setForeground(new Color(150, 150, 150));
        txtBuscar.setBackground(Color.WHITE);
        txtBuscar.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtBuscar.setBounds(10, 15, 280, 32);
        panel.add(txtBuscar);

        JButton btnNueva = new JButton("+ NUEVA PRENDA");
        btnNueva.setFont(new Font("Arial", Font.BOLD, 13));
        btnNueva.setBackground(new Color(130, 190, 140));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setBorderPainted(false);
        btnNueva.setFocusPainted(false);
        btnNueva.setOpaque(true);
        btnNueva.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNueva.setBounds(620, 12, 165, 36);
        panel.add(btnNueva);

        // Grid donde se muestran las tarjetas de prendas
        gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        gridPanel.setBackground(new Color(245, 242, 225));
        JScrollPane scroll = new JScrollPane(gridPanel);
        scroll.setBounds(10, 60, 778, 575);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(245, 242, 225));
        panel.add(scroll);

        // Al hacer clic abre el formulario para agregar nueva prenda
        btnNueva.addActionListener(e -> mostrarFormularioNueva());

        return panel;
    }

    // PANEL GESTIÓN (tabla con EDITAR/ELIMINAR)

    private JPanel construirPanelGestion() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245, 242, 225));

        JTextField txtBuscarG = new JTextField("Buscar");
        txtBuscarG.setFont(new Font("Arial", Font.PLAIN, 13));
        txtBuscarG.setBackground(Color.WHITE);
        txtBuscarG.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtBuscarG.setBounds(10, 15, 200, 32);
        panel.add(txtBuscarG);

        JButton btnBuscarG = new JButton("Buscar");
        btnBuscarG.setBackground(new Color(220, 190, 195));
        btnBuscarG.setBorderPainted(false);
        btnBuscarG.setFocusPainted(false);
        btnBuscarG.setOpaque(true);
        btnBuscarG.setBounds(215, 15, 80, 32);
        panel.add(btnBuscarG);

        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCat.setBounds(310, 20, 70, 20);
        panel.add(lblCat);

        String[] cats = {"TODAS", "NEW DROP", "COLECCIONES", "PANTALONES & JEANS",
                         "TOPS & BODIES", "FALDAS & SHORTS", "VESTIDOS", "ACCESORIOS"};
        JComboBox<String> comboCatG = new JComboBox<>(cats);
        comboCatG.setBounds(385, 15, 180, 32);
        panel.add(comboCatG);

        JButton btnNuevaG = new JButton("+ NUEVA PRENDA");
        btnNuevaG.setFont(new Font("Arial", Font.BOLD, 12));
        btnNuevaG.setBackground(new Color(130, 190, 140));
        btnNuevaG.setForeground(Color.WHITE);
        btnNuevaG.setBorderPainted(false);
        btnNuevaG.setFocusPainted(false);
        btnNuevaG.setOpaque(true);
        btnNuevaG.setBounds(600, 12, 165, 36);
        panel.add(btnNuevaG);
        btnNuevaG.addActionListener(e -> mostrarFormularioNueva());

        // Definición de columnas de la tabla de gestión
        String[] colsG = {"ID", "IMAGEN", "NOMBRE", "STOCK", "PRECIO", "CATEGORÍA", "ACCIONES"};
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
                JButton bE  = crearBotonTabla("EDITAR",    new Color(220, 190, 195));
                JButton bEl = crearBotonTabla("ELIMINAR",  new Color(180, 180, 185));
                p.add(bE); p.add(bEl);
                return p;
            });

        // Anchos de columna
        int[] anchos = {80, 70, 150, 60, 80, 140, 160};
        for (int i = 0; i < anchos.length; i++)
            tablaGestion.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        // Detecta clic en columna ACCIONES y decide si es EDITAR o ELIMINAR
        tablaGestion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaGestion.rowAtPoint(e.getPoint());
                int col = tablaGestion.columnAtPoint(e.getPoint());
                if (col != 6 || row < 0) return;

                int x = e.getX() - tablaGestion.getCellRect(row, col, true).x;
                if (x < 80) {
                    // EDITAR: leer datos de la lógica y abrir formulario
                    Prenda p = gestor.getPrendas().get(row);
                    mostrarFormularioEditar(row, p.getNombre(),
                        String.valueOf(p.getPrecio()),
                        String.valueOf(p.getStock()),
                        p.getCodigo(),
                        p.getCategoria(), null, null, null, null);
                } else {
                    // ELIMINAR: confirmar, delegar a lógica y refrescar
                    int ok = JOptionPane.showConfirmDialog(null,
                        "¿Eliminar esta prenda?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (ok == JOptionPane.YES_OPTION) {
                        // Llama a gestionar(int) — método sobrecargado que elimina por posición
                        gestor.gestionar(row);
                        iconosPorFila.remove(row);
                        refrescarVista();
                        JOptionPane.showMessageDialog(null, "Se eliminó de la tabla y del catálogo.");
                    }
                }
            }
        });

        JScrollPane scrollG = new JScrollPane(tablaGestion);
        scrollG.setBounds(10, 60, 778, 570);
        scrollG.setBorder(new LineBorder(new Color(220, 220, 220)));
        panel.add(scrollG);

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

    /** Abre el diálogo para registrar una nueva prenda con variantes por talla y color */
    private void mostrarFormularioNueva() {
        JDialog dialog = new JDialog(this, "Nueva Prenda", true);
        dialog.setSize(480, 620);
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

        // Panel donde aparecen los campos de talla+color dinámicamente
        JLabel lStock = new JLabel("Stock por talla:");
        lStock.setFont(new Font("Arial", Font.BOLD, 12));
        lStock.setBounds(20, 260, 200, 20);
        panel.add(lStock);

        JPanel panelVariantes = new JPanel(null);
        panelVariantes.setBackground(new Color(245, 245, 245));
        panelVariantes.setBounds(20, 282, 430, 180);
        panel.add(panelVariantes);

        // Cuando cambia la categoría, se regeneran los campos de talla
        comboCat.addActionListener(e ->
            construirCamposVariantes(panelVariantes, (String) comboCat.getSelectedItem(), null));
        // Mostrar tallas de la categoría inicial al abrir el formulario
        construirCamposVariantes(panelVariantes, (String) comboCat.getSelectedItem(), null);

        // Selector de imagen
        JLabel lblRuta = new JLabel("Sin imagen seleccionada");
        lblRuta.setFont(new Font("Arial", Font.ITALIC, 11));
        lblRuta.setForeground(new Color(150, 150, 150));
        lblRuta.setBounds(20, 470, 230, 20);
        panel.add(lblRuta);

        final File[] imagenSeleccionada = {null};
        JButton btnImg = new JButton("Seleccionar imagen");
        btnImg.setBounds(260, 466, 150, 26);
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

        JButton btnGuardar  = crearBotonFormulario("GUARDAR",  new Color(130, 190, 140), 20,  500);
        JButton btnCancelar = crearBotonFormulario("CANCELAR", new Color(220, 100, 100), 210, 500);
        panel.add(btnGuardar);
        panel.add(btnCancelar);

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnGuardar.addActionListener(e -> {
            String nombre    = txtNombre.getText().trim().toUpperCase();
            String codigo    = txtCodigo.getText().trim().toUpperCase();
            String precio    = txtPrecio.getText().trim();
            String categoria = (String) comboCat.getSelectedItem();

            // Validar datos básicos (sin stock, ese viene en variantes)
            String resultado = gestor.validarTodo(nombre, codigo, precio);
            if (!resultado.equals("OK")) {
                JOptionPane.showMessageDialog(dialog, resultado);
                return;
            }

            // Leer las variantes que el usuario llenó en el panel
            List<Variante> variantes = leerVariantesDelPanel(panelVariantes);
            // Delegar validación de variantes a la lógica
            String resVariantes = gestor.validarVariantes(variantes);
            if (!resVariantes.equals("OK")) {
                JOptionPane.showMessageDialog(dialog, resVariantes);
                return;
            }

            // Llama a gestionar(String, String, double, String, List) — método sobrecargado que agrega la prenda
            gestor.gestionar(codigo, nombre, Double.parseDouble(precio), categoria, variantes);

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

    /** Abre el diálogo para editar una prenda existente */
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

    /**
     * Reconstruye la tabla de gestión y el grid de tarjetas
     * leyendo la lista de prendas desde InventarioLogica.
     * La GUI nunca escribe datos directamente: solo lee y pinta.
     */
    private void refrescarVista() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Limpiar grid de tarjetas
        gridPanel.removeAll();

        // Leer la lista actualizada desde la lógica
        java.util.List<Prenda> prendas = gestor.getPrendas();

        for (int i = 0; i < prendas.size(); i++) {
            Prenda p = prendas.get(i);

            // Recuperar iconos guardados (pueden ser null si no tiene imagen)
            ImageIcon[] iconos = iconosPorFila.get(i);
            ImageIcon iconTabla   = (iconos != null) ? iconos[0] : null;
            ImageIcon iconTarjeta = (iconos != null) ? iconos[1] : null;

            // Agregar fila a la tabla de gestión
            modeloTabla.addRow(new Object[]{
                p.getCodigo(), iconTabla, p.getNombre(),
                String.valueOf(p.getStock()),
                "S/. " + p.getPrecio(),
                p.getCategoria(), ""
            });

            // Agregar tarjeta al catálogo
            gridPanel.add(crearTarjeta(p, i, iconTarjeta));
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // ─────────────────────────────────────────
    // TARJETA DEL CATÁLOGO
    // ─────────────────────────────────────────

    /**
     * Crea una tarjeta visual para el catálogo.
     * Recibe el objeto Prenda directamente desde la lógica, no strings sueltos.
     */
    private JPanel crearTarjeta(Prenda prenda, int fila, ImageIcon iconTarjeta) {
        return crearTarjeta(prenda.getNombre(), String.valueOf(prenda.getPrecio()),
                            prenda.getCodigo(), String.valueOf(prenda.getStock()),
                            iconTarjeta, fila);
    }

    /** Construye visualmente la tarjeta con los datos recibidos */
    private JPanel crearTarjeta(String nombre, String precio, String codigo,
                                String stock, ImageIcon iconTarjeta, int filaTabla) {
        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        card.setPreferredSize(new Dimension(185, 235));

        // Imagen de la prenda (ya viene escalada desde iconosPorFila)
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
        imgLabel.setBackground(new Color(230, 230, 230));
        imgLabel.setOpaque(true);
        if (iconTarjeta != null)
            imgLabel.setIcon(iconTarjeta);
        imgLabel.setBounds(1, 1, 183, 140);
        card.add(imgLabel);

        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setBounds(5, 145, 175, 18);
        card.add(lblNombre);

        JLabel lblPrecio = new JLabel("S/. " + precio, SwingConstants.CENTER);
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPrecio.setForeground(new Color(80, 80, 80));
        lblPrecio.setBounds(5, 163, 175, 16);
        card.add(lblPrecio);

        JLabel lblInfo = new JLabel("ID: " + codigo + "   Stock: " + stock, SwingConstants.CENTER);
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblInfo.setForeground(new Color(130, 130, 130));
        lblInfo.setBounds(5, 179, 175, 14);
        card.add(lblInfo);

        JButton btnEditar = new JButton("EDITAR");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 10));
        btnEditar.setBackground(new Color(100, 160, 220));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setOpaque(true);
        btnEditar.setBounds(5, 200, 82, 24);
        card.add(btnEditar);

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 10));
        btnEliminar.setBackground(new Color(220, 100, 100));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setOpaque(true);
        btnEliminar.setBounds(95, 200, 85, 24);
        card.add(btnEliminar);

        // Eliminar: confirmar, delegar a lógica y refrescar
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(card,
                "¿Deseas eliminar esta prenda?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int idx = gridPanel.getComponentZOrder(card);
                // Llama a gestionar(int) — método sobrecargado que elimina por posición
                gestor.gestionar(idx);
                iconosPorFila.remove(idx);
                refrescarVista();
                JOptionPane.showMessageDialog(null, "Se eliminó de la tabla y del catálogo.");
            }
        });

        // Editar: leer datos de la lógica y abrir formulario
        btnEditar.addActionListener(e -> {
            int fila = gridPanel.getComponentZOrder(card);
            Prenda p = gestor.getPrendas().get(fila);
            mostrarFormularioEditar(fila,
                p.getNombre(), String.valueOf(p.getPrecio()),
                String.valueOf(p.getStock()), p.getCodigo(), p.getCategoria(),
                null, null, null, null);
        });

        // Clic en la tarjeta (no en los botones) abre los detalles
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = gridPanel.getComponentZOrder(card);
                Prenda p = gestor.getPrendas().get(fila);
                verDetallesPrenda(p);
            }
        });

        return card;
    }

    // ─────────────────────────────────────────
    // VER DETALLES DE PRENDA
    // ─────────────────────────────────────────

    // Abre un diálogo con toda la info de la prenda y su tabla de variantes
    private void verDetallesPrenda(Prenda prenda) {
        JDialog dlg = new JDialog(this, "Detalles de Prenda", true);
        dlg.setSize(450, 400);
        dlg.setLocationRelativeTo(this);
        dlg.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dlg.setContentPane(panel);

        // Título con el nombre de la prenda
        JLabel lblTitulo = new JLabel(prenda.getNombre());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(20, 15, 400, 25);
        panel.add(lblTitulo);

        // Datos básicos
        JLabel lblCodigo = new JLabel("Código: " + prenda.getCodigo());
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCodigo.setBounds(20, 48, 200, 20);
        panel.add(lblCodigo);

        JLabel lblCategoria = new JLabel("Categoría: " + prenda.getCategoria());
        lblCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCategoria.setBounds(20, 68, 300, 20);
        panel.add(lblCategoria);

        JLabel lblPrecio = new JLabel("Precio: S/. " + prenda.getPrecio());
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPrecio.setBounds(20, 88, 200, 20);
        panel.add(lblPrecio);

        JLabel lblStockTotal = new JLabel("Stock total: " + prenda.getStock() + " uds");
        lblStockTotal.setFont(new Font("Arial", Font.BOLD, 12));
        lblStockTotal.setForeground(new Color(80, 140, 80));
        lblStockTotal.setBounds(20, 108, 200, 20);
        panel.add(lblStockTotal);

        // Separador visual
        JSeparator sep = new JSeparator();
        sep.setBounds(20, 135, 400, 2);
        panel.add(sep);

        // Título de la tabla de variantes
        JLabel lblVariantes = new JLabel("Stock por talla y color:");
        lblVariantes.setFont(new Font("Arial", Font.BOLD, 12));
        lblVariantes.setBounds(20, 142, 250, 20);
        panel.add(lblVariantes);

        // Tabla con las variantes (talla, color, stock)
        String[] columnas = {"Talla", "Color", "Stock"};
        DefaultTableModel modeloVariantes = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; } // solo lectura
        };

        // Llenar la tabla con las variantes de la prenda
        for (Variante v : prenda.getVariantes()) {
            modeloVariantes.addRow(new Object[]{v.getTalla(), v.getColor(), v.getStock()});
        }

        // Si no tiene variantes, mostrar mensaje
        if (prenda.getVariantes().isEmpty()) {
            modeloVariantes.addRow(new Object[]{"—", "Sin variantes registradas", "—"});
        }

        JTable tabla = new JTable(modeloVariantes);
        tabla.setRowHeight(25);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(240, 235, 225));
        tabla.setBackground(Color.WHITE);
        tabla.setGridColor(new Color(220, 220, 220));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 165, 400, 160);
        panel.add(scroll);

        // Botón cerrar
        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setBackground(new Color(220, 190, 195));
        btnCerrar.setForeground(new Color(50, 50, 50));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setBounds(160, 335, 120, 30);
        btnCerrar.addActionListener(e -> dlg.dispose());
        panel.add(btnCerrar);

        dlg.setVisible(true);
    }

    // ─────────────────────────────────────────
    // AUXILIARES DE VARIANTES
    // ─────────────────────────────────────────

    /**
     * Devuelve las tallas que corresponden a cada categoría.
     * Pantalones usan tallas numéricas, el resto usa XS-XL, accesorios es talla única.
     */
    private String[] tallasParaCategoria(String categoria) {
        if (categoria.contains("PANTALONES")) {
            return new String[]{"26", "28", "30", "32", "34"};
        } else if (categoria.equals("ACCESORIOS")) {
            return new String[]{"ÚNICA"};
        } else {
            return new String[]{"XS", "S", "M", "L", "XL"};
        }
    }

    /**
     * Construye dinámicamente los campos de stock por talla dentro del panelVariantes.
     * Cada fila muestra: [Talla] [Color____] [Stock: 0]
     * Si se pasan variantesActuales (al editar), precarga los valores existentes.
     */
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

    /**
     * Lee los campos del panelVariantes y construye la lista de Variante.
     * Incluye TODAS las filas para que la lógica pueda validarlas correctamente.
     * Las filas con placeholder "Color" se incluyen con color vacío para que la lógica las detecte.
     */
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

    /** Carga y escala una imagen desde un archivo. Retorna null si falla */
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
