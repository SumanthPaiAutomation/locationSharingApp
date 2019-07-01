package ca.cmetcalfe.locationshare;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void insertContact(View view){
        DBAdapter db=new DBAdapter(this);
        db.open();

        EditText txt1,txt2;
        txt1=(EditText)findViewById(R.id.username);
        txt2=(EditText)findViewById(R.id.password);



        if((txt1.getText().toString()).equals("")||((txt2.getText().toString()).equals(""))){

            Toast.makeText(this,"Please Enter the Details",Toast.LENGTH_LONG).show();
        }
        else{
            long id= db.insertContact(txt1.getText().toString(),txt2.getText().toString());//this insert contact is defined in DAdapter.java
            if(id!=0){

                Toast.makeText(this,"Contact Inserted Successfully",Toast.LENGTH_LONG).show();


            }
            else {

                Toast.makeText(this,"Error While Inserting",Toast.LENGTH_LONG).show();
            }

            db.close();
            txt1.setText("");
            txt2.setText("");
            txt2.clearFocus();





        }

    }



    public void displayContact(View view){



        DBAdapter db=new DBAdapter(this);
        db.open();
        Cursor obj=db.getAllContacts();
        if(obj.moveToFirst())
        {
            do{

                display(obj);

            }while (obj.moveToNext());

            db.close();

        }

    }


    public void display(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email: " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }

    public void searchContact(View view)
    {
        EditText txt =(EditText)findViewById(R.id.username);
        String query = "";
        String name;
        long rowId; //id required of the record that is to be retrieved, this is entered by the user.

        name = txt.getText().toString();
        //rowId = Integer.parseInt(query);
        Toast.makeText(this, "contact found " + name, Toast.LENGTH_LONG).show();
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getContact(name); //getcontact() defined in DBAdapter.java along with insertContact,deleteContact(),updateContact(),getALLContacts()
        if (c.moveToFirst())
            //display(c);
            Toast.makeText(this, "contact found " + name, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();
    }

}
