 package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.SwingConstants;

import Gestores.GestorFicheros;


public class VentanaCarga extends JFrame {

    private static final long serialVersionUID = 1L;
    private JProgressBar barra;
    private Timer temporizador;
    private int progreso = 0;
    private Image imagenFondo;
    
     private JLabel lblTitulo; 

    public VentanaCarga() {
        setTitle("Cargando...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        
        try {
            imagenFondo = ImageIO.read(new File("carga.jpg"));
        } catch (IOException e) {
            System.out.println("Imagen no encontrada.");
        }

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        setContentPane(panel);

       
        lblTitulo = new JLabel("CARGANDO SISTEMA...");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(200, 200, 400, 30);
        panel.add(lblTitulo);

        barra = new JProgressBar(0, 100);
        barra.setBounds(100, 400, 600, 30);
        barra.setStringPainted(true);
        barra.setForeground(new Color(50, 205, 50)); 
        panel.add(barra);

        iniciarCargaFluida();
    }

    private void iniciarCargaFluida() {
        temporizador = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progreso++; 
                barra.setValue(progreso); 

                if (progreso == 80) {
                    
                    lblTitulo.setText("Validando archivos..."); 
                    
                    
                    lblTitulo.repaint(); 
                    
                    if (!GestorFicheros.validarArchivosExistentes()) {
                        temporizador.stop();
                        JOptionPane.showMessageDialog(null, 
                            "Error Crítico: Faltan archivos de configuración.");
                        System.exit(0);
                    }
                }

                if (progreso >= 100) {
                    temporizador.stop();
                    dispose();
                    new VentanaPrincipal(); 
                }
            }
        });
        temporizador.start();
    }
}