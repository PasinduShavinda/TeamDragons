package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedbackCalculation extends AppCompatActivity {


    EditText  edtFirst, edtSecond, edtThird, edtFourth, edtFeedbackTot;
    Button btnFeedCal, btnFeedClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_calculation);


            edtFirst = findViewById(R.id.et_firstweekfeed);
            edtSecond = findViewById(R.id.et_secweekfeedtot);
            edtThird = findViewById(R.id.et_thirdweekfeedtot);
            edtFourth = findViewById(R.id.et_fourthweekfeedtot);
            edtFeedbackTot = findViewById(R.id.et_totfeedback);

            btnFeedCal = findViewById(R.id.btn_feedcal);
            btnFeedClear = findViewById(R.id.btn_clearfeed);


            btnFeedClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    clear();

                }
            });

        btnFeedCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    feedbackCal();

                }
            });
        }
        public void feedbackCal(){

            int firstTot, secondTot, thirdTot, fourthTot;
            int totFeedback;

            firstTot = Integer.parseInt(edtFirst.getText().toString());
            secondTot = Integer.parseInt(edtSecond.getText().toString());
            thirdTot = Integer.parseInt(edtThird.getText().toString());
            fourthTot = Integer.parseInt(edtFourth.getText().toString());

            totFeedback =  firstTot + secondTot + thirdTot + fourthTot;

            edtFeedbackTot.setText(String.valueOf(totFeedback));

        }

        public void clear(){

            edtFirst.setText("");
            edtSecond.setText("");
            edtThird.setText("");
            edtFourth.setText("");
            edtFeedbackTot.setText("");
        }
    }

