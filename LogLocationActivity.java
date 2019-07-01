package ca.cmetcalfe.locationshare;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LogLocationActivity extends AppCompatActivity {
    String names;
    String allNames="";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_location);


        LocDBAdapter db=new LocDBAdapter(this);
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
        names= c.getString(0)+"."+ c.getString(1)+"\n    Latitude:"+c.getString(2)+"\n    Longitude:"+c.getString(3)+"\n\n";
        allNames=allNames+names;
        tv=(TextView)this.findViewById(R.id.textView4);
        tv.setText(allNames);
    }

    public void deleteAll(){
        LocDBAdapter db=new LocDBAdapter(this);
        db.open();
        db.deleteAll();
        db.close();

    }



}
