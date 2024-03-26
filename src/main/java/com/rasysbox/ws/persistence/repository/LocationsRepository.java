package com.rasysbox.ws.persistence.repository;

import com.rasysbox.ws.dto.LocationsResponseDto;
import com.rasysbox.ws.persistence.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long>
{
    LocationsResponseDto getLocationByRegion(String region);
    LocationsResponseDto getLocationByDepartamento(String departamento);
    List<Locations> findByMunicipioContaining(String municipio);
    List<Locations> findByDepartamentoContaining(String departamento);
    List<Locations> findByRegionContaining(String region);
    List<Locations> findByCodigoDaneDelDepartamentoContaining(String departmentCode);
    List<Locations> findByCodigoDaneDelMunicipioContaining(String municipalityCode);
}
