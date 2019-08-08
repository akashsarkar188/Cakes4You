package gd.rf.savvy.www.cakes4you;

import android.app.DownloadManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Designtypelist extends AppCompatActivity {
    RecyclerView rv;
    private Query q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designtypelist);
        rv = findViewById(R.id.recyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        q = FirebaseDatabase.getInstance().getReference().child("Cakes").orderByChild("type").equalTo("Designer/Cute Cake");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Forgetset,Designtypelist.Itemviewholder> f = new FirebaseRecyclerAdapter<Forgetset, Itemviewholder>(
                Forgetset.class,R.layout.cardview,Designtypelist.Itemviewholder.class,q
        ) {
            @Override
            protected void populateViewHolder(Itemviewholder viewHolder, Forgetset model, int position) {
                viewHolder.setType(model.type);
                viewHolder.setPrice(model.price);
                viewHolder.setImage(getApplication(),model.getImage());

            }
        };
        rv.setAdapter(f);
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
            type1.setText(type);
        }
        public void setPrice (String price)
        {
            TextView type2 = (TextView)mview.findViewById(R.id.priceText);
            type2.setText(price);
        }
        public void setImage (Context c, String image)
        {
            ImageView imagec = (ImageView) mview.findViewById(R.id.img);
            Picasso.with(c).load(image).into(imagec);
        }
    }
}
