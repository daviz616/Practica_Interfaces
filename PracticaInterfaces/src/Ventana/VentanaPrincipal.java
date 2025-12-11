package Ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaPrincipal() {
        // 1. Configuración de la ventana (Con bordes y botones normales)
        setTitle("PROYECTO DAM 25 - Noticias");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Cargamos el Panel de Login nada más abrir
        
        PanelLogin login = new PanelLogin(this);
        setContentPane(login);
        
        setVisible(true);
    }
    
    // Método esencial para navegar entre pantallas (Login -> Noticias -> Config)
    public void cambiarPanel(JPanel panelNuevo) {
        setContentPane(panelNuevo);
        revalidate();
        repaint();
    }
}