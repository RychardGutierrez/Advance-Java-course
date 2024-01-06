package models;

public class Dragon extends Enemy implements Flyable{
    public Dragon(Double helth, Integer posX, Integer posY) {
        super(helth, posX, posY);
    }

    @Override
    @ExampleAnnotation
    public void fly() {

    }
}
