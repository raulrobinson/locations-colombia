package com.rasysbox.ws.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "colombia_locations")
@NoArgsConstructor
@AllArgsConstructor
public class Locations
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "c_digo_dane_del_departamento")
    private String codigoDaneDelDepartamento;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "c_digo_dane_del_municipio")
    private String codigoDaneDelMunicipio;

    @Column(name = "municipio")
    private String municipio;
}
