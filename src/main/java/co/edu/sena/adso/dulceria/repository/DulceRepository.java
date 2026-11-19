package co.edu.sena.adso.dulceria.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import co.edu.sena.adso.dulceria.model.Dulce;
 
// ¡Eso es todo! Spring Data JPA escribe la implementación por usted.
// JpaRepository<Dulce, Long>: repositorio de la entidad Dulce, cuya llave es de tipo Long.
// Ya trae findAll, findById, save, deleteById, count y muchos más.
public interface DulceRepository extends JpaRepository<Dulce, Long> {
}

