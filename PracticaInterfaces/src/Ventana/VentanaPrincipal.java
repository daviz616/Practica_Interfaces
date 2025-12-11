package Ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {
        setTitle("PROYECTO DAM 25 - Noticias");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // setContentPane(new PanelLogin(this));
        
        setVisible(true);
    }
    
    // Método simple para cambiar paneles
    public void cambiarPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}