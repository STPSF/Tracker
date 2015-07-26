package ogr.scorelab.ucsc.mobility_track;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    public static String deviceMAC = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getDeviceMAC();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        }

        return super.onOptionsItemSelected(item);
    }

    /* start background service */
    public void start(View v) {
        Intent intent = new Intent(this, LocationUpdates.class);
        startService(intent);
    }

    /* stop background service */
    public void stop(View v) {
        Intent intent = new Intent(this, LocationUpdates.class);
        stopService(intent);
    }

    private void getDeviceMAC() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.RMNET0_ADDRESS_FILE_PATH));
        for (String block : reader.readLine().split(":")) {
            deviceMAC += block.toUpperCase();
        }
        reader.close();
    }

    /*public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }*/
}
