package aplicacion.android.isotomayor.tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private Button btn;
    private CheckBox chb;
    private Switch swt;
    private final String Saludo = "Hola a todos desde activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, secondactivity.class);
                intent.putExtra("sl2", Saludo);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Clieck en siguiente", Toast.LENGTH_SHORT).show();

            }
        });

        chb = (CheckBox) findViewById(R.id.checkBox);
        chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CheckBox", Toast.LENGTH_SHORT).show();

            }
        });

        swt = (Switch) findViewById(R.id.switch1);
        swt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, " Switch 1", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void miMetodo(View v) {
        //int a = 4 ;
        Toast.makeText(MainActivity.this, "click en el boton", Toast.LENGTH_SHORT).show();
    }


    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "boton presionado", Toast.LENGTH_SHORT).show();
    }
}
