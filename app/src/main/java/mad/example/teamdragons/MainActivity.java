package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnOrder = (Button) findViewById(R.id.btn_orderHome);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecond();
            }
        });
    }

    // navigation

    public void openSecond(){

        Intent intent = new Intent(this, orderActivityFirst.class);

        startActivity(intent);

    }
}