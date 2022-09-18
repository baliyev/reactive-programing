package az.abb.reactive.service;

import az.abb.reactive.entity.Person;
import az.abb.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    public Flux<Person> getAll(){return repository.findAll();}
    public Mono<Person> getById(Integer id){return repository.findById(id);}
    public Mono<Person> create(Person person){  return repository.save(person);}
}
