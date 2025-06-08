package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class GastoFijoDTO {
    @NotBlank(message = "El nombre del gasto es obligatorio")
    @Size(min = 3, max = 200, message = "La longitud del nombre debe ser entre 3 y 200 caracteres")
    private String nombre;

    @Positive(message = "El monto debe ser mayor a 0")
    private double monto;

    @Positive(message = "Error en id de proveedor")
    private int idProveedor;
    @Positive(message = "Error en id de estado")
    private int idEstado;

    public GastoFijoDTO() {}
    public GastoFijoDTO(String nombre, double monto, int idProveedor, int idEstado) {
        this.nombre = nombre;
        this.monto = monto;
        this.idProveedor = idProveedor;
        this.idEstado = idEstado;
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

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GastoFijoDTO that = (GastoFijoDTO) o;
        return Double.compare(monto, that.monto) == 0 && idProveedor == that.idProveedor && idEstado == that.idEstado && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, monto, idProveedor, idEstado);
    }
}
