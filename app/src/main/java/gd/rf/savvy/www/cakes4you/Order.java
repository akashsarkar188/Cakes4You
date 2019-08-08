package gd.rf.savvy.www.cakes4you;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order extends AppCompatActivity {
    private String item_key = null;
    TextView t1,t2;
    EditText e1,e2,e3,e4,e5;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        item_key = getIntent().getExtras().getString("id");
        t1=findViewById(R.id.textview);
        t2=findViewById(R.id.textview5);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText01);
        e3=findViewById(R.id.editText02);
        e4=findViewById(R.id.editText03);
        e5=findViewById(R.id.editText04);
        t1.setText(item_key);
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
    }

    public void update(View view) {
        double a = Double.parseDouble(item_key);
        String e = e1.getText().toString();
        double f = Double.parseDouble(e);
        double n = a*f;
        String result = String.valueOf(n);
        t2.setText(result);
    }

    public void Order(View view) {
        String name = e2.getText().toString();
        String phone = e3.getText().toString();
        String email = e4.getText().toString();
        String address = e5.getText().toString();
        String quantity = e1.getText().toString();
        String price = t1.getText().toString();
        String total = t2.getText().toString();
        String id = databaseReference.push().getKey();
        Details details = new Details(name,phone,email,address,quantity,price,total,id);
        databaseReference.child(id).setValue(details);
        Uri uri = Uri.parse("your-payment-gateway-link");
        Intent paisa = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(paisa);
    }
}
