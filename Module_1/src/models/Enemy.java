package models;

public abstract class Enemy {
    protected Double helth;
    protected Integer posX;
    protected Integer posY;

    public Enemy(Double helth, Integer posX, Integer posY) {
        this.helth = helth;
        this.posX = posX;
        this.posY = posY;
    }

    public Double getHelth() {
        return helth;
    }

    public void setHelth(Double helth) {
        this.helth = helth;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }
}
