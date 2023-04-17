package com.airbnb.AirBnb.repositorios;

import com.airbnb.AirBnb.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{

@Query("SELECT c FROM Usuario c WHERE c.email = :email")
    public Cliente buscarPorEmail(@Param("email")String email);
}


