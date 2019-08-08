package gd.rf.savvy.www.cakes4you;

import android.app.Activity;
import android.content.Context;
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

public class Forcartadapter extends ArrayAdapter<Cartgetset> {
    private Activity context;
    List<Cartgetset> cakes;
    public Forcartadapter(Activity context,List<Cartgetset> cakes)
    {
        super(context, R.layout.forcartview,cakes);
        this.context=context;
        this.cakes=cakes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*return super.getView(position, convertView, parent);*/
        LayoutInflater inflater = context.getLayoutInflater();
        View v=inflater.inflate(R.layout.forcartview,null,true);
        ImageView im=v.findViewById(R.id.ib);
        TextView t1=v.findViewById(R.id.t1);
        TextView t2=v.findViewById(R.id.t2);
        Cartgetset forgetset = cakes.get(position);
        t1.setText(forgetset.item_name);
        t2.setText(forgetset.item_price);
        String image = forgetset.getItem_image().toString();
        Picasso.with(context).load(image).into(im);
        return v;
    }
}
