package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayDetailsAdmin extends AppCompatActivity {

    Button view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_details_admin);

        view = findViewById(R.id.viewdetailspaybtn);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DisplayPaymentDetails.class);
                startActivity(i);
            }
        });
    }
}