package models;

public class Minotour extends Enemy {
    public Minotour(Double helth, Integer posX, Integer posY) {
        super(helth, posX, posY);
    }

    public void attack(CalcualteDistance calcualteDistance) {
        int distance = calcualteDistance.calculate(1, 3);
        System.out.println("Distance: " + distance);
    }

    public void fight() {
        attack((int endx, int endy) -> {
            int diffX = endx - this.posX;
            int diffY = endy - this.posY;
            return diffY * diffY + diffX * diffX;
        });
    }
}
