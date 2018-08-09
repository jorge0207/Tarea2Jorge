package aplicacion.android.isotomayor.tarea2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private final int PHONE_CALL_CODE = 100;

    // INICIO onCreate//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editText5);
        editTextWeb = (EditText) findViewById(R.id.editText7);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButton);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButton3);

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nroTelefono = editTextPhone.getText().toString();
                if (nroTelefono != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //newerVersion(nroTelefono);
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    }
                } else {
                    olderVersion(nroTelefono);
                }
            }

            private void olderVersion(String nroTelefono) {
                Intent intentLlamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + nroTelefono));
                if (verificarPermiso(Manifest.permission.CALL_PHONE)) {
                    if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intentLlamar);
                } else {
                    Toast.makeText(ThirdActivity.this, "sin permiso para llamar", Toast.LENGTH_SHORT).show();
                }
            }

            private void newerVersion(String nroTelefono) {

            }


        });

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamera);
            }
        });
    }
    // FIN onCreate//

    // INICIO onRequestPermissionsResult//
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        String nroTelefono = editTextPhone.getText().toString();
                        Intent intentLlamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + nroTelefono));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentLlamar);
                    } else {
                        Toast.makeText(ThirdActivity.this, "sin permiso para llamar", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    // FIN onRequestPermissionsResult//

    // INICIO verificarPermiso//
    private boolean verificarPermiso(String permission) {
        int resultado = checkCallingOrSelfPermission(permission);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
// FIN verificarPermiso//
}