package Entidades;

public class Usuario {
    
   
    private String nickname;
    private String password;
    private String email;
    private boolean esAdmin;
    private boolean tienePreferencias; // Para controlar si es la 1ª vez

    // Constructor
    public Usuario(String nickname, String password, String email, boolean esAdmin) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.esAdmin = esAdmin;
        this.tienePreferencias = false; // De serie false
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean isTienePreferencias() {
        return tienePreferencias;
    }

    public void setTienePreferencias(boolean tienePreferencias) {
        this.tienePreferencias = tienePreferencias;
    }

    
    @Override
    public String toString() {
        return "Usuario [nickname=" + nickname + ", email=" + email + ", esAdmin=" + esAdmin + "]";
    }
}