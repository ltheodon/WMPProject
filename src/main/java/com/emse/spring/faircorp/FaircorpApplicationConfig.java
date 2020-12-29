package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FaircorpApplicationConfig {
    @Bean
    public CommandLineRunner greetingCommandLine(@Qualifier("anotherConsoleGreetingService") GreetingService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                service.greet("Spring");
            }
        };
    }
}
