package co.edu.sena.adso.dulceria.repository;
 
import java.util.List;
import java.util.Optional;
 
import co.edu.sena.adso.dulceria.model.Dulce;
 
// CONTRATO de la capa de datos: QUÉ operaciones existen, no CÓMO se hacen.
// Los nombres no son casuales: son los mismos que usaremos con PostgreSQL en la etapa 9.
public interface DulceRepository {
 
    List<Dulce> findAll();
 
    Optional<Dulce> findById(Long id);
 
    Dulce save(Dulce dulce);
 
    void deleteById(Long id);
}

