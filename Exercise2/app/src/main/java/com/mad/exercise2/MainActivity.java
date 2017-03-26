package com.mad.exercise2;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

	Button submitBtn, clearAllBtn, exitBtn;
	Snackbar snackbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), getString(R.string.submitButtonSnackbar), Snackbar.LENGTH_LONG);

		submitBtn = (Button) findViewById(R.id.submitButton);
		clearAllBtn = (Button) findViewById(R.id.clearAllButton);
		exitBtn = (Button) findViewById(R.id.exitButton);

		submitBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				snackbar.show();
			}
		});

		clearAllBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearAllForms();
			}
		});
	}


	private void clearAllForms() {
		((EditText) findViewById(R.id.nameText)).setText("");
		((EditText) findViewById(R.id.emailText)).setText("");
		((EditText) findViewById(R.id.numberText)).setText("");
		((Spinner) findViewById(R.id.numberTypeDropdown)).setSelection(0);
	}


}