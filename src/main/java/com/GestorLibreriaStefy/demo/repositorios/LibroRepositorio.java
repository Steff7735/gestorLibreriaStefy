
package com.GestorLibreriaStefy.demo.repositorios;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    @Query("SELECT a from Libro a WHERE a.alta =: true")
    List<Libro> buscarActivos();

    @Query("SELECT a FROM Libro a WHERE a.id = : id")
    Libro buscarLibroxid(@Param("id") String id);
}

