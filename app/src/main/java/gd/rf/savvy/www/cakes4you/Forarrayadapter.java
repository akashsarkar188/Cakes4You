package gd.rf.savvy.www.cakes4you;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gd.rf.savvy.www.cakes4you.R;

public class Forarrayadapter extends ArrayAdapter<Forgetset> {
    private Activity context;
    List<Forgetset> cakes;
    public Forarrayadapter(Activity context,List<Forgetset> cakes)
    {
        super(context, R.layout.toview,cakes);
        this.context=context;
        this.cakes=cakes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*return super.getView(position, convertView, parent);*/
        LayoutInflater inflater = context.getLayoutInflater();
        View v=inflater.inflate(R.layout.toview,null,true);
        ImageView im=v.findViewById(R.id.imgpro);
        TextView t1=v.findViewById(R.id.textview1);
        TextView t2=v.findViewById(R.id.textview2);
        TextView t3=v.findViewById(R.id.textview3);
        TextView t4=v.findViewById(R.id.textview4);
        TextView t5=v.findViewById(R.id.textview5);
        Forgetset forgetset = cakes.get(position);
        t1.setText("Name : "+forgetset.name);
        t2.setText("Price : ₹"+forgetset.price);
        t3.setText("Cake Type : "+forgetset.type);
        t4.setText("Cake Flavour : "+forgetset.flavour);
        t5.setText("Cake Weight : "+forgetset.weight);
        String image = forgetset.getImage().toString();
        Picasso.with(context).load(image).into(im);
        return v;
    }
}
