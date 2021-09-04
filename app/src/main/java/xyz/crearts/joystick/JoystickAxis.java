package xyz.crearts.joystick;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoystickAxis {
    private int axisX;
    private int axisY;
    private int angle;
    private int power;
    private int direction;
}
