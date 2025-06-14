package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "El password es obligatorio")
    @Size(min = 5, message = "El largo minimo del password es 5 caracteres")
    private String password;
    @Positive(message = "El perfil es obligatorio")
    private int perfilId;
    @Positive(message = "El estado es obligatorio")
    private int estadoId;

    public UsuarioDTO(String nombre, String email, String password, int perfilId, int estadoId) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.perfilId = perfilId;
        this.estadoId = estadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return perfilId == that.perfilId && estadoId == that.estadoId && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, email, password, perfilId, estadoId);
    }
}
