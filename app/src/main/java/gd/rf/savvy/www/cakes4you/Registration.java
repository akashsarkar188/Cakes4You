package gd.rf.savvy.www.cakes4you;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private EditText e1,e2,e3;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        mAuth=FirebaseAuth.getInstance();
    }

    public void register(View view) {
        final String name=e1.getText().toString();
        final String email=e2.getText().toString();
        final String password=e3.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user= new User (name,email,password); //User is class (constructor and getter setter)
                    FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                                Intent first = new Intent(Registration.this,Login.class);
                                startActivity(first);
                            }
                        }
                    });
                    if(task.isSuccessful()==false)
                    {
                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                        Toast.makeText(getApplicationContext(),"Registration Unsuccessful"+e.getMessage(),Toast.LENGTH_LONG).show();
                        return;
                    }
                }

            }
        });
    }

    public void skip(View view) {
        Intent first = new Intent(Registration.this,First.class);
        startActivity(first);

    }
}
