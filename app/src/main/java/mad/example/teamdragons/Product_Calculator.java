package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Product_Calculator extends AppCompatActivity {

    EditText calc_Pname,calc_Quantity,calc_Price;
    Button calc_button, calc_clear;
    TextView calc_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_calculator);

        calc_Pname= findViewById(R.id.calc_Pname);
        calc_Quantity = findViewById(R.id.calc_Quantity);
        calc_Price = findViewById(R.id.calc_Price );
        calc_button = findViewById(R.id.calc_button);
        calc_clear = findViewById(R.id.calc_clear);
        calc_Answer = findViewById(R.id.calc_Answer);
    }
}