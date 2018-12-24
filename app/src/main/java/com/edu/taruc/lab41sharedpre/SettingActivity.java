package com.edu.taruc.lab41sharedpre;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {
    private EditText editTextName ;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Linking UI to the program
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences(getString(R.string.pref_file), MODE_PRIVATE);
        String name;
        int gender ; //-1 = default, 1 = male , 0 = female
        name = sharedPreferences.getString("name", "");
        gender = sharedPreferences.getInt("gender", -1);
        editTextName.setText(name);

        if(gender == 1){
            radioButtonMale.setChecked(true);
        }else if(gender == 1){
            radioButtonFemale.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //save shared preferences
        //1. Create instance of editor
        SharedPreferences.Editor editor = sharedPreferences.edit(); //start the transaction
        //2. Read data from UI
        String name = editTextName.getText().toString();
        int gender = radioGroupGender.getCheckedRadioButtonId();
        //3. Begin transaction

        //4. Write data to the shared preferences
        editor.putString("name",name);

        if(gender == R.id.radioButtonMale){
            editor.putInt("gender", 1);
        }else if (gender == R.id.radioButtonFemale){
            editor.putInt("gender",0);
        }else{
            editor.putInt("gender", -1);
        }

        //5. Commit or Apply changes
        //editor.commit();
        //or
        editor.apply(); //preferences


    }
}
