package altamirano.hernandez.proyectogastos_springboot_angular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(min = 3, max = 100, message = "La longitud del nombre debe ser entre 3 y 100 caracteres")
    private String nombre;

    //Constructores
    public Proveedor() {

    }
    public Proveedor(String nombre) {
        this.nombre = nombre;
    }
    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // G y S
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

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor that = (Proveedor) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
