package com.rasysbox.ws.services;

import com.rasysbox.ws.dto.LocationsResponseDto;
import com.rasysbox.ws.persistence.entity.Locations;
import com.rasysbox.ws.persistence.repository.LocationsRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.rasysbox.ws.utils.Shield.blindStr;


@Service
public class LocationsServiceImpl implements LocationsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationsServiceImpl.class);

    private final LocationsRepository locationsRepository;

    @Autowired
    public LocationsServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> allLocations() {
        var require = locationsRepository.findAll();
        return getListResponseEntity(require);
    }

    @Override
    public ResponseEntity<LocationsResponseDto> findById(String id) {
        var require = this.locationsRepository.findById(Long.valueOf(id));
        if (require.isEmpty()) {
            LOGGER.error("not found id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("found location with id: {}, municipality name: {}", require.get().getId(), require.get().getMunicipio());
        return new ResponseEntity<>(LocationsResponseDto.builder()
                .id(Long.valueOf(blindStr(String.valueOf(require.get().getId()))))
                .region(blindStr(require.get().getRegion()))
                .departamento(blindStr(require.get().getDepartamento()))
                .municipio(blindStr(require.get().getMunicipio()))
                .codigoDaneDelDepartamento(blindStr(require.get().getCodigoDaneDelDepartamento()))
                .codigoDaneDelMunicipio(blindStr(require.get().getCodigoDaneDelMunicipio()))
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> locationsByMunicipality(String municipality) {
        var require = locationsRepository.findByMunicipioContaining(municipality);
        return getListResponseEntity(require);
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> locationsByDepartment(final String department) {
        var require = locationsRepository.findByDepartamentoContaining(StringUtils.capitalize(department));
        return getListResponseEntity(require);
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> locationsByRegion(final String region) {
        var require = locationsRepository.findByRegionContaining(StringUtils.capitalize(region));
        return getListResponseEntity(require);
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> locationsByDepartmentCode(String codeDepartment) {
        var require = locationsRepository.findByCodigoDaneDelDepartamentoContaining(StringUtils.capitalize(codeDepartment));
        return getListResponseEntity(require);
    }

    @Override
    public ResponseEntity<List<LocationsResponseDto>> locationsByMunicipalityCode(String code) {
        var require = locationsRepository.findByCodigoDaneDelMunicipioContaining(code);
        return getListResponseEntity(require);
    }

    private ResponseEntity<List<LocationsResponseDto>> getListResponseEntity(List<Locations> require) {
        if (require.isEmpty()) {
            LOGGER.error("no content");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<LocationsResponseDto> response = new ArrayList<>();
        for (Locations location : require) {
            LocationsResponseDto result = new LocationsResponseDto();
            result.setId(Long.valueOf(blindStr(String.valueOf(location.getId()))));
            result.setRegion(blindStr(location.getRegion()));
            result.setDepartamento(blindStr(location.getDepartamento()));
            result.setMunicipio(blindStr(location.getMunicipio()));
            result.setCodigoDaneDelDepartamento(blindStr(location.getCodigoDaneDelDepartamento()));
            result.setCodigoDaneDelMunicipio(blindStr(location.getCodigoDaneDelMunicipio()));
            response.add(result);
        }
        LOGGER.info("found {} locations", require.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
