package Ventana;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Entidades.Usuario;

public class PanelNoticias extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelNoticias(VentanaPrincipal ventana, Usuario usuario) {
        setLayout(null);
        setBackground(new Color(40, 40, 40)); 
        setBounds(0, 0, 1024, 768);
        
        JLabel lbl = new JLabel("PANEL DE NOTICIAS");
        lbl.setForeground(new Color(255, 102, 102));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setBounds(50, 50, 800, 50);
        add(lbl);
    }
}