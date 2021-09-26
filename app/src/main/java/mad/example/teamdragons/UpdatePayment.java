package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mad.example.teamdragons.Database.DBHandlerpayment;

public class UpdatePayment extends AppCompatActivity {

    EditText cardnum,cashholderN,expird,cvc;
    Button delete, update, search, refund;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_payment);

        cardnum = findViewById(R.id.etUpdatecardN);
        cashholderN = findViewById(R.id.searchpay);
        expird = findViewById(R.id.etUpdateExpdate);
        cvc = findViewById(R.id.etUpdateCvc);
        delete = findViewById(R.id.btnDeletePay);
        update = findViewById(R.id.etUpdatePay);
        search = findViewById(R.id.searchbtn);
        refund = findViewById(R.id.refundedUI);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerpayment dbHandlerpayment = new DBHandlerpayment(getApplicationContext());
                List pay = dbHandlerpayment.readAllPayment(cashholderN.getText().toString());

                //check if the cash holder name is alredy here or not
                if(pay.isEmpty()){
                    Toast.makeText(UpdatePayment.this, "No Payment User", Toast.LENGTH_SHORT).show();
                    cashholderN.setText(null);
                }
                else{
                    Toast.makeText(UpdatePayment.this, "Payment User Founded", Toast.LENGTH_SHORT).show();


                    cardnum.setText(pay.get(0).toString());
                    cashholderN.setText(pay.get(1).toString());
                    expird.setText(pay.get(2).toString());
                    cvc.setText(pay.get(3).toString());
                }

            }
        });

        //update code
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerpayment dbHandlerpayment = new DBHandlerpayment(getApplicationContext());

                Boolean status = dbHandlerpayment.updatePayment(cardnum.getText().toString(),cashholderN.getText().toString(),expird.getText().toString(),cvc.getText().toString());
                if(status){
                    Intent i = new Intent(getApplicationContext(),PaymentSuccess.class);
                    startActivity(i);
                    Toast.makeText(UpdatePayment.this, "Payment Details are Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpdatePayment.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //delete function
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerpayment dbHandlerpayment = new DBHandlerpayment(getApplicationContext());
                dbHandlerpayment.deletePay(cashholderN.getText().toString());

                Toast.makeText(UpdatePayment.this, "Succefully Deleted", Toast.LENGTH_SHORT).show();

                //after deleted payment record all columns are being null
                cardnum.setText(null);
                cashholderN.setText(null);
                expird.setText(null);
                cvc.setText(null);

            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatePayment.this, "Refunded Succeffully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),RefundedSucess.class);
                startActivity(i);
            }
        });


    }
}

