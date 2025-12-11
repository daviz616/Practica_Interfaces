package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entidades.Usuario;
import Gestores.GestorFicheros;

public class PanelGestionUsuarios extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal ventana;
    private Usuario usuarioLogueado; 
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    // Colores corporativos
    private final Color COLOR_ROJO_SUAVE = new Color(255, 102, 102);
    private final Color COLOR_FONDO_OSCURO = new Color(40, 40, 40);
    private final Color COLOR_TABLA_FONDO = new Color(60, 60, 60);
    private final Color COLOR_VERDE_BTN = new Color(46, 204, 113); // Verde para el botón crear

    public PanelGestionUsuarios(VentanaPrincipal v, Usuario u) {
        this.ventana = v;
        this.usuarioLogueado = u;
        
        setLayout(null);
        setBackground(COLOR_FONDO_OSCURO);
        setBounds(0, 0, 1024, 768);

        
        JLabel lblTitulo = new JLabel("GESTIÓN DE USUARIOS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_ROJO_SUAVE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setBounds(0, 30, 1024, 40);
        add(lblTitulo);

        
        String[] columnas = {"Nickname", "Contraseña", "Es Admin", "Tiene Pref."};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            // Hacemos que las celdas no sean editables directamente (opcional)
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setBackground(COLOR_TABLA_FONDO);
        tablaUsuarios.setForeground(Color.WHITE);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.setGridColor(Color.GRAY);
        tablaUsuarios.setSelectionBackground(COLOR_ROJO_SUAVE);
        tablaUsuarios.setSelectionForeground(Color.WHITE);
        
        // Ponemos la tabla dentro de un ScrollPane
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBounds(112, 100, 800, 400);
        scroll.getViewport().setBackground(COLOR_FONDO_OSCURO);
        add(scroll);

        // Cargamos los datos iniciales
        cargarDatosEnTabla();

        
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(112, 550, 150, 40);
        btnVolver.setBackground(Color.GRAY);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.cambiarPanel(new PanelAdminMenu(ventana, usuarioLogueado));
            }
        });

       
        JButton btnNuevo = new JButton("NUEVO USUARIO");
        btnNuevo.setBounds(387, 550, 250, 40); // Posición central
        btnNuevo.setBackground(COLOR_VERDE_BTN);
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNuevo.setFocusPainted(false);
        add(btnNuevo);

        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuarioDialogo();
            }
        });

        
        JButton btnEliminar = new JButton("ELIMINAR SELECCIONADO");
        btnEliminar.setBounds(662, 550, 250, 40);
        btnEliminar.setBackground(COLOR_ROJO_SUAVE);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setFocusPainted(false);
        add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuarioSeleccionado();
            }
        });
    }

    

    private void cargarDatosEnTabla() {
        ArrayList<Usuario> lista = GestorFicheros.cargarUsuarios();
        for (Usuario usu : lista) {
            agregarFila(usu);
        }
    }

    private void agregarFila(Usuario u) {
        Object[] fila = {
            u.getNickname(),
            u.getPassword(),
            u.isEsAdmin() ? "SÍ" : "NO",
            u.isTienePreferencias() ? "SÍ" : "NO"
        };
        modeloTabla.addRow(fila);
    }

    private void crearUsuarioDialogo() {
        
        String nick = JOptionPane.showInputDialog(this, "Introduce el nickname:");
        if (nick == null || nick.trim().isEmpty()) return;

        
        String pass = JOptionPane.showInputDialog(this, "Introduce la contraseña:");
        if (pass == null || pass.trim().isEmpty()) return;

        
        int esAdminRespuesta = JOptionPane.showConfirmDialog(this, 
                "¿Este usuario será ADMINISTRADOR?", "Tipo", JOptionPane.YES_NO_OPTION);
        boolean esAdmin = (esAdminRespuesta == JOptionPane.YES_OPTION);

        
        //Usuario nuevo = new Usuario(nick, pass, esAdmin, false);
        //agregarFila(nuevo);
        
        JOptionPane.showMessageDialog(this, "Usuario creado correctamente (Visualmente).");
        // TODO: En el futuro aquí llamaremos a GestorFicheros.guardar(nuevo);
    }

    private void eliminarUsuarioSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario de la tabla.");
            return;
        }

        String nick = (String) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Seguro que quieres eliminar a " + nick + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(fila);
            JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            // TODO: En el futuro aquí llamaremos a GestorFicheros.borrar(nick);
        }
    }
}