package Ventana;

import java.awt.Panel;

import javax.swing.JFrame;



public class VentanaPrincipal extends JFrame{

	public VentanaPrincipal() {
		setTitle("NOTICIAS");
		setSize(800, 600);

		setLocationRelativeTo(null);
		
		Panel PanelCarga= new Panel();		
		add(PanelCarga);
	}

}
