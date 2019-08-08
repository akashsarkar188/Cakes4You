package gd.rf.savvy.www.cakes4you;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class Cupcaketypelist extends AppCompatActivity {
    RecyclerView r;

    Query q;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcaketypelist);
        r=findViewById(R.id.recyclerview);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        q= FirebaseDatabase.getInstance().getReference().child("Cakes").orderByChild("type").equalTo("Cupcake");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Forgetset,Itemviewholder> f = new FirebaseRecyclerAdapter<Forgetset, Itemviewholder>
                (Forgetset.class,R.layout.cardview,Cupcaketypelist.Itemviewholder.class,q) {
            @Override
            protected void populateViewHolder(Itemviewholder viewHolder, Forgetset model, int position) {
                viewHolder.setType(model.type);
                viewHolder.setPrice(model.price);

            }
        };
        r.setAdapter(f);
    }

    public static class Itemviewholder extends RecyclerView.ViewHolder
    {
        View view;

        public Itemviewholder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
        public void setType(String type)
        {
            TextView a = (TextView)view.findViewById(R.id.typeText);
            a.setText(type);
        }
        public void setPrice(String price)
        {
            TextView b = (TextView)view.findViewById(R.id.priceText);
            b.setText(price);
        }
        public void setImage(String image,Context c)
        {
            ImageView i = (ImageView)view.findViewById(R.id.img);
            Picasso.with(c).load(image).into(i);
        }
    }
}



