package gd.rf.savvy.www.cakes4you;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class First extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView t1;
    Query q;
    FirebaseUser current_user;
    FirebaseAuth mAuth;
    ImageView im1,im2,im3,im4;
    /*private ViewFlipper flipper;
    Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
    Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        /*current_user = mAuth.getCurrentUser();
        String email = current_user.getEmail();*/
        //String name = current_user.getDisplayName();
        /*q = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("email").equalTo(email);
        t1=findViewById(R.id.name);
        t1.setText(name);*/
        /*flipper = findViewById(R.id.vflip);
        flipper.SetInAnimation(this,android.R.anim.slide_in_left);*/
        /*Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        flipper.setInAnimation(in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        flipper.setOutAnimation(out);*/

      im1=findViewById(R.id.im1);
      im2=findViewById(R.id.im2);
      im3=findViewById(R.id.im3);
      im4=findViewById(R.id.im4);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about = new Intent(First.this, About.class);
                startActivity(about);

                /*Snackbar.make(view, "Khat likhte rahena :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            Toast.makeText(First.this,"Welcome",Toast.LENGTH_LONG).show();

        }else{

            startActivity(new Intent(First.this,Login.class));
            finish();

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent setting = new Intent(First.this, Settings.class);
            startActivity(setting);
            return true;
        }
        if (id == R.id.action_about) {
            Intent setting = new Intent(First.this, About.class);
            startActivity(setting);
            return true;
        }
        if (id == R.id.action_cart) {
            Intent setting = new Intent(First.this, Cart.class);
            startActivity(setting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            FirebaseAuth.getInstance().signOut();
            Intent admin = new Intent(First.this, Login.class);
            startActivity(admin);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void wedding(View view) {
        Intent i = new Intent(this, Weddingtypelist.class);
        startActivity(i);
    }

    public void Design(View view) {
        Intent i = new Intent(getApplicationContext(), Designtypelist.class);
        startActivity(i);
    }

    public void cupcake(View view) {
        Intent i = new Intent(First.this, Cupcaketypelist.class);
        startActivity(i);
    }
}
