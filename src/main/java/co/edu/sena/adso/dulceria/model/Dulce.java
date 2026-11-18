package co.edu.sena.adso.dulceria.model;
 
// MODELO: representa un producto de la tienda.
public class Dulce {
 
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;          // En pesos colombianos (COP)
    private Categoria categoria;
    private boolean disponible = true;
 
    public Dulce() {
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


