package co.edu.sena.adso.dulceria.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
@Entity                    // Esta clase se guarda en una TABLA de PostgreSQL
@Table(name = "dulces")    // Nombre de la tabla
public class Dulce {
 
    @Id                                                   // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // PostgreSQL asigna el código
    private Long id;
 
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "Máximo 80 caracteres")
    @Column(nullable = false, length = 80)
    private String nombre;
 
    @Size(max = 300, message = "Máximo 300 caracteres")
    @Column(length = 300)
    private String descripcion;
 
    @NotNull(message = "Indique el precio")
    @Min(value = 500, message = "El precio mínimo es $ 500")
    @Max(value = 200000, message = "El precio máximo es $ 200.000")
    @Column(nullable = false)
    private Integer precio;
 
    @NotNull(message = "Seleccione una categoría")
    @Enumerated(EnumType.STRING)   // Guarda el texto "COCADAS", no un número
    @Column(nullable = false, length = 20)
    private Categoria categoria;
 
    @Column(nullable = false)
    private boolean disponible = true;
 
    public Dulce() {      // JPA y los formularios necesitan un constructor vacío
    }
 
    public Dulce(String nombre, String descripcion, Integer precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
 
    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }
 
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
 
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}

