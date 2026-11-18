package co.edu.sena.adso.dulceria.model;
 
// Un enum es un tipo con un conjunto FIJO de valores posibles
public enum Categoria {
 
    COCADAS("Cocadas"),
    ALEGRIAS("Alegrías"),
    PANELITAS("Panelitas"),
    CONSERVAS("Conservas"),
    OTROS("Otros dulces");
 
    private final String nombre;   // Texto para mostrar al usuario
 
    Categoria(String nombre) {
        this.nombre = nombre;
    }
 
    public String getNombre() {
        return nombre;
    }
}

