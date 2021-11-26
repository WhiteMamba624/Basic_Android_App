package ro.ibm.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY = "intent_extra_key";
//    public static final String SHARED_PREF_EMAIL = "shared_pref_email";
//    public static final String SHARED_PREF_PASSWORD = "shared_pref_password";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        Button button = findViewById(R.id.login);
        EditText editTextEmail = findViewById(R.id.textEmail);
        EditText editTextPassword = findViewById(R.id.textPassword);
        TextView errorMessage = findViewById(R.id.errorMessage);
//        checkEmailAlreadyExistingInSharedPref(editTextEmail);
//        checkPasswordAlreadyExistingInSharedPref(editTextPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (isValidEmail(email) && !password.isEmpty()) {
                    errorMessage.setText("");
//                    saveEmail(email);
//                    savePassword(password);
//                    goToWelcomeScreen(email);
                    loginUser(email,password);
                } else if (!isValidEmail(email)) {
                    errorMessage.setText(getResources().getString(R.string.invalidEmail));
                } else {
                    errorMessage.setText(getResources().getString(R.string.emptyPassword));
                }
            }
        });

        TextView registerClick=findViewById(R.id.registerText);
        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen();
            }
        });
    }

    private void goToRegisterScreen()
    {
        Intent intentGoToRegisterScreen=new Intent(this,RegisterScreen.class);
        startActivity(intentGoToRegisterScreen);
    }

    private void goToWelcomeScreen() {
        Intent intentGoToWelcomeScreen = new Intent(this, Dashboard.class);
//        intentGoToWelcomeScreen.putExtra(INTENT_EXTRA_KEY, sendText);
        startActivity(intentGoToWelcomeScreen);
    }

//    private void checkEmailAlreadyExistingInSharedPref(EditText editTextEmail) {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        String alreadyExistingEmail = sharedPref.getString(SHARED_PREF_EMAIL, null);
//        if (alreadyExistingEmail != null) {
//            editTextEmail.setText(alreadyExistingEmail);
//        }
//    }
//    private void checkPasswordAlreadyExistingInSharedPref(EditText editTextPassword) {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        String alreadyExistingEmail = sharedPref.getString(SHARED_PREF_PASSWORD, null);
//        if (alreadyExistingEmail != null) {
//            editTextPassword.setText(alreadyExistingEmail);
//        }
//    }

//    private void saveEmail(String email) {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(SHARED_PREF_EMAIL, email);
//        editor.apply();
//    }

//    private void savePassword(String password) {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(SHARED_PREF_PASSWORD, password);
//        editor.apply();
//    }

    public boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private void loginUser(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            goToWelcomeScreen();
                        }
                        else{
                            Toast.makeText(SplashScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}