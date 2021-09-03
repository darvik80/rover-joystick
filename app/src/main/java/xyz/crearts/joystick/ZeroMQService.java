package xyz.crearts.joystick;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class ZeroMQService {
    Gson gson = new Gson();
    private final ZMQ.Context context = ZMQ.context(1);
    private ZMQ.Socket socket;

    public void create() {
        try {
            socket = context.socket(SocketType.PUB);
            if (!socket.connect("tcp://192.168.100.163:5556")) {
                throw new RuntimeException("Test");
            }

        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public void send(Joystick joystick) {
        try {
            socket.sendMore("joystick");
            socket.send(gson.toJson(joystick));
        } catch (Exception ex) {
            socket.connect("tcp://192.168.100.163:5556");
            socket.subscribe("joystick");
        }
    }
}
