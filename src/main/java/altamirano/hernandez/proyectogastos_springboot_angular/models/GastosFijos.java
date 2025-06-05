package altamirano.hernandez.proyectogastos_springboot_angular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
    @Table(name = "gastos_fijos")
public class GastosFijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del gasto es obligatorio")
    @Size(min = 3, max = 200, message = "La longitud del nombre debe ser entre 3 y 200 caracteres")
    private String nombre;

    @Positive(message = "El monto debe ser mayor a 0")
    private double monto;
    private Date fecha;

    //Varios gastos fijos para un estado
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    //Varios gastos fijos para un proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    //Constructores
    public GastosFijos() {

    }

    public GastosFijos(String nombre, double monto, Date fecha, Estado estado, Proveedor proveedor) {
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.proveedor = proveedor;
    }

    public GastosFijos(Long id, String nombre, double monto, Date fecha, Estado estado, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.proveedor = proveedor;
    }

    // G y S
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GastosFijos that = (GastosFijos) o;
        return Double.compare(monto, that.monto) == 0 && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fecha, that.fecha) && Objects.equals(estado, that.estado) && Objects.equals(proveedor, that.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, monto, fecha, estado, proveedor);
    }
}
