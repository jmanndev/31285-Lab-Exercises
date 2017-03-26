package com.mad.exercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

	TextView nameText, numberText, phoneTypeText, emailText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		nameText = (TextView) findViewById(R.id.nameActivity2);
		emailText = (TextView) findViewById(R.id.emailActivity2);
		phoneTypeText = (TextView) findViewById(R.id.phoneTypeActivity2);
		numberText = (TextView) findViewById(R.id.phoneNumberActivity2);


		/** Check intent contains data **/
		Bundle userData = getIntent().getExtras();
		if (userData == null)
			return;

		/** Set fields using data from intent **/
		nameText.setText(userData.getString(MainActivity.KEY_NAME));
		emailText.setText(userData.getString(MainActivity.KEY_EMAIL));
		phoneTypeText.setText(userData.getString(MainActivity.KEY_PHONETYPE));
		numberText.setText(userData.getString(MainActivity.KEY_NUMBER));
	}

	@Override
	public void onClick(View v) {
		/** Return checkbox status to MainActivity **/
		boolean agreement = ((CheckBox) findViewById(R.id.agreementCheckBox)).isChecked();
		Intent intent = new Intent();
		intent.putExtra(MainActivity.KEY_AGREEMENT, agreement);
		setResult(MainActivity.INTENT_CODE, intent);
		finish();
	}
}
