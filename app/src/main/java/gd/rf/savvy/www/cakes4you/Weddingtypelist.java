package gd.rf.savvy.www.cakes4you;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Weddingtypelist extends AppCompatActivity {
    private RecyclerView rv;
    /*private DatabaseReference mRef;*/
    private Query mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weddingtypelist);
        rv = findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference().child("Cakes").orderByChild("type").equalTo("Wedding Cake");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Forgetset,Weddingtypelist.Itemviewholder> FBRA = new FirebaseRecyclerAdapter<Forgetset, Itemviewholder>(
                Forgetset.class, R.layout.cardview, Weddingtypelist.Itemviewholder.class, mRef) {
            @Override
            protected void populateViewHolder(Itemviewholder viewHolder, Forgetset model, int position) {
                viewHolder.setType(model.type);
                viewHolder.setPrice(model.price);
                viewHolder.setFlav(model.flavour);
                viewHolder.setName(model.name);
                viewHolder.setWeight(model.weight);
                viewHolder.setImage(getApplication(),model.getImage());
                final String item_key = getRef(position).getKey().toString();
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Weddingtypelist.this,Specification.class);
                        i.putExtra("itemid",item_key);
                        startActivity(i);
                    }
                });


            }
        };
        rv.setAdapter(FBRA);
    }

    public static class Itemviewholder extends RecyclerView.ViewHolder{
        View mview;

        public Itemviewholder(View itemView) {
            super(itemView);
            this.mview = itemView;
        }
        public void setType (String type)
        {
            TextView type1 = (TextView)mview.findViewById(R.id.typeText);
            type1.setText("Type: "+type);
        }
        public void setPrice (String price)
        {
            TextView type2 = (TextView)mview.findViewById(R.id.priceText);
            type2.setText("Rs."+price);
        }
        public void setImage (Context c,String image)
        {
            ImageView imagec = (ImageView) mview.findViewById(R.id.img);
            Picasso.with(c).load(image).into(imagec);
        }

        public void setFlav(String flavour) {
            TextView flavtext=mview.findViewById(R.id.flavText);
            flavtext.setText("Flavor: "+flavour);
        }

        public void setName(String name) {
            TextView nametext=mview.findViewById(R.id.nameText);
            nametext.setText(name);
        }

        public void setWeight(String weight) {
            TextView weighttext=mview.findViewById(R.id.weightText);
        weighttext.setText("Weight: "+weight);
        }
    }
}
