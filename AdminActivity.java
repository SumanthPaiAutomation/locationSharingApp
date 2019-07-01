package ca.cmetcalfe.locationshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void userDetails(View view){
        Intent intobj=new Intent(this,ViewUserActivity.class);
        startActivity(intobj);

    }

    public void viewLocationLog(View view){
        Intent intobj=new Intent(this,LogLocationActivity.class);
        startActivity(intobj);
    }
}
