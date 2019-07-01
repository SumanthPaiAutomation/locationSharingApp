package ca.cmetcalfe.locationshare;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewUserActivity extends AppCompatActivity {
    String names="";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor obj = db.getAllContacts();
        if (obj.moveToFirst()) {
            do {

                display(obj);

            } while (obj.moveToNext());

            db.close();

        }

    }
    public void display(Cursor c)
    {
        names= names+"\n"+c.getString(1);
        tv=(TextView)this.findViewById(R.id.textView2);
        tv.setText(names);

    }

    }
