package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mad.example.teamdragons.Database.DBHandlerFeedback;

public class Feedback extends AppCompatActivity {

    EditText customerName, email, description;
    Button addFeedback, updateFeedback, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        customerName = findViewById(R.id.et_nameFeed);
        email = findViewById(R.id.et_entercusfeedemail);
        description = findViewById(R.id.et_enterfeeddescrip);
        addFeedback = findViewById(R.id.btn_addFeedback);
        updateFeedback = findViewById(R.id.btn_editFeedback);
        view = findViewById(R.id.btn_view_feedback);

        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), feedbackList.class);
                startActivity(i);
            }
        });

        updateFeedback.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), UpdateFeedback.class);
                startActivity(i);
            }
        });


        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                long newID = dbHandler.addData(customerName.getText().toString(), email.getText().toString(), description.getText().toString());

                Toast.makeText(Feedback.this,"Feedback added, Feedback ID: "+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), UpdateFeedback.class);
                startActivity(i);

                customerName.setText(null);
                email.setText(null);
                description.setText(null);

            }
        });

    }
}