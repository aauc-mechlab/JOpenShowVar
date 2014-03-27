package no.hials;

import java.io.IOException;

import no.hials.crosscom.networking.Callback;
import no.hials.crosscom.networking.CrossComClient;
import no.hials.crosscom.networking.Request;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements Runnable {

	private volatile boolean doRun = false;
	private Thread kukaThread = null;

	/*
	 * private TextView xLabel; private TextView yLabel; private TextView
	 * zLabel;
	 */

	public void connect(View view) {

		if (kukaThread == null) {
			kukaThread = new Thread(this);
			kukaThread.start();

		}

		if (doRun) {
			doRun = false;
			kukaThread.interrupt();
			kukaThread = null;
			/*
			 * xLabel.setTextColor(Color.BLACK);
			 * yLabel.setTextColor(Color.BLACK);
			 * zLabel.setTextColor(Color.BLACK);
			 */
		} else {
			doRun = true;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		/*
		 * xLabel = (TextView) findViewById(R.id.xLabel); yLabel = (TextView)
		 * findViewById(R.id.yLabel); zLabel = (TextView)
		 * findViewById(R.id.zLabel);
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void run() {
		Log.d("Crosscom", "Starting thread");
		CrossComClient client = null;

		try {
			String IP = "192.168.2.2";
			int PORT = 7000;
			client = new CrossComClient(IP, PORT);

			Button xUpButton = (Button) findViewById(R.id.xIncButton);
			Button xDownButton = (Button) findViewById(R.id.xDecButton);
			Button yUpButton = (Button) findViewById(R.id.yIncButton);
			Button yDownButton = (Button) findViewById(R.id.yDecButton);
			Button zUpButton = (Button) findViewById(R.id.zIncButton);
			Button zDownButton = (Button) findViewById(R.id.zDecButton);

			while (doRun) {

				SeekBar slider = (SeekBar) findViewById(R.id.seekBar1);
				double speed = slider.getProgress();
				double x = 0, y = 0, z = 0;
				if (xUpButton.isPressed()) {
					x = speed;
				} else if (xDownButton.isPressed()) {
					x = -speed;
				}
				if (yUpButton.isPressed()) {
					y = speed;
				} else if (yDownButton.isPressed()) {
					y = -speed;
				}
				if (zUpButton.isPressed()) {
					z = speed;
				} else if (zDownButton.isPressed()) {
					z = -speed;
				}
				Log.d("Crosscom", "{x=" + x + ", y=" + y + ", z=" + z + "}"
						+ "\t" + "Stepsize= " + speed);
				client.sendRequest(new Request(0, "MYPOS", "{X " + x + ",Y "
						+ y + ",Z " + z + "}"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.sendRequest(new Request(0, "MYPOS", "{X " + 0 + ",Y "
						+ 0 + ",Z " + 0 + "}"));
				client.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
