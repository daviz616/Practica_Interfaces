package Principal;

import java.awt.EventQueue;

import Ventana.VentanaPrincipal;



public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				  
					VentanaPrincipal miVentana= new VentanaPrincipal();		
					miVentana.setSize(800, 600);
					miVentana.setVisible(true);				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}


}
