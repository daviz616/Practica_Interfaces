package Gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Entidades.Usuario;

public class GestorFicheros {

    private static final String RUTA_USUARIOS = "usuarios.txt";
    private static final String RUTA_CONFIG = "configuracion.txt"; 
    
    public static boolean validarArchivosExistentes() {
        // 1. Creamos las referencias a los archivos
        File fUsuarios = new File(RUTA_USUARIOS);
        File fConfig = new File(RUTA_CONFIG);

        
        if (fUsuarios.exists() && fConfig.exists()) {
            return true; 
        } else {
            return false; 
        }
    }
  
    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        File archivo = new File(RUTA_USUARIOS);

        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                
                if (partes.length == 4) {
                    String nick = partes[0];
                    String pass = partes[1];
                    String email = partes[2];
                    boolean esAdmin = Boolean.parseBoolean(partes[3]);

                    Usuario u = new Usuario(nick, pass, email, esAdmin);
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo usuarios: " + e.getMessage());
        }
        return lista;
    }
}