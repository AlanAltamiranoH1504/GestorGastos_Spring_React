package altamirano.hernandez.proyectogastos_springboot_angular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "gastos_por_dia")
public class GatosPorDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El neto debe ser mayor a 0")
    private double neto;
    @Positive(message = "El IVA debe ser mayor a 0")
    private double iva;
    @Positive(message = "El total debe ser mayor a 0")
    private double total;
    private Date fecha;
    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 300, message = "La longitud de la descripcion debe ser entre 3 y 300 caracteres")
    private String descripcion;

    //Varios gastos por dia para un proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    //Constructores
    public GatosPorDia() {
    }

    public GatosPorDia(double neto, double iva, double total, Date fecha, String descripcion, Proveedor proveedor) {
        this.neto = neto;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public GatosPorDia(Long id, double neto, double iva, double total, Date fecha, String descripcion, Proveedor proveedor) {
        this.id = id;
        this.neto = neto;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    // G y S
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        GatosPorDia that = (GatosPorDia) o;
        return Double.compare(neto, that.neto) == 0 && Double.compare(iva, that.iva) == 0 && Double.compare(total, that.total) == 0 && Objects.equals(id, that.id) && Objects.equals(fecha, that.fecha) && Objects.equals(descripcion, that.descripcion) && Objects.equals(proveedor, that.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, neto, iva, total, fecha, descripcion, proveedor);
    }
}
