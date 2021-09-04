package xyz.crearts.joystick;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class Joystick {
    private JoystickAxis leftAxis;
    private JoystickAxis rightAxis;
}
