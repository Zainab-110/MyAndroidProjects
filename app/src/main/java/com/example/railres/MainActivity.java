package com.example.railres;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText name,email,members;
    String email1,ticketclass;
    Button btn1;
    Integer num1;
    Intent i, i1;
    Integer flagnom,flagMail,flagTkt;
    AutoCompleteTextView ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_RailRes);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        members = findViewById(R.id.no_of_members);
        email = findViewById(R.id.email);
        btn1 = findViewById(R.id.btnNext);
        flagnom = 0;
        flagMail = 0;
        flagTkt = 0;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1 = email.getText().toString();
                isEmailValid(email1);
                num1 = Integer.parseInt(members.getText().toString());
                if(!((num1>0)&&(num1<5)))
                {
                   nom_toast();
                   flagnom = 1;
                }
                else{
                    flagnom = 0;
                    //do nothing
                }
                if(flagnom==0 && flagMail==0 && flagTkt==0) {
                    i = new Intent(getApplicationContext(), second_login_page.class);
                    i.putExtra("NAME",name.getText().toString());
                    i.putExtra("EMAIL",email1);
                    i.putExtra("MEMBERS",members.getText().toString());
                    startActivity(i);
                }
                else {
                   Toast.makeText(
                           MainActivity.this,
                           "Please fill all the information asked",
                           Toast.LENGTH_SHORT).show();
                }

            }

        });
        //ticket class code
        ticket = findViewById(R.id.ticket_class);
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTicketClassOptions();
            }
        });

        }

    private void showTicketClassOptions() {
        final String[] tickets = {"SS","SL","CC","1A","2A","3A"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select Ticket Class ");
        builder.setSingleChoiceItems(tickets, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ticketclass = tickets[which];
                ticket.setText(ticketclass);
                flagTkt = 0;//'which' is position of item selected
            }
        });
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                flagTkt = 1;
                ticket.clearFocus();
            }
        });
        builder.show();
    }

    public void nom_toast(){
            Toast.makeText(this, "Invalid Entry! Please enter value from the given range only", Toast.LENGTH_SHORT).show();
        }
    public void isEmailValid(String email)
    {

        if (!email1.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            flagMail = 0;
        } else {
            flagMail = 1;
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }
    }




