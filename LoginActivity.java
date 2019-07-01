package ca.cmetcalfe.locationshare;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    EditText strobj1;
    EditText strobj2;

    int counter=0;// for max login attempts, cant make local should be global
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        strobj1 = (EditText) this.findViewById(R.id.username);
        strobj2 = (EditText) this.findViewById(R.id.password);
    }

    public void validateLogin(View view) {
        String line1 = strobj1.getText().toString();
        String line2 = strobj2.getText().toString();
        String adminpwd="admin123";
        String userpwd= "user123";
        String retpassword="";

        String name= strobj1.getText().toString();
        DBAdapter db = new DBAdapter(this);
        db.open();
        try {
            Cursor c = db.getContact(name);
            if (c.moveToFirst()) {
                retpassword = c.getString(2).toString();
            } else {
                Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.e("IO","IO"+e);
        }
        db.close();


        //int counter=0;
        if(line1.isEmpty()||line2.isEmpty()){
            Toast.makeText(this, "Enter Both Credentials", Toast.LENGTH_LONG).show();
        }
        if (line2.equals(retpassword)) {

           // Toast.makeText(this, "Login successful !! WELCOME!!", Toast.LENGTH_LONG).show();
            counter=0;
            Intent intobj=new Intent(this,MainActivity.class);
            intobj.putExtra("Name",line1);
            startActivity(intobj);

        }
        else if (line2.equals(adminpwd)) {

            Toast.makeText(this, "Login successful !! WELCOME ADMIN!!", Toast.LENGTH_LONG).show();
            counter=0;
            Intent intobj=new Intent(this,AdminActivity.class);
            intobj.putExtra("Name",line1);
            startActivity(intobj);

        }
        else {
            //int counter=0;

            Toast.makeText(this, "Not valid Authentication/User Denied Acess", Toast.LENGTH_LONG).show();
            counter++;

            if (counter>=3){
                Toast.makeText(this,"Login Attempts Exhausted",Toast.LENGTH_LONG).show();
            }


        }

    }
    public void registrationLink(View view){
        Intent intobj=new Intent(this,RegisterActivity.class);
        startActivity(intobj);
    }



}
