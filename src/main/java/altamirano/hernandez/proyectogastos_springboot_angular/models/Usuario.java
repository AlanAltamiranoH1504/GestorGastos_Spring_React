package altamirano.hernandez.proyectogastos_springboot_angular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener una intitud de entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 3, max = 100, message = "Los apellidos deben tener una longitud de entre 3 y 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El email es obligatorio")
    @Size(min = 3, message = "El email debe tener una longitud minima de 3 caracteres")
    private String email;

    @NotBlank(message = "El password es obligatorio")
    @Size(min = 5, max = 100, message = "El password debe tener una longitud de entre 5 y 100 caracteres")
    private String password;
    private String token;
    private Date fecha;
    private String imagenURL;

    //Varios Usuarios para un Perfil
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    //Varios Usuarios para un Estado
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    //Constructores
    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String email, String password, String imagenURL, Perfil perfil, Estado estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.imagenURL = imagenURL;
        this.perfil = perfil;
        this.estado = estado;
    }

    public Usuario(String nombre, String apellidos, String email, String password, String imagenURL, String token, Date fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.token = token;
        this.imagenURL = imagenURL;
        this.fecha = fecha;
    }

    public Usuario(String nombre, String apellidos, String email, String password, String token, String imagenURL, Date fecha, Perfil perfil, Estado estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.token = token;
        this.fecha = fecha;
        this.imagenURL = imagenURL;
        this.perfil = perfil;
        this.estado = estado;
    }

    public Usuario(int id, String nombre, String apellidos, String email, String password, String token, String imagenURL, Date fecha, Perfil perfil, Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.token = token;
        this.imagenURL = imagenURL;
        this.fecha = fecha;
        this.perfil = perfil;
        this.estado = estado;
    }

    // Get y Set
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    // E y H

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(token, usuario.token) && Objects.equals(fecha, usuario.fecha) && Objects.equals(imagenURL, usuario.imagenURL) && Objects.equals(perfil, usuario.perfil) && Objects.equals(estado, usuario.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, email, password, token, fecha, imagenURL, perfil, estado);
    }
}
