package com.airbnb.AirBnb.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Casa {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    private Integer minDias;
    private Integer maxDias;
    private Double precio;
    private String tipoVivienda;
    
     @OneToMany(mappedBy = "casa")
    private List<Comentario> comentarios;

    @ManyToOne
       @JoinColumn(name = "familia_id")
    private Familia familia;

    @OneToMany(mappedBy = "casa")
    private List<Estancia> estancias;

    @OneToOne
    private Imagen imagen;
    
}
