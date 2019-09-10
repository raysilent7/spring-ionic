package com.raysilent.curso.resources;

import com.raysilent.curso.domain.City;
import com.raysilent.curso.domain.State;
import com.raysilent.curso.domain.dto.CityDTO;
import com.raysilent.curso.domain.dto.StateDTO;
import com.raysilent.curso.services.CityService;
import com.raysilent.curso.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/states")
public class StateResources {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {
        List<State> list = service.findAll();
        List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{stateId}/cities")
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
        List<City> list = cityService.findAll(stateId);
        List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
