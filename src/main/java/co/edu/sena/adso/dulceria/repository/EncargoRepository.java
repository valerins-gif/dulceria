package co.edu.sena.adso.dulceria.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import co.edu.sena.adso.dulceria.model.Encargo;
 
public interface EncargoRepository extends JpaRepository<Encargo, Long> {
 
    // QUERY METHOD: Spring Data entiende el NOMBRE y genera la consulta:
    // SELECT * FROM encargos ORDER BY creado_en DESC
    List<Encargo> findAllByOrderByCreadoEnDesc();
}

