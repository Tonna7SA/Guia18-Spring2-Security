package com.airbnb.AirBnb.repositorios;

import com.airbnb.AirBnb.entidades.Estancia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanciaRepositorio extends JpaRepository<Estancia, String>{

    @Query("SELECT e FROM Estancia e WHERE e.cliente.id = :idCliente")
    public List<Estancia> buscarEstanciaPorClienteId(@Param("idCliente") String idCliente);

}
