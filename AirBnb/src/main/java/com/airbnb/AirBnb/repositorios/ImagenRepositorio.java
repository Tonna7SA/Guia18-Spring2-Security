package com.airbnb.AirBnb.repositorios;

import com.airbnb.AirBnb.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{

}
