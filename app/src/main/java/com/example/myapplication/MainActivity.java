package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DBhelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etuname,etpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuname = findViewById(R.id.uname);
        etpass = findViewById(R.id.pass);
    }

    public void adddata(View view){
        DBhelper dBhelper = new DBhelper(this);

        long val = dBhelper.addinfo(etuname.getText().toString(),etpass.getText().toString());

        if(val>0){
            Toast.makeText(this,"Data Successfully Inserted",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewall(View view){
        DBhelper dBhelper = new DBhelper(this);

        List unames = dBhelper.readallinfo("user");
        Toast.makeText(this, unames.toString(), Toast.LENGTH_SHORT).show();

    }

    public void deletedata(View view){
        DBhelper dBhelper = new DBhelper(this);

        dBhelper.deleteinfo(etuname.getText().toString());
        Toast.makeText(this, etuname.getText().toString()+"Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    public void updatedata(View view) {
        DBhelper dBhelper = new DBhelper(this);

        int val = dBhelper.updateinfo(etuname.getText().toString(),etpass.getText().toString());

        if(val>0){
            Toast.makeText(this,"Data Successfully Updated",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Data Not Updated",Toast.LENGTH_SHORT).show();
        }
    }

    public void signin(View view){
        DBhelper dBhelper = new DBhelper(this);

        List unames = dBhelper.readallinfo("user");
        List passw = dBhelper.readallinfo("password");

        String user = etuname.getText().toString();
        String password = etpass.getText().toString();

        if (unames.indexOf(user)>=0){
            if(passw.get(unames.indexOf(user)).equals(password)){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Login Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        }
    }

}