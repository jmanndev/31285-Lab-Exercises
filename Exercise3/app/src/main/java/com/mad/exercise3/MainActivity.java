package com.mad.exercise3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private EditText firstNameEntry, lastNameEntry, phoneEntry, emailEntry;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstNameEntry = (EditText)findViewById(R.id.firstNameEntryText);
		lastNameEntry = (EditText)findViewById(R.id.lastNameEntryText);
		phoneEntry = (EditText)findViewById(R.id.phoneEntryText);
		emailEntry = (EditText)findViewById(R.id.emailEntryText);
		image = (ImageView)findViewById(R.id.picture);

		emailEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(getApplicationContext(), hasFocus ? "Email has focus" : "Email lost focus", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void swapClickHandler(View view) {
	}

	public void rotateClickHandler(View view) {
		//returns int 1 for Portrait, int 2 for Landscape
		int orientation = getWindowManager().getDefaultDisplay().getRotation();

	}
}
