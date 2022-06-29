package com.recichertvictor.apirest.repositories;

import com.recichertvictor.apirest.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>  {

    List<Persona> findAllByOrderByApellido();
    List<Persona> findAllByEmpleadoOrderByApellido(boolean empleado);

}
