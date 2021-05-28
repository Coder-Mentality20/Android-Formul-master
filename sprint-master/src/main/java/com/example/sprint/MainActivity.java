package com.example.sprint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AlertDialog recap;
    private EditText editTextName, editTextEmail, editTextMobile,
            editTextDob, editTextAge;

    private Button buttonSubmit;

    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //initializing view objects
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextDob = (EditText) findViewById(R.id.editTextPass);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);


        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.editTextName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.appbar_scrolling_view_behavior);
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.appbar_scrolling_view_behavior);
        awesomeValidation.addValidation(this, R.id.editTextMobile, Patterns.PHONE.pattern(), R.string.appbar_scrolling_view_behavior);
        awesomeValidation.addValidation(this, R.id.editTextPass, "[0-9]{6}", R.string.appbar_scrolling_view_behavior);
        awesomeValidation.addValidation(this, R.id.editTextAge, Range.closed(13, 60), R.string.appbar_scrolling_view_behavior);

        buttonSubmit.setOnClickListener(this);
    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            recap = new AlertDialog.Builder(this).create();
            recap.setTitle("Welcome Mr.(Mme) : " + " " + editTextName.getText() + " To Youssoufia City");
            recap.setMessage("Your Email :" + editTextEmail.getText() + "\n" + "Is Validated ");
            recap.show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            submitForm();
        }
    }
}
