package configs;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/task_management", "postgres", "postgres")
                .locations("classpath:db/migration")
                .load();
    }

}
