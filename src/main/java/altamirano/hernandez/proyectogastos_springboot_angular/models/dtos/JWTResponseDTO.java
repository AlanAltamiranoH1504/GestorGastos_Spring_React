package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import java.util.Objects;

public class JWTResponseDTO {
    private int id;
    private String nombre;
    private String perfil;
    private int perfilId;
    private String token;

    //Constructores
    public JWTResponseDTO() {
    }

    public JWTResponseDTO(int id, String nombre, String perfil, int perfilId, String token) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        this.perfilId = perfilId;
        this.token = token;
    }

    //G y S
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JWTResponseDTO that = (JWTResponseDTO) o;
        return id == that.id && perfilId == that.perfilId && Objects.equals(nombre, that.nombre) && Objects.equals(perfil, that.perfil) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, perfil, perfilId, token);
    }
}
