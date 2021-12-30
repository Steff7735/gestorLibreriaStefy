
package com.GestorLibreriaStefy.demo.repositorios;

/**
 *
 * @author fanny
 */
 
import com.GestorLibreriaStefy.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository  
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    @Query("SELECT a from Autor a WHERE a.alta = true")
    List<Autor> buscarActivos();
}

