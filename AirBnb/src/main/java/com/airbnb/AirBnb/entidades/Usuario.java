package com.airbnb.AirBnb.entidades;

import com.airbnb.AirBnb.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Usuario {

    @Id
   @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String alias;
    private String email;
    private String clave;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    protected boolean estado;

    
}
