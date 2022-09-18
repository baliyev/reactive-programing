package az.abb.reactive.config;
import az.abb.reactive.entity.Person;
import az.abb.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;


@Configuration
public class DatasourceConfig {

    @Bean
    public CommandLineRunner initDatabase(ConnectionFactory cf) {
        return (args) -> {

                Flux.from(cf.create())
                        .flatMap(c -> Flux.from(c.createBatch()
                                        .add("drop table if exists person")
                                        .add("create table person(" +
                                                "id IDENTITY(1,1)," +
                                                "firstname varchar(80)," +
                                                "lastname varchar(80))")
                                        .add(generate())
                                        .execute())
                                        .doFinally((st) -> c.close())).blockLast();

        };
    }

    private String  generate(){
        String query = "";
        StringBuilder builder= new StringBuilder();
        IntStream.range(1,32).forEach(i->
                builder.append("INSERT INTO person (firstname,lastname) " +
                        "values('" +"Name_" + i + "','" + "Surname_"+i+"');")
                );
       query = builder.toString();
       return query;
    }
}
