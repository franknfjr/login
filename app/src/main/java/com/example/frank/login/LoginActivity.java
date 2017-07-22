package com.example.frank.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.frank.login.domain.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Frank on 21/07/2017.
 */

public class LoginActivity extends CommonActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = getFirebaseAuthResultHandler();
        initViews();
        initUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    protected void initViews() {
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
    }

    protected void initUser() {
        user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.generateCryptPassword();
    }

    public void callSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void sendLoginData(View view) {
        openProgressBar();
        initUser();
        verifyLogin();
    }
    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();


                if( userFirebase != null && user.getId() == null ){
                    user.saveIdSP( LoginActivity.this, userFirebase.getUid() );
                    user.setId( userFirebase.getUid() );
                    user.setName( userFirebase.getDisplayName() );
                    user.setEmail( userFirebase.getEmail() );
                    user.saveDB();


                    callMainActivity();
                }
            }
        };

        return( callback );
    }

    private void callMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void verifyLogin() {

        user.saveTokenSP(LoginActivity.this, "");
        mAuth.signInWithEmailAndPassword(
                user.getEmail(),

                user.getPassword()
        )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (!task.isSuccessful()) {
                            showSnackbar("Login falhou");
                            return;
                        }
                    }

                });
    }


}
