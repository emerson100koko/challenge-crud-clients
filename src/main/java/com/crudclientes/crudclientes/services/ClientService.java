package com.crudclientes.crudclientes.services;

import com.crudclientes.crudclientes.dto.ClientDTO;
import com.crudclientes.crudclientes.entiti.Client;
import com.crudclientes.crudclientes.repositories.ClientRepository;
import com.crudclientes.crudclientes.services.exceptions.DatabaseException;
import com.crudclientes.crudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao Encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso nao Encontrado");
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setId((dto.getId()));
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBithDate(dto.getBithDate());
        entity.setChildren(dto.getChildren());
    }
}
