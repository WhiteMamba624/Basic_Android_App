package ro.ibm.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static ro.ibm.myapplication.SplashScreen.INTENT_EXTRA_KEY;

public class WelcomeScreen extends AppCompatActivity {

    private static final int PERMISSION_READ_CONTACTS=100;
    private static final int PERMISSION_RECORD_AUDIO=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button permissionBtn=findViewById(R.id.PermissionBtn);

        TextView welcome=findViewById(R.id.welcome);
        if(getIntent()!=null)
        {
            String receiveString= getIntent().getStringExtra(INTENT_EXTRA_KEY);
            receiveString=receiveString.split("@")[0];
            String showString=welcome.getText().toString()+" "+receiveString;
            welcome.setText(showString.toUpperCase());

        }
        permissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions={Manifest.permission.READ_CONTACTS,Manifest.permission.RECORD_AUDIO};
            askPermission(permissions,PERMISSION_READ_CONTACTS);

            }
        });
    }
    private void askPermission(String[] permissions, int requestCode)
    {
//        if (ContextCompat.checkSelfPermission(WelcomeScreen.this, permissions) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(WelcomeScreen.this,permissions, requestCode);
      //  }
//        else {
//            Toast.makeText(WelcomeScreen.this, "Permission already granted", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(WelcomeScreen.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(WelcomeScreen.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}