package gd.rf.savvy.www.cakes4you;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void addproduct(View view) {
        Intent adpro = new Intent(Admin.this,AddProduct.class);
        startActivity(adpro);
    }

    public void showproduct(View view) {
        Intent shpro = new Intent(Admin.this,ShowProduct.class);
        startActivity(shpro);
    }
}
