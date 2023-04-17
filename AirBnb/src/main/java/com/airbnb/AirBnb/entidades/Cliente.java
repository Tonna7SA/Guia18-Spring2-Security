package com.airbnb.AirBnb.entidades;

import com.airbnb.AirBnb.enumeraciones.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Cliente extends Usuario {

    
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
   private List<Estancia> estancias;
  
    
    
    
}


 