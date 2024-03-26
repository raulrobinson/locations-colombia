package com.rasysbox.ws.services;

import com.rasysbox.ws.dto.LocationsResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationsService
{
    ResponseEntity<List<LocationsResponseDto>> allLocations();
    ResponseEntity<LocationsResponseDto> findById(String id);
    ResponseEntity<List<LocationsResponseDto>> locationsByMunicipality(String municipality);
    ResponseEntity<List<LocationsResponseDto>> locationsByDepartment(String department);
    ResponseEntity<List<LocationsResponseDto>> locationsByRegion(String region);
    ResponseEntity<List<LocationsResponseDto>> locationsByDepartmentCode(String code);
    ResponseEntity<List<LocationsResponseDto>> locationsByMunicipalityCode(String code);

}
