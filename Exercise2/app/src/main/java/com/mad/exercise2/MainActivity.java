package com.mad.exercise2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

	public static final String LOG_TAG = "MainActivity";
	public static final int INTENT_CODE = 314;
	public static final String KEY_NAME = "name";
	public static final String KEY_PHONETYPE = "phoneType";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_AGREEMENT = "agreement";

	EditText nameText, emailText, numberText;
	Spinner numberTypeDropdown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nameText = (EditText) findViewById(R.id.nameText);
		emailText = (EditText) findViewById(R.id.emailText);
		numberText = (EditText) findViewById(R.id.numberText);
		numberTypeDropdown = (Spinner) findViewById(R.id.numberTypeDropdown);

		Log.d(LOG_TAG, "onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();

		Log.d(LOG_TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.d(LOG_TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();

		Log.d(LOG_TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();

		Log.d(LOG_TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		Log.d(LOG_TAG, "onDestroy");
	}

	public void submitButtonClickHandler(View view) {
		/** Assign user data from inputs to variables **/
		String name = nameText.getText().toString();
		String email = emailText.getText().toString();
		String number = numberText.getText().toString();
		String phoneType = numberTypeDropdown.getSelectedItem().toString();

		/** Send user data and launch ActivityTwo **/
		Intent intentLaunchActivityTwo = new Intent(this, ActivityTwo.class);
		intentLaunchActivityTwo.putExtra(KEY_NAME, name);
		intentLaunchActivityTwo.putExtra(KEY_EMAIL, email);
		intentLaunchActivityTwo.putExtra(KEY_NUMBER, number);
		intentLaunchActivityTwo.putExtra(KEY_PHONETYPE, phoneType);
		startActivityForResult(intentLaunchActivityTwo, INTENT_CODE);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		/** Display snackbar depending on checkbox status in ActivityTwo **/
		if (requestCode == INTENT_CODE) {
			Snackbar snackbar;
			if (data.getExtras().getBoolean(KEY_AGREEMENT)) {
				snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.snackbar_agree, Snackbar.LENGTH_LONG);
				snackbar.show();
			} else {
				snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.snackbar_disagree, Snackbar.LENGTH_LONG);
				snackbar.show();
			}
		}
	}

	public void exitButtonClickHandler(View view) {
		/** Close application **/
		finish();
	}

	public void clearButtonClickHandler(View view) {
		/** Set fields to null/default **/

		nameText.setText("");
		emailText.setText("");
		numberText.setText("");
		numberTypeDropdown.setSelection(0);
	}
}