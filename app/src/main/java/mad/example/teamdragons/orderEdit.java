package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import mad.example.teamdragons.Database.DBHandler;

public class orderEdit extends AppCompatActivity {

    EditText customer, contact, address, postal, date;
    Button edit, delete, search;
    RadioButton wrapYes, wrapNo, cash, card;
    String wrap, payment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);

        customer = findViewById(R.id.et_nameEdit);
        contact = findViewById(R.id.et_phoneEdit);
        address = findViewById(R.id.et_addressEdit);
        postal = findViewById(R.id.et_postalEdit);
        date = findViewById(R.id.et_dateEdit);
        wrapYes = findViewById(R.id.rd_yesEdit);
        wrapNo = findViewById(R.id.rd_noEdit);
        cash = findViewById(R.id.rd_cashEdit);
        card = findViewById(R.id.rd_cardEdit);
        edit = findViewById(R.id.btn_editEdit);
        delete = findViewById(R.id.btn_deleteEdit);
        search = findViewById(R.id.btn_search_edit);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {

                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    List order = dbHandler.readAllData(customer.getText().toString());

                    if(order.isEmpty()){

                        Toast.makeText(orderEdit.this, "No order found", Toast.LENGTH_SHORT).show();
                        customer.setText(null);
                    }
                    else {

                        Toast.makeText(orderEdit.this, "Order details found! Customer: "+order.get(0).toString(), Toast.LENGTH_SHORT).show();

                        customer.setText(order.get(0).toString());
                        contact.setText(order.get(1).toString());
                        address.setText(order.get(2).toString());
                        postal.setText(order.get(3).toString());
                        date.setText(order.get(4).toString());


                        if(order.get(5).toString().equals("Yes")){

                            wrapYes.setChecked(true);

                        }else if(order.get(5).toString().equals("No")){

                            wrapNo.setChecked(true);

                        }
                        if(order.get(6).toString().equals("Cash")){

                            cash.setChecked(true);

                        }else if(order.get(6).toString().equals("Card")){

                            card.setChecked(true);
                        }


                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {


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

                Boolean status = dbHandler.updateData(customer.getText().toString(),contact.getText().toString(), address.getText().toString(),postal.getText().toString(),date.getText().toString(),wrap, payment);

                if(status){
                    Toast.makeText(orderEdit.this, "Order updated", Toast.LENGTH_SHORT).show();
                    
                }
                else{
                    Toast.makeText(orderEdit.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteData(customer.getText().toString());

                Toast.makeText(orderEdit.this, "Order Deleted", Toast.LENGTH_SHORT).show();

                customer.setText(null);
                contact.setText(null);
                address.setText(null);
                postal.setText(null);
                date.setText(null);
                wrapYes.setChecked(false);
                wrapNo.setChecked(false);
                cash.setChecked(false);
                card.setChecked(false);
            }
        });

    }

}