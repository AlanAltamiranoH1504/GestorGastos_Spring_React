package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class GastoPorDiaDTO {
    @Positive(message = "El neto debe ser mayot a 0")
    private double neto;

    @Positive(message = "El IVA debe ser mayor a 0")
    private double iva;

//    @Positive(message = "El total debe ser mayor a 0")
    private double total;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @Positive(message = "El id del proveedor es obligatorio")
    private int proveedorId;

    public GastoPorDiaDTO() {

    }
    public GastoPorDiaDTO(double neto, double iva, double total, String descripcion, int proveedorId) {
        this.neto = neto;
        this.iva = iva;
        this.total = total;
        this.descripcion = descripcion;
        this.proveedorId = proveedorId;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GastoPorDiaDTO that = (GastoPorDiaDTO) o;
        return Double.compare(neto, that.neto) == 0 && Double.compare(iva, that.iva) == 0 && Double.compare(total, that.total) == 0 && proveedorId == that.proveedorId && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neto, iva, total, descripcion, proveedorId);
    }
}
