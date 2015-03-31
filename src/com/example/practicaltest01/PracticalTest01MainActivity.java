package com.example.practicaltest01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class PracticalTest01MainActivity extends Activity {
	
	private TextListner upTextListner, downTextListner;
	private EditText up_edit_text, down_edit_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_main);
		
		this.up_edit_text = (EditText)findViewById(R.id.up_edit_text);
		this.down_edit_text = (EditText)findViewById(R.id.down_edit_text);
		
		this.upTextListner = new TextListner(up_edit_text);
		this.downTextListner = new TextListner(down_edit_text);
		
		this.up_edit_text.addTextChangedListener(this.upTextListner);
		this.down_edit_text.addTextChangedListener(this.downTextListner);
		boolean c1 = false;
		String text;
		if (savedInstanceState != null)
			c1 = savedInstanceState.getBoolean("upper");
			if (c1 == true) {
				text = savedInstanceState.getString("uppper_text");
				CheckBox up_check = (CheckBox)findViewById(R.id.checkBox1);
				up_check.setChecked(true);
				up_edit_text.setText(text);
				Log.d("WARNING", text);
			}
			c1 = savedInstanceState.getBoolean("down");
			if (c1 == true) {
				text = savedInstanceState.getString("down_text");
				CheckBox down_check = (CheckBox)findViewById(R.id.checkBox2);
				down_check.setChecked(true);
				down_edit_text.setText(text);
				Log.d("WARNING", text);
			}	
		
	}
	@Override
	  protected void onSaveInstanceState(Bundle savedInstanceState) {
		CheckBox up_check = (CheckBox)findViewById(R.id.checkBox1);
	    savedInstanceState.putBoolean("upper", up_check.isChecked());
	    savedInstanceState.putString("upper_text", this.up_edit_text.getText().toString());
	    savedInstanceState.putBoolean("down", up_check.isChecked());
	    savedInstanceState.putString("down_text", this.down_edit_text.getText().toString());
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_main, menu);
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
	
	public class TextListner implements TextWatcher {
		
		private View view;
		
		private TextListner(View v) {
			this.view = v;
		}

		@Override
		public void afterTextChanged(Editable arg0) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
			switch (view.getId()) {
			case R.id.up_edit_text:
				EditText text_field = (EditText)findViewById(R.id.up_edit_text);
				String text = text_field.getText().toString();
				CheckBox up_check = (CheckBox)findViewById(R.id.checkBox1);
				if (checker(text))
					up_check.setChecked(true);
				else
					up_check.setChecked(false);
				break;
				
			case R.id.down_edit_text:
				EditText text_field2 = (EditText)findViewById(R.id.down_edit_text);
				String text2 = text_field2.getText().toString();
				CheckBox up_check2 = (CheckBox)findViewById(R.id.checkBox2);
				if (checker(text2))
					up_check2.setChecked(true);
				else
					up_check2.setChecked(false);
				break;

			default:
				break;
			}
			
		}
		
		public boolean checker(String s) {
			int count = 0;
			for (int i = 0, len = s.length(); i < len; i++) {
			    if (Character.isDigit(s.charAt(i))) {
			        count++;
			    }
			}
			if (count == s.length() && count > 10)
				return true;
			boolean isValid = false;

		    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		    CharSequence inputStr = s;

		    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(inputStr);
		    if (matcher.matches()) {
		        isValid = true;
		    }
		    return isValid;
		}
		
	}
}
