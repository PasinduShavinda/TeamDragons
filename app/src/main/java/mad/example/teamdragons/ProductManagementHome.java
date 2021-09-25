package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductManagementHome extends AppCompatActivity {

    Button add, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_home);

        add = findViewById(R.id.btn_Home_Add);
        view = findViewById(R.id.btn_Home_View);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductManagementHome.this, ViewProducts.class));
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, AddProducts.class);
        startActivity(intent);
    }



}