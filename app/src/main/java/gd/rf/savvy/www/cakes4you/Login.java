package gd.rf.savvy.www.cakes4you;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText e1, e2;
    private FirebaseAuth mAuth;
    String email, password;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
    }

    public void login(View view) {
        pd.setMessage("Please wait !!");
        pd.show();
        email = e1.getText().toString();
        password = e2.getText().toString();

        if (email.equals("admin") && password.equals("admin")) {
            Intent first = new Intent(Login.this, Admin.class);
            startActivity(first);

        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        startActivity(new Intent(Login.this, First.class));

                    } else {
                        String error = task.getException().getMessage();
                        Toast.makeText(Login.this,error,Toast.LENGTH_LONG).show();
                        pd.dismiss();

                    }
                }
            });
        }

    }

    public void registernew(View view) {
        Intent register = new Intent(Login.this, Registration.class);
        startActivity(register);
        finish();
    }

    public void forgetpassword(View view) {
        Intent register = new Intent(Login.this, ForgetPAssword.class);
        startActivity(register);
        finish();

    }

    public void andarlele(View view) {
        Intent lele = new Intent(Login.this, First.class);
        startActivity(lele);
        finish();
    }
}
