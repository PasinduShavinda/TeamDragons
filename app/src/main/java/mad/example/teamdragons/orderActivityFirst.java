package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import mad.example.teamdragons.Database.DBHandler;

public class orderActivityFirst extends AppCompatActivity {

    EditText customer, contact, address, postal, date;
    Button add, edit, view;
    RadioButton wrapYes, wrapNo, cash, card;
    String wrap, payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_first);

        customer = findViewById(R.id.et_nameOF);
        contact = findViewById(R.id.et_phoneOF);
        address = findViewById(R.id.et_addressOF);
        postal = findViewById(R.id.et_postalOF);
        date = findViewById(R.id.et_dateOF);
        wrapYes = findViewById(R.id.rd_wrapYesOF);
        wrapNo = findViewById(R.id.rd_wrapNoOF);
        cash = findViewById(R.id.rd_cashOF);
        card = findViewById(R.id.rd_cardOF);
        add = findViewById(R.id.btn_submitOF);
        edit = findViewById(R.id.btn_editOF);
        view = findViewById(R.id.btn_viewOF);


        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), orderList.class);
                startActivity(i);
            }
        });


        edit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), orderEdit.class);
                startActivity(i);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(wrapYes.isChecked()){

                    wrap = "Yes";
                }
                else if (wrapNo.isChecked()){
                    wrap = "No";
                }

                if(cash.isChecked()){
                    payment = "Cash";
                }
                else if(card.isChecked()){
                    payment = "Card";
                }


                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addData(customer.getText().toString(), contact.getText().toString(), address.getText().toString(), postal.getText().toString(), date.getText().toString(), wrap, payment);

                Toast.makeText(orderActivityFirst.this,"Order added, Order ID: "+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), orderEdit.class);
                startActivity(i);

                customer.setText(null);
                contact.setText(null);
                address.setText(null);
                postal.setText(null);
                date.setText(null);
                wrapYes.setChecked(true);
                wrapNo.setChecked(false);
                cash.setChecked(true);
                card.setChecked(false);

            }
        });




    }

}