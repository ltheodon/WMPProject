package com.emse.spring.faircorp.hello;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class DummyUserService implements UserService{

    @Override
    public void greetAll() {
        List<String> labNames = Arrays.asList("Elodie", "Charles");
        GreetingService greetingService = new ConsoleGreetingService(); // (2)
        greetingService.greet(labNames.get(0));
        greetingService.greet(labNames.get(1));
    }
}
