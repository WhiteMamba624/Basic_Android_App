package ro.ibm.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        mAuth = FirebaseAuth.getInstance();
        Button registerBtn = findViewById(R.id.register);
        EditText password = findViewById(R.id.textPasswordRegister);
        EditText email = findViewById(R.id.textEmailRegister);
        CheckBox checkTerms = findViewById(R.id.checkBoxTerms);
        AlertDialog.Builder alertCheckTerms = new AlertDialog.Builder(this);
        alertCheckTerms.setTitle("Attention")
                .setMessage("Please accept the terms")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRegister = email.getText().toString().trim();
                String passwordRegister = password.getText().toString().trim();
                if (checkTerms.isChecked()) {
                    if (isValidEmail(emailRegister)&&isValidPassword(passwordRegister)) {
                        registerUser(emailRegister, passwordRegister);
                    }
                    else {
                        Toast.makeText(RegisterScreen.this, "Please check the credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    alertCheckTerms.show();
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goToLoginScreen();
                        } else {
                            Toast.makeText(RegisterScreen.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToLoginScreen() {
//        Intent intentGoToLoginScreen = new Intent(this, SplashScreen.class);
//        startActivity(intentGoToLoginScreen);
        onBackPressed();
    }

    private boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() > 4;
    }
}