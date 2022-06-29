package com.recichertvictor.apirest.servicesimpl;

import com.recichertvictor.apirest.entities.Persona;
import com.recichertvictor.apirest.repositories.PersonaRepository;
import com.recichertvictor.apirest.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> findAllByEmpleado(boolean empleado) {
        return personaRepository.findAllByEmpleadoOrderByApellido(empleado);
    }

    @Override
    public List<Persona> findAllByOrderByApellido() {
        return personaRepository.findAllByOrderByApellido();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    @Transactional
    public Persona edit(Persona entity) throws RuntimeException {
        return personaRepository.save(entity);
    }

    @Override
    @Transactional
    public Persona create(Persona entity) throws RuntimeException {
        return personaRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws RuntimeException{
        /*La persona no se borra de la base de datos, se cambia el estado a empleado = flase */
        Optional<Persona> Entityfound = personaRepository.findById(id);
        if(Entityfound.isPresent()){
            Persona Entity = Entityfound.get();
            Entity.setEmpleado(false);
            personaRepository.save(Entity);
        }
    }
}
