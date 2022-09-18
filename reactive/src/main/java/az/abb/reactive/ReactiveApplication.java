package az.abb.reactive;

import az.abb.reactive.entity.Person;
import az.abb.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class ReactiveApplication{
	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}
}
