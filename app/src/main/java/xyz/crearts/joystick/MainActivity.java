package xyz.crearts.joystick;

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

    private JoystickView joystickLeft;

    private TextView angleTextViewRight;
    private TextView powerTextViewRight;
    private TextView directionTextViewRight;
    private JoystickView joystickRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        angleTextViewLeft = findViewById(R.id.angleTextViewLeft);
        powerTextViewLeft = findViewById(R.id.powerTextViewLeft);
        directionTextViewLeft = findViewById(R.id.directionTextViewLeft);

        joystickLeft = findViewById(R.id.joystickViewLeft);
        joystickLeft.setOnJoystickMoveListener((angle, power, direction) -> {
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
        }, JoystickView.DEFAULT_LOOP_INTERVAL);

        angleTextViewRight = findViewById(R.id.angleTextViewRight);
        powerTextViewRight = findViewById(R.id.powerTextViewRight);
        directionTextViewRight = findViewById(R.id.directionTextViewRight);

        joystickRight = findViewById(R.id.joystickViewRight);
        joystickRight.setOnJoystickMoveListener((angle, power, direction) -> {
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