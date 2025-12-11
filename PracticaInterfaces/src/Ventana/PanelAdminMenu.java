package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Entidades.Usuario;

public class PanelAdminMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal ventana;

    
    private final Color COLOR_ROJO_SUAVE = new Color(255, 102, 102);
    private final Color COLOR_FONDO_OSCURO = new Color(40, 40, 40);
    private final Color COLOR_BOTON_HOVER = new Color(255, 120, 120); 

    public PanelAdminMenu(VentanaPrincipal v, Usuario usuario) {
        this.ventana = v;
        setLayout(null);
        setBackground(COLOR_FONDO_OSCURO);
        setBounds(0, 0, 1024, 768);

        
        JLabel lblTitulo = new JLabel("PANEL DE ADMINISTRADOR");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_ROJO_SUAVE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setBounds(0, 50, 1024, 50);
        add(lblTitulo);
        
        JLabel lblUser = new JLabel("Sesión iniciada como: " + usuario.getNickname());
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setForeground(Color.LIGHT_GRAY);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUser.setBounds(0, 100, 1024, 30);
        add(lblUser);

        
         //Botones
        
        
        JButton btnGestionUsuarios = crearBoton("GESTIÓN DE USUARIOS", 200);
        add(btnGestionUsuarios);
        
        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PANEL DE TABLA DE USUARIOS
                JOptionPane.showMessageDialog(null, "Aquí se abrirá la tabla de usuarios.");
            }
        });

        
        JButton btnConfig = crearBoton("CONFIGURACIÓN GLOBAL", 280);
        add(btnConfig);
        
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aquí irían las configuraciones del sistema.");
            }
        });

        
        JButton btnLogout = crearBoton("CERRAR SESIÓN", 450); // Lo ponemos más abajo
        btnLogout.setBackground(new Color(100, 40, 40)); // Un rojo más oscuro para diferenciarlo
        add(btnLogout);
        
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver al Login
                ventana.cambiarPanel(new PanelLogin(ventana));
            }
        });
    }

    /**
     * crear botones con el mismo estilo y ahorrar código.
     */
    private JButton crearBoton(String texto, int posY) {
        JButton boton = new JButton(texto);
        // Centramos el botón: (AnchoVentana - AnchoBoton) / 2
        // (1024 - 400) / 2 = 312
        boton.setBounds(312, posY, 400, 50); 
        boton.setBackground(COLOR_ROJO_SUAVE);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        return boton;
    }
}