package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import Gestores.GestorFicheros;

public class VentanaCarga extends JFrame {

    private JProgressBar barra;
    private Timer temporizador;
    private int segundos = 0;

    public VentanaCarga() {
        // Configuración básica (Tu Main pone el tamaño y el setVisible, así que aquí no hace falta)
        setTitle("Cargando...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Diseño absoluto para colocar cosas a mano

        // Panel de fondo (opcional, para darle color)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(0, 0, 800, 600); // Cubre toda la ventana
        add(panel);

        // Texto
        JLabel lblTitulo = new JLabel("CARGANDO SISTEMA...");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(250, 200, 300, 30);
        panel.add(lblTitulo);

        // Barra de progreso
        barra = new JProgressBar(0, 100);
        barra.setBounds(100, 400, 600, 30);
        barra.setStringPainted(true);
        panel.add(barra);

        // Arrancamos el contador
        iniciarTimer();
    }

    private void iniciarTimer() {
        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                barra.setValue(segundos * 20); // 20, 40, 60...

                // 1. Validar ficheros en el segundo 4
                if (segundos == 4) {
                    if (!GestorFicheros.validarArchivosExistentes()) {
                        temporizador.stop();
                        JOptionPane.showMessageDialog(null, "Error: Faltan archivos txt.");
                        System.exit(0);
                    }
                }

                // 2. Terminar en el segundo 5
                if (segundos == 5) {
                    temporizador.stop();
                    dispose(); // Cierra esta ventana
                    
                    // Abre la ventana principal
                    new VentanaPrincipal(); 
                }
            }
        });
        temporizador.start();
    }
}