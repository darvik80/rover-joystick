package xyz.crearts.joystick;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import xyz.crearts.joystick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView angleTextViewLeft;
    private TextView powerTextViewLeft;
    private TextView directionTextViewLeft;
    private TextView coordTextViewLeft;

    private JoystickView joystickLeft;

    private TextView angleTextViewRight;
    private TextView powerTextViewRight;
    private TextView directionTextViewRight;
    private TextView coordTextViewRight;

    private JoystickView joystickRight;

    private ZeroMQService zeroMQService;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (zeroMQService != null) {
            zeroMQService.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Joystick joystick = new Joystick();
        zeroMQService = new ZeroMQService();
        zeroMQService.create();

        angleTextViewLeft = findViewById(R.id.angleTextViewLeft);
        powerTextViewLeft = findViewById(R.id.powerTextViewLeft);
        directionTextViewLeft = findViewById(R.id.directionTextViewLeft);
        coordTextViewLeft = findViewById(R.id.coordTextViewLeft);

        joystickLeft = findViewById(R.id.joystickViewLeft);
        joystickLeft.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                angleTextViewLeft.setText(" " + angle + "°");
                powerTextViewLeft.setText(" " + power + "%");

                switch (direction) {
                    case JoystickView.FRONT:
                        directionTextViewLeft.setText(R.string.front_lab);
                        break;
                    case JoystickView.FRONT_RIGHT:
                        directionTextViewLeft.setText(R.string.front_right_lab);
                        break;
                    case JoystickView.RIGHT:
                        directionTextViewLeft.setText(R.string.right_lab);
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        directionTextViewLeft.setText(R.string.right_bottom_lab);
                        break;
                    case JoystickView.BOTTOM:
                        directionTextViewLeft.setText(R.string.bottom_lab);
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        directionTextViewLeft.setText(R.string.bottom_left_lab);
                        break;
                    case JoystickView.LEFT:
                        directionTextViewLeft.setText(R.string.left_lab);
                        break;
                    case JoystickView.LEFT_FRONT:
                        directionTextViewLeft.setText(R.string.left_front_lab);
                        break;
                    default:
                        directionTextViewLeft.setText(R.string.center_lab);
                }
            }
            @Override
            public void onValueChanged(JoystickAxis axis) {
                coordTextViewLeft.setText("" + axis.getAxisX() + ":" + axis.getAxisY());
                joystick.setLeftAxis(axis);

                zeroMQService.send(joystick);
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);

        angleTextViewRight = findViewById(R.id.angleTextViewRight);
        powerTextViewRight = findViewById(R.id.powerTextViewRight);
        directionTextViewRight = findViewById(R.id.directionTextViewRight);
        coordTextViewRight = findViewById(R.id.coordTextViewRight);

        joystickRight = findViewById(R.id.joystickViewRight);
        joystickRight.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                angleTextViewRight.setText(" " + angle + "°");
                powerTextViewRight.setText(" " + power + "%");
                switch (direction) {
                    case JoystickView.FRONT:
                        directionTextViewRight.setText(R.string.front_lab);
                        break;
                    case JoystickView.FRONT_RIGHT:
                        directionTextViewRight.setText(R.string.front_right_lab);
                        break;
                    case JoystickView.RIGHT:
                        directionTextViewRight.setText(R.string.right_lab);
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        directionTextViewRight.setText(R.string.right_bottom_lab);
                        break;
                    case JoystickView.BOTTOM:
                        directionTextViewRight.setText(R.string.bottom_lab);
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        directionTextViewRight.setText(R.string.bottom_left_lab);
                        break;
                    case JoystickView.LEFT:
                        directionTextViewRight.setText(R.string.left_lab);
                        break;
                    case JoystickView.LEFT_FRONT:
                        directionTextViewRight.setText(R.string.left_front_lab);
                        break;
                    default:
                        directionTextViewRight.setText(R.string.center_lab);
                }
            }

            @Override
            public void onValueChanged(JoystickAxis axis) {
                coordTextViewRight.setText("" + axis.getAxisX() + ":" + axis.getAxisY());
                joystick.setRightAxis(axis);

                zeroMQService.send(joystick);
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_quit) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}