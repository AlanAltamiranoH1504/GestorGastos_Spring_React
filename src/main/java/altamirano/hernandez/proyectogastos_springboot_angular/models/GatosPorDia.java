package altamirano.hernandez.proyectogastos_springboot_angular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

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
}
