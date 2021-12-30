package com.GestorLibreriaStefy.demo.repositorios;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT a from Editorial a WHERE a.alta = true")
    List<Editorial> buscarActivos();

}
