package app.model;

import org.springframework.stereotype.Component;

@Component("dog")
public class Dog extends Animal {
    @Override
    public String makeSound() {
        return "Woof!";
    }

    @Override
    public String toString() {
        return "Im a Dog";
    }
}