package com.recichertvictor.apirest.services;

import com.recichertvictor.apirest.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<Persona> findAllByEmpleado(boolean empleado);

    List<Persona> findAllByOrderByApellido();
    public Optional<Persona> findById(Long id);
    public Persona edit(Persona entity);
    public Persona create(Persona entity);
    public void delete(Long id);
}
