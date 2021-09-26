package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mad.example.teamdragons.Database.DBHandlerFeedback;

public class UpdateFeedback extends AppCompatActivity {

    EditText customerName, email, description;
    Button updateFeedback, deleteFeedback, searchFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);

            customerName = findViewById(R.id.et_cusnamefeedback);
            email = findViewById(R.id.et_emailfeedback);
            description = findViewById(R.id.et_editdescrip);
            updateFeedback = findViewById(R.id.btn_editfeedback);
            deleteFeedback = findViewById(R.id.btn_deleteFeedback);
            searchFeedback = findViewById(R.id.btn_searchcusnamefeedback);


            searchFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                    List feedback = dbHandler.readAllData(customerName.getText().toString());

                    if(feedback.isEmpty()){

                        Toast.makeText(UpdateFeedback.this, "No Feedback Found", Toast.LENGTH_SHORT).show();
                        customerName.setText(null);
                    }
                    else {

                        Toast.makeText(UpdateFeedback.this, "Feedback details found! Customer Name: "+feedback.get(0).toString(), Toast.LENGTH_SHORT).show();

                        customerName.setText(feedback.get(0).toString());
                        email.setText(feedback.get(1).toString());
                        description.setText(feedback.get(2).toString());

                    }

                }
            });

        updateFeedback.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {


                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());

                    Boolean status = dbHandler.updateData(customerName.getText().toString(),email.getText().toString(), description.getText().toString());

                    if(status){
                        Toast.makeText(UpdateFeedback.this, "Feedback Updated", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(UpdateFeedback.this, "Feedback Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        deleteFeedback.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                    dbHandler.deleteData(customerName.getText().toString());

                    Toast.makeText(UpdateFeedback.this, "Feedback Deleted", Toast.LENGTH_SHORT).show();

                    customerName.setText(null);
                    email.setText(null);
                    description.setText(null);
                }
            });

        }

    }
