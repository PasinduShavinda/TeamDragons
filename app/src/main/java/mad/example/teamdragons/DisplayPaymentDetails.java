package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandlerpayment;

public class DisplayPaymentDetails extends AppCompatActivity {


    ListView paymentlist;
    ArrayList paydataList;
    ArrayAdapter payadapter;
    DBHandlerpayment paydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_details);

        paymentlist = findViewById(R.id.payment_list);

        paydb = new DBHandlerpayment(getApplicationContext());
        paydataList = paydb.readAllPayment();

        payadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,paydataList);
        paymentlist.setAdapter(payadapter);

        paymentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String paytext = paymentlist.getItemAtPosition(i).toString();
                Toast.makeText(DisplayPaymentDetails.this, "Payment"+paytext, Toast.LENGTH_SHORT).show();
            }
        });

 }


    }
