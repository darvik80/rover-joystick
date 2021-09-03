package xyz.crearts.joystick;

public class Joystick {
    public int angle;
    public int power;
    public int direction;

    public Joystick(int angle, int power, int direction) {
        this.angle = angle;
        this.power = power;
        this.direction = direction;
    }

    public Joystick() {
    }
}
