package com.airbnb.AirBnb.repositorios;

import com.airbnb.AirBnb.entidades.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, String>{

  @Query("SELECT f FROM Familia f WHERE f.email = :email")
    public Familia buscarPorEmail(@Param("email")String email);
}
