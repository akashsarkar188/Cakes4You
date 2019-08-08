package gd.rf.savvy.www.cakes4you;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowProduct extends AppCompatActivity {
    private ListView lv;
    DatabaseReference databaseReference;
    List<Forgetset> cakes;

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cakes.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Forgetset forgetset=snapshot.getValue(Forgetset.class);
                    cakes.add(forgetset);
                }
                Forarrayadapter forarrayAdapter=new Forarrayadapter (ShowProduct.this,cakes);
                lv.setAdapter(forarrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        lv = findViewById(R.id.listviewshowproduct);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cakes");
        cakes = new ArrayList<>();


    }
}
