package Model;

public class AppModel {
    private String usuarioActual = null;
    private String rolActual = null; // "ADMIN", "USER", "PABLO", etc.

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getRolActual() {
        return rolActual;
    }

    public void setRolActual(String rolActual) {
        this.rolActual = rolActual;
    }

    public boolean isLoggedIn() {
        return usuarioActual != null;
    }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(rolActual);
    }
}
