package tw.idv.mirochiu.transparentbgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = findViewById(R.id.textView);
        t.setText(this.getClass().getSimpleName());

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        (BarActivity.this instanceof NoBarActivity)?
                        new Intent(BarActivity.this,
                            BarActivity.class):
                        new Intent(BarActivity.this,
                                NoBarActivity.class)
                );
                finish();
            }
        });
    }
}
