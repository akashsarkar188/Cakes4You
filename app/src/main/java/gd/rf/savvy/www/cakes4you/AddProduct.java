/*Add product page used by admin to add products to firebase no other class is needed works on its own directly takes data from the user and uploads to
firebase database and firebase storage*/
package gd.rf.savvy.www.cakes4you;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProduct extends AppCompatActivity {
    /*Making variable of each component used*/

    private ImageButton addimg;
    private static final int GALLREQ=1;
    private Uri uri=null;
    private EditText pri,nam;
    private Spinner fla,typ,wei;
    private DatabaseReference drf;
    private StorageReference srf;
    private ProgressDialog pd;
    private Vibrator vibrator;
    boolean connected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Sets this java file on the layout so that it works*/
        setContentView(R.layout.activity_add_product);
        /*Linking the widgets from layout to class using findviewbyid*/
        pri = findViewById(R.id.etprice);
        nam = findViewById(R.id.etname);
        fla = findViewById(R.id.sflavour);
        typ = findViewById(R.id.stype);
        wei = findViewById(R.id.sweight);
        addimg = findViewById(R.id.addimage);
        pd=new ProgressDialog(this);
        /*Creating storage*/
        srf= FirebaseStorage.getInstance().getReference();
        /*Creates database and a table since Cakes id not created initially it will be created*/
        drf = FirebaseDatabase.getInstance().getReference("Cakes");
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            {
            connected = true;
            }
        else
            {
            connected = false;
                new AlertDialog.Builder(this)
                        .setTitle("Bruh Internet ?")
                        .setMessage("You need an active internet connection to proceed !")
                        .setPositiveButton("Alright !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent bhag = new Intent(AddProduct.this,Admin.class);
                                startActivity(bhag);

                            }
                        })
                        .setNegativeButton("I iz gareeb", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pd.setMessage("Transferring money to your account ...");
                                pd.show();
                            }
                        }).show();
            }

    }


    public void Addimg(View view) {
        Intent adimg = new Intent (Intent.ACTION_GET_CONTENT);
        adimg.setType("image/*");
        startActivityForResult(adimg,GALLREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLREQ && resultCode==RESULT_OK)
        {
            uri = data.getData();
            addimg.setImageURI(uri);
        }
    }


    public void bsubmit(View view) {
        final String type = typ.getSelectedItem().toString();
        final String flavour = fla.getSelectedItem().toString();
        final String weight = wei.getSelectedItem().toString();
        final String price = pri.getText().toString();
        final String name = nam.getText().toString();
        if (price==null || name==null || type.equals("Select Type") || flavour.equals("Select Flavour") || weight.equals("Select Weight"))
        {
            Toast.makeText(getApplicationContext(),"One of the data is not Provided, please check. ",Toast.LENGTH_LONG).show();
            vibrator.vibrate(150);
        }
        else {


            pd.setMessage("Baking Cake \uD83C\uDF82 \uD83C\uDF82 ...");
            pd.show();
            vibrator.vibrate(150);

            StorageReference filepath = srf.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Yuummmyyy  ❤️ \uD83C\uDF82️ ❤️", Toast.LENGTH_LONG).show();
                    final DatabaseReference m = drf.push();
                    m.child("name").setValue(name);
                    m.child("weight").setValue(weight);
                    m.child("flavour").setValue(flavour);
                    m.child("type").setValue(type);
                    m.child("price").setValue(price);
                    m.child("image").setValue(downloadUrl.toString());
                }
            });
        }
    }

}
