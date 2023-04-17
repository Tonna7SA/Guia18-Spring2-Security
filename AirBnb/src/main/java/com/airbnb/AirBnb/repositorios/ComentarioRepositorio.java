package com.airbnb.AirBnb.repositorios;

import com.airbnb.AirBnb.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, String>{

}
