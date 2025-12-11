package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Entidades.Usuario;
import Gestores.GestorFicheros;

public class PanelLogin extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal ventana;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JCheckBox chkVerPass;
    
    private final Color COLOR_ROJO_SUAVE = new Color(255, 102, 102); 
    private final Color COLOR_FONDO_OSCURO = new Color(40, 40, 40);
    private final Color COLOR_CAMPO_TEXTO = new Color(60, 60, 60);

    public PanelLogin(VentanaPrincipal v) {
        this.ventana = v;
        setLayout(null);
        setBounds(0, 0, 1024, 768);
        setBackground(COLOR_FONDO_OSCURO);

        // 1. TÍTULO
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_ROJO_SUAVE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setBounds(0, 150, 1024, 50);
        add(lblTitulo);

        // 2. CAMPO USUARIO
        JLabel lblUser = new JLabel("Usuario");
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUser.setBounds(362, 230, 300, 20);
        add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(362, 260, 300, 40);
        txtUsuario.setBackground(COLOR_CAMPO_TEXTO);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(COLOR_ROJO_SUAVE);
        txtUsuario.setBorder(BorderFactory.createLineBorder(COLOR_ROJO_SUAVE, 1));
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            txtUsuario.getBorder(), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtUsuario);

        // 3. CAMPO CONTRASEÑA
        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setForeground(Color.WHITE);
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPass.setBounds(362, 320, 300, 20);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(362, 350, 300, 40);
        txtPass.setBackground(COLOR_CAMPO_TEXTO);
        txtPass.setForeground(Color.WHITE);
        txtPass.setCaretColor(COLOR_ROJO_SUAVE);
        txtPass.setBorder(BorderFactory.createLineBorder(COLOR_ROJO_SUAVE, 1));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setBorder(BorderFactory.createCompoundBorder(
                txtPass.getBorder(), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtPass);

        // 4. CHECKBOX MOSTRAR PASS
        chkVerPass = new JCheckBox("Mostrar contraseña");
        chkVerPass.setBounds(362, 400, 200, 20);
        chkVerPass.setBackground(COLOR_FONDO_OSCURO);
        chkVerPass.setForeground(Color.LIGHT_GRAY);
        chkVerPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chkVerPass.setFocusPainted(false);
        add(chkVerPass);

        chkVerPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkVerPass.isSelected()) {
                    txtPass.setEchoChar((char) 0);
                } else {
                    txtPass.setEchoChar('•');
                }
            }
        });

        // 5. BOTÓN ENTRAR
        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(362, 450, 300, 45);
        btnEntrar.setBackground(COLOR_ROJO_SUAVE);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorderPainted(false);
        add(btnEntrar);

        // LÓGICA DEL LOGIN
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });
    }

    private void verificarCredenciales() {
        String nick = txtUsuario.getText();
        String pass = new String(txtPass.getPassword());

        // Cargamos la lista real desde el gestor
        ArrayList<Usuario> usuarios = GestorFicheros.cargarUsuarios();
        
        boolean encontrado = false;
        Usuario usuarioLogueado = null;

        for (Usuario u : usuarios) {
            if (u.getNickname().equals(nick) && u.getPassword().equals(pass)) {
                encontrado = true;
                usuarioLogueado = u;
                break;
            }
        }

        if (encontrado) {
            // Lógica de redirección según el tipo de usuario
            if (usuarioLogueado.isEsAdmin()) {
                // Mensaje opcional, puedes quitarlo si prefieres entrar directo
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador.");
                ventana.cambiarPanel(new PanelAdminMenu(ventana, usuarioLogueado)); 
            } else {
                if (usuarioLogueado.isTienePreferencias()) {
                    ventana.cambiarPanel(new PanelNoticias(ventana, usuarioLogueado));
                } else {
                    ventana.cambiarPanel(new PanelConfiguracion(ventana, usuarioLogueado));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}