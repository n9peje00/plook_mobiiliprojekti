package co.plook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Parent class for all of the other activities to check if user is logged in on every onCreate()
public class ParentActivity extends AppCompatActivity
{
    protected FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null)
        {
            //send user to login page if not logged in
            //startSplashScreenActivity();
            Intent intent = new Intent(this, MainActivity.class);

            //comment out this line if you want to ignore login check
            startActivity(intent);
        }
    }
}
