package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder, btnUser, btnOrderCal, btnOrderAdmin;
    private Button btn_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnOrder = (Button) findViewById(R.id.btn_orderHome);
        btnUser = (Button) findViewById(R.id.btn_userProfile);
        btnOrderCal = (Button) findViewById(R.id.btnOrdCalMain);
        btnOrderAdmin = (Button) findViewById(R.id.btn_adminOrder);
        btn_product = (Button) findViewById(R.id.btn_product);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderHome();
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfile();
            }
        });

        btnOrderCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderCal();
            }        });

        btnOrderAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderAdmin();
                openSecond();
            }
        });
        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, ProductManagementHome.class));

            }


        });


    }

    // navigationst

    public void openOrderHome(){

        Intent intent = new Intent(this, orderActivityFirst.class);

        startActivity(intent);

    }

    public void openUserProfile(){

        Intent intent = new Intent(this, createUserProfile.class);

        startActivity(intent);

    }

    public void openOrderCal(){

        Intent intent = new Intent(this, OrderCalculation.class);

        startActivity(intent);

    }

    public void openOrderAdmin(){

        Intent intent = new Intent(this, AdminOrderHome.class);

        startActivity(intent);

    }



}