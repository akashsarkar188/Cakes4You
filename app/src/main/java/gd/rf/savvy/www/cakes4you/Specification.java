package gd.rf.savvy.www.cakes4you;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Specification extends AppCompatActivity {

    private String item_key = null;
    private DatabaseReference mRef,mRef1,userdata,databaseReference;
    ProgressDialog pd;
    private TextView t1,t2;
    private ImageView ib;
    private FirebaseAuth mAuth;
    private FirebaseUser  current_user;
    String item_price,item_name,item_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specification);
        t1 = findViewById(R.id.t1);
        ib = findViewById(R.id.specimg);
        t2 = findViewById(R.id.t2);
        pd = new ProgressDialog(this);
        item_key=getIntent().getExtras().getString("itemid");
        mRef= FirebaseDatabase.getInstance().getReference("Cakes");
        mRef1= FirebaseDatabase.getInstance().getReference("Cart");
        mAuth=FirebaseAuth.getInstance();
        current_user=mAuth.getCurrentUser();
        userdata=FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
        mRef.child(item_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item_name = (String)dataSnapshot.child("name").getValue();
                item_price = (String)dataSnapshot.child("price").getValue();
                item_image = (String)dataSnapshot.child("image").getValue();
                t1.setText("Name : "+item_name);
                t2.setText("₹: "+item_price);
                Picasso.with(getApplicationContext()).load(item_image).into(ib);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addtocart1(View view) {
        pd.setMessage("Adding "+item_name+" to your  cart");
        pd.show();
        final DatabaseReference newOrder = mRef1.push();
        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newOrder.child("item_name").setValue(item_name);
                newOrder.child("item_price").setValue(item_price);
                newOrder.child("item_image").setValue(item_image);
                current_user = FirebaseAuth.getInstance().getCurrentUser();
                String email = current_user.getEmail();
                newOrder.child("user_email").setValue(email);
                newOrder.child("user_name").setValue(dataSnapshot.child("Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Intent i =  new Intent(Specification.this,Cart.class);
                        startActivity(i);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
