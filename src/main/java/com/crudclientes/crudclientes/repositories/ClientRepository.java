package com.crudclientes.crudclientes.repositories;

import com.crudclientes.crudclientes.entiti.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {



}
