package com.example.railres;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import java.text.DateFormat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TimePicker;


public class second_login_page extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{
    Intent i1;
    String[] states = {"Select City", "Surat", "Udhna", "Vadodara", "Navsari", "Ahmedabad"};
    TextView tvDate,tvTime;
    String str_From,str_To,str,strName,strMail,strNOM;
    Button btPickDate,btPickTime,btnDisplay;
    int flag1 , flag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_login_page);

        strName = getIntent().getStringExtra("NAME");
        strMail = getIntent().getStringExtra("EMAIL");
        strNOM = getIntent().getStringExtra("MEMBERS");

        //the two spinners 'to' and 'from'
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter aa,ad;
        aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,states);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flag1 = 0;
                str_From = spin.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),str_From , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flag1 = 1;

            }
        });

        ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,states);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(ad);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_To = spin2.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),str_To , Toast.LENGTH_LONG).show();
                flag2 = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flag2 = 1;
            }
        });


        //DatePicker code
        tvDate = findViewById(R.id.txtDate);
        btPickDate = findViewById(R.id.btnDate);
        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.railres.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.railres.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        tvTime = findViewById(R.id.txtTime);
        btPickTime = findViewById(R.id.btnTime);
        btPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.railres.TimePicker mTimePicker;
                mTimePicker = new com.example.railres.TimePicker();
                mTimePicker.show(getSupportFragmentManager(),"TIME PICK");


            }
        });

        btnDisplay  = findViewById(R.id.btnDisplay);
        if(checkSpinnerValue(flag1,flag2)) {
            btnDisplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i1 = new Intent(getApplicationContext(),Display_activity.class);
                    i1.putExtra("NAME",strName);
                    i1.putExtra("EMAIL",strMail);
                    i1.putExtra("MEMBERS",strNOM);
                    i1.putExtra("FROM",str_From);
                    i1.putExtra("TO",str_To);
                    i1.putExtra("DATE",tvDate.getText().toString());
                    i1.putExtra("TIME",tvTime.getText().toString());
                    startActivity(i1);

                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(),"Enter all the information ", Toast.LENGTH_LONG);

        }
        }

    //spinner methods

    //method to check whether to and from values are not same
    public boolean checkSpinnerValue(int f1,int f2) {
        return f1 == 0 && f2 == 0;
    }
    //spinner methods end

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDate.setText(selectedDate);

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
         c.set(Calendar.MINUTE, minute);
        tvTime.setText(hourOfDay + ":" + minute);
    }
}
//