package ru.leverx.leverxpetsspringbootproxy.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;
import ru.leverx.leverxpetsspringbootproxy.service.PersonService;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping
    public List<PersonResponseDto> getPeople() throws IOException {
        return personService.getPeople();
    }

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(@Valid @RequestBody PersonRequestDto personRequestDto) throws IOException {
        return ResponseEntity.ok(personService.createPerson(personRequestDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable Long id) throws IOException {
        personService.deletePersonById(id);
        return ResponseEntity.ok("Person with id=" + id + " was successfully deleted");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonResponseDto> updatePersonById(@PathVariable Long id,
                                                              @Valid @RequestBody PersonRequestDto personRequestDto) throws IOException {
        return ResponseEntity.ok(personService.updatePerson(id, personRequestDto));
    }
}