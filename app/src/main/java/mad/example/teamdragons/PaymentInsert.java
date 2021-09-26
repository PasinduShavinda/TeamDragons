package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mad.example.teamdragons.Database.DBHandlerpayment;

public class PaymentInsert extends AppCompatActivity {

    EditText cardnum,cashholderN,expird,cvc;
    Button proceed, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_insert);

        cardnum = findViewById(R.id.etCardnumberPI);
        cashholderN = findViewById(R.id.etCashHolderNPI);
        expird = findViewById(R.id.etExpirePI);
        cvc = findViewById(R.id.etCvcPI);
        proceed = findViewById(R.id.btnpay);
        edit = findViewById(R.id.btneditPI);




       proceed.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               DBHandlerpayment dbHandlerpayment = new DBHandlerpayment(getApplicationContext());

               //get all varibles name passing db handler 
               long newID = dbHandlerpayment.paymentAdd(cardnum.getText().toString(),cashholderN.getText().toString(),expird.getText().toString(),cvc.getText().toString());
               
               //create toast m.g
               Toast.makeText(PaymentInsert.this, "Payment was Successfully Payment ID"+ newID, Toast.LENGTH_SHORT).show();

               Intent i = new Intent(getApplicationContext(),PaymentSuccess.class);
               startActivity(i);
               cardnum.setText(null);
               cashholderN.setText(null);
               expird.setText(null);
               cvc.setText(null);
           }
       });

       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(),UpdatePayment.class);
               startActivity(i);
           }
       });
    }


}