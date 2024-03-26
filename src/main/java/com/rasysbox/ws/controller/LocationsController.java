package com.rasysbox.ws.controller;

import com.rasysbox.ws.dto.LocationsResponseDto;
import com.rasysbox.ws.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rasysbox.ws.utils.Shield.blindStr;

@RestController
@RequestMapping("${controller.properties.base-path}")
@Tag(name = "Locations Colombia", description = "Departments, Cities and Municipalities of Colombia")
public class LocationsController {

    private final LocationsService service;

    @Autowired
    public LocationsController(LocationsService service) {
        this.service = service;
    }

    /**
     * Method Get all Location
     * @return List to {@link LocationsResponseDto}
     */
    @GetMapping("allLocations")
    @Operation(summary = "All Locations", description = "Listing all locations of Colombia.")
    public ResponseEntity<List<LocationsResponseDto>> allLocations() {
        return service.allLocations();
    }

    /**
     * Methode to get location by id
     * @param id Long
     * @return {@link LocationsResponseDto}
     */
    @GetMapping("locationsById")
    @Operation(summary = "Location by Id", description = "Find location by Id.")
    public ResponseEntity<LocationsResponseDto> locationsById(@RequestParam(value = "id") String id) {
        return service.findById(blindStr(id));
    }

    /**
     * Method to get Municipality by name
     * @param municipality String
     * @return list to {@link LocationsResponseDto}
     */
    @GetMapping("locationsByMunicipality")
    @Operation(summary = "Location by municipality name", description = "Find location by municipality name.")
    public ResponseEntity<List<LocationsResponseDto>> locationsByMunicipality(@RequestParam(value = "municipality") String municipality) {
        return service.locationsByMunicipality(blindStr(municipality));
    }

    /**
     * Method to get list to departments
     * @param department String
     * @return List to {@link LocationsResponseDto}
     */
    @GetMapping("locationsByDepartment")
    @Operation(summary = "Location by department name", description = "Find location by department name.")
    public ResponseEntity<List<LocationsResponseDto>> locationsByDepartment(@RequestParam(value = "department") String department) {
        return  service.locationsByDepartment(department);
    }

    /**
     * Method to get list to regions
     * @param region String
     * @return List to {@link LocationsResponseDto}
     */
    @GetMapping("locationsByRegion")
    @Operation(summary = "Location by region name", description = "Find location by region name.")
    public ResponseEntity<List<LocationsResponseDto>> locationsByRegion(@RequestParam(value = "region") String region) {
        return service.locationsByRegion(region);
    }

}
