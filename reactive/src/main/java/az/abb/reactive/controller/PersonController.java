package az.abb.reactive.controller;

import az.abb.reactive.entity.Person;
import az.abb.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService service;

    @GetMapping("/users")
    public Flux<Person> getUsers(){
        return service.getAll();
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<Person>> getUser(@PathVariable("id") Integer id){
        return service.getById(id).
                map(person->new ResponseEntity<>(person, HttpStatus.OK)).
                switchIfEmpty(Mono.just(new ResponseEntity<>(null,HttpStatus.NOT_FOUND)));
    }

    @PostMapping("/users")
    public Mono<ResponseEntity<Person>> createUser(@RequestBody Person person){
        return service.create(person).
                map(_person->new ResponseEntity<>(_person,HttpStatus.CREATED)).log();
    }
}
