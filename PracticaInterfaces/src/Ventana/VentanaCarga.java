package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import Gestores.GestorFicheros;

public class VentanaCarga extends JFrame {

    private static final long serialVersionUID = 1L;
    private JProgressBar barra;
    private Timer temporizador;
    private int progreso = 0;
    private Image imagenFondo;
    private final Color COLOR_ROJO_SUAVE = new Color(255, 102, 102);

    public VentanaCarga() {
        setUndecorated(true);
        setTitle("Cargando...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            URL urlImagen = getClass().getResource("carga.jpg");
            if (urlImagen != null) {
                imagenFondo = ImageIO.read(urlImagen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
                g.setColor(new Color(0, 0, 0, 150)); // Filtro oscuro
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        setContentPane(panel);

        JLabel lblTitulo = new JLabel("CARGANDO SISTEMA...");
        lblTitulo.setForeground(COLOR_ROJO_SUAVE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBounds(100, 475, 400, 20);
        panel.add(lblTitulo);

        barra = new JProgressBar(0, 100);
        barra.setBounds(100, 500, 600, 10);
        barra.setStringPainted(false);
        barra.setForeground(COLOR_ROJO_SUAVE);
        barra.setBackground(new Color(60, 60, 60));
        barra.setBorder(BorderFactory.createEmptyBorder());
        panel.add(barra);

        iniciarCargaFluida();
    }

    private void iniciarCargaFluida() {
        temporizador = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progreso++;
                barra.setValue(progreso);

                // Segundo 4 -> Validar
                if (progreso == 80) {
                    if (!GestorFicheros.validarArchivosExistentes()) {
                        temporizador.stop();
                        JOptionPane.showMessageDialog(null, "Error Crítico: Faltan archivos de configuración.");
                        System.exit(0);
                    }
                }

                // Fin -> Abrir Principal
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