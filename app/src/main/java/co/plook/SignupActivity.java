package co.plook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;



public class SignupActivity extends AppCompatActivity {

    private EditText textPersonName;
    private EditText textEmailAddress;
    private EditText textPassword;
    private EditText textPassword2;
    private Button SignUpButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        textPersonName = (EditText) findViewById(R.id.editText);
        textEmailAddress = (EditText) findViewById(R.id.editText2);
        textPassword = findViewById(R.id.editText3);
        textPassword2 = findViewById(R.id.editText4);
        SignUpButton = findViewById(R.id.SignUpButton);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                registerNewUser();

                //Intent intent_one =new Intent(SignupActivity.this, MainActivity.class);
                //startActivity(intent_one);
            }

        });
    }

    private void registerNewUser() {
        String username, email, confirmpassword, password;
        username = textPersonName.getText().toString();
        email = textEmailAddress.getText().toString();
        password = textPassword.getText().toString();
        confirmpassword = textPassword2.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Kirjoita sähköposti...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Kirjoita salasana", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(this, "Vahvista salasana", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmpassword)) {
            Toast.makeText(this, "Salasanat eivät matchaa", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {



                                Toast.makeText(SignupActivity.this, "Olet rekisteröity", Toast.LENGTH_SHORT).show();
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SignupActivity.this, "Virhe kirjautumisessa " + message, Toast.LENGTH_SHORT).show();
                            }
                            ;
                        }
                    });
        }
    }
}




