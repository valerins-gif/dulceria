package co.edu.sena.adso.dulceria.model;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
 
import org.springframework.format.annotation.DateTimeFormat;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "encargos")
public class Encargo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    // RELACIÓN: muchos encargos pueden ser del mismo dulce (N a 1).
    // En la tabla "encargos" se crea la columna dulce_id, una LLAVE FORÁNEA hacia "dulces".
    @ManyToOne(optional = false)
    @JoinColumn(name = "dulce_id")
    private Dulce dulce;
 
    @NotBlank(message = "Escriba su nombre")
    @Size(max = 80, message = "Máximo 80 caracteres")
    @Column(name = "nombre_cliente", nullable = false, length = 80)
    private String nombreCliente;
 
    @NotBlank(message = "Escriba su celular")
    @Pattern(regexp = "3\\d{9}", message = "Escriba un celular de 10 dígitos que empiece por 3")
    @Column(nullable = false, length = 10)
    private String telefono;
 
    @NotNull(message = "Indique la cantidad")
    @Min(value = 1, message = "Mínimo 1 unidad")
    @Max(value = 50, message = "Máximo 50 unidades por encargo")
    @Column(nullable = false)
    private Integer cantidad;
 
    @NotNull(message = "Indique la fecha de entrega")
    @FutureOrPresent(message = "La fecha no puede estar en el pasado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)   // Formato del <input type="date">: AAAA-MM-DD
    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;
 
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;
 
    public Encargo() {
    }
 
    public Encargo(Dulce dulce, String nombreCliente, String telefono, Integer cantidad, LocalDate fechaEntrega) {
        this.dulce = dulce;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }
 
    @PrePersist   // Se ejecuta justo antes del INSERT
    void alCrear() {
        this.creadoEn = LocalDateTime.now();
    }
 
    // Dato CALCULADO: no se guarda en la base de datos
    public int getTotal() {
        return dulce.getPrecio() * cantidad;
    }
 
    public Long getId() { return id; }
 
    public Dulce getDulce() { return dulce; }
 
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
 
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
 
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
 
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
 
    public LocalDateTime getCreadoEn() { return creadoEn; }
}

