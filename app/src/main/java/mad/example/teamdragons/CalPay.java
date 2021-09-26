package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalPay extends AppCompatActivity {

    EditText productP, productQuanty, priceofselling, qtyofselling, discountOfSelling, totalSellingOneDay,totproductCost;
    Button sumProductCost, sumSellingProduct,clearcal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_pay);

        productP = findViewById(R.id.productprice);
        productQuanty = findViewById(R.id.qty);
        priceofselling = findViewById(R.id.sellPrice);
        qtyofselling = findViewById(R.id.qtyofsell);
        discountOfSelling = findViewById(R.id.discountodsell);
        totalSellingOneDay = findViewById(R.id.totalsell);
        totproductCost = findViewById(R.id.totalproductC);
        sumProductCost = findViewById(R.id.sum);
        sumSellingProduct = findViewById(R.id.sumofsellingProducts);
        clearcal = findViewById(R.id.calculator);



       sumProductCost.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sumofcall();
           }
       });

       sumSellingProduct.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               buyingCall();
           }
       });

       clearcal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              emptycal();
           }
       });



}

    private void emptycal() {
        productP.setText(null);
        productQuanty.setText(null);
        priceofselling.setText(null);
        qtyofselling.setText(null);
        discountOfSelling.setText(null);
        totalSellingOneDay.setText(null);
        totproductCost.setText(null);
    }

    //product buying
    public void sumofcall(){
    double price_Buy;
    int Quantity;
    double tot;

    price_Buy =  Integer.parseInt(productP .getText().toString());
        Quantity = Integer.parseInt(productQuanty.getText().toString());

    tot = price_Buy * Quantity;
        sumSellingProduct.setText(String.valueOf(tot));

}

    //returns calculation
    public void buyingCall(){
        double priceP;
        int QuantityP;
        double tot2pay;

        priceP =  Integer.parseInt(sumSellingProduct .getText().toString());
        QuantityP = Integer.parseInt(productQuanty.getText().toString());

        tot2pay = priceP * QuantityP;
        sumSellingProduct.setText(String.valueOf(tot2pay));

    }


}

