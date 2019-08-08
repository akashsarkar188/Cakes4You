package gd.rf.savvy.www.cakes4you;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    ListView lv;
    List<Cartgetset> uploads;
    TextView t1,t2;
    EditText e1,e2;
    Dialog View;
    String id;
    Button b1;
    private FirebaseUser current_user;
    DatabaseReference databaseReference,userdata,carddata;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long i) {
                Cartgetset cart1=uploads.get(position);
                id = uploads.get(position).toString();
                showUpdateDialog(cart1.getItem_price());
            }
        });
        uploads =  new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        current_user = mAuth.getCurrentUser();
        String email = current_user.getEmail();
        Query query = FirebaseDatabase.getInstance().getReference().child("Cart").orderByChild("user_email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploads.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Cartgetset cart1 = dataSnapshot1.getValue(Cartgetset.class);
                    uploads.add(cart1);
                    //showUpdateDialog(cartgetset.getItem_price());
                }
                Forcartadapter forcartadapter =  new Forcartadapter(Cart.this,uploads);
                lv.setAdapter(forcartadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    protected void showUpdateDialog (final String item_price)
    {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialogbox,null);
        builder.setView(view);
        final Button b1= view.findViewById(R.id.t1);
        builder.setTitle("Do you want it ???");
        final AlertDialog b = builder.create();
        b.show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Cart.this,Order.class);
                i.putExtra("id",item_price);
                startActivity(i);
            }
        });
    }
}
