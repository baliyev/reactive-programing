package az.abb.reactive.repository;

import az.abb.reactive.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person,Integer> {
}
