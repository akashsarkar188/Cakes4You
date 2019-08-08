package gd.rf.savvy.www.cakes4you;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPAssword extends AppCompatActivity {
    private EditText e1;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        pd = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        e1 = findViewById(R.id.email);
    }

    public void resetpassword(View view) {
        pd.setMessage("Kam chal rha ha bhai");
        pd.show();
        String email = e1.getText().toString();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete())
                {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(),"Check ur email to proceed",Toast.LENGTH_LONG).show();
                }
                else
                {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Network error or email not register ",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void loginback(View view) {
        Intent login = new Intent(ForgetPAssword.this,Login.class);
        startActivity(login);
        finish();
    }
}
