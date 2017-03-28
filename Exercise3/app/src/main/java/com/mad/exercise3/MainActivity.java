package com.mad.exercise3;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Main activity for application
 */
public class MainActivity extends AppCompatActivity {

	public static final String LOG_TAG = "MAD";
	public static final String STATE_FIRST = "FIRST_NAME", STATE_LAST = "LAST_NAME", STATE_PHONE = "PHONE", STATE_EMAIL = "EMAIL", STATE_IMAGE_ID = "IMAGE_ID";

	private EditText firstNameEntry, lastNameEntry, phoneEntry, emailEntry;
	private ImageView image;
	private Button swapBtn;

	/**
	 * Called when the activity is starting.
	 * Contains initializations for UI elements.
	 *
	 * @param savedInstanceState	The data most recently supplied in onSaveInstanceState(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstNameEntry = (EditText) findViewById(R.id.firstNameEntryText);
		lastNameEntry = (EditText) findViewById(R.id.lastNameEntryText);
		phoneEntry = (EditText) findViewById(R.id.phoneEntryText);

		image = (ImageView) findViewById(R.id.picture);
		setImageLogo(R.mipmap.ic_launcher);

		emailEntry = (EditText) findViewById(R.id.emailEntryText);
		emailEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(getApplicationContext(), hasFocus ? "Email has focus" : "Email lost focus", Toast.LENGTH_SHORT).show();
			}
		});

		swapBtn = (Button) findViewById(R.id.swapButton);
		swapBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				swapImageLogo();
			}
		});
	}

	/**
	 * Called to retrieve per-instance state from activity before it is killed.
	 *
	 * @param outState	Bundle in which to place the saved state
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(STATE_FIRST, firstNameEntry.getText().toString());
		outState.putString(STATE_LAST, lastNameEntry.getText().toString());
		outState.putString(STATE_PHONE, phoneEntry.getText().toString());
		outState.putString(STATE_EMAIL, emailEntry.getText().toString());
		outState.putInt(STATE_IMAGE_ID, (int) image.getTag());

		Log.d(LOG_TAG, "Log: onSaveInstanceState(bundle)");
	}

	/**
	 * Called after onStart(Bundle).
	 * Re-initializes activity from previously saved state.
	 *
	 * @param savedInstanceState	The data most recently supplied in onSaveInstanceState(Bundle)
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		firstNameEntry.setText(savedInstanceState.getString(STATE_FIRST));
		lastNameEntry.setText(savedInstanceState.getString(STATE_LAST));
		phoneEntry.setText(savedInstanceState.getString(STATE_PHONE));
		emailEntry.setText(savedInstanceState.getString(STATE_EMAIL));
		setImageLogo(savedInstanceState.getInt(STATE_IMAGE_ID));

		Log.d(LOG_TAG, "Log: onRestoreInstanceState(bundle)");
	}

	/**
	 * Called when a view has been clicked.
	 * Implements rotate button click listener.
	 *
	 * @param view	View that was clicked
	 */
	public void onClick(View view) {
		int viewId = view.getId();
		switch (viewId) {
			case R.id.rotateButton:
				rotateScreen();
				break;
		}
	}

	/**
	 * Rotates screen orientation based on current orientation.
	 */
	private void rotateScreen() {
		switch (this.getResources().getConfiguration().orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				break;
		}
	}

	/**
	 * Swaps image based on current image.
	 */
	private void swapImageLogo() {
		switch ((int) image.getTag()) {
			case R.mipmap.ic_launcher:
				setImageLogo(R.mipmap.ic_launcher_round);
				break;
			case R.mipmap.ic_launcher_round:
				setImageLogo(R.mipmap.ic_launcher);
				break;
		}

	}

	/**
	 * Sets image and image tag.
	 *
	 * @param resId		Resource ID of image
	 */
	private void setImageLogo(int resId) {
		image.setTag(resId);
		image.setImageResource(resId);
	}
}
