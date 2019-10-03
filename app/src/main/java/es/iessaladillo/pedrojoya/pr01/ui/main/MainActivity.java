package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;
import es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    EditText txtWeight, txtHeight;
    Button btnReset, btnCalculate;
    TextView lblResult;
    ImageView imgBmi;
    Float weight, height, bmi;

    BmiCalculator bmiCalculator = new BmiCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setupViews();

        btnReset.setOnClickListener(v -> runResetButton());
        btnCalculate.setOnClickListener(v -> runCalculateButton());
        // TODO
    }

    private void setupViews() {
        txtWeight = ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight = ActivityCompat.requireViewById(this, R.id.txtHeight);
        btnReset = ActivityCompat.requireViewById(this, R.id.btnReset);
        btnCalculate = ActivityCompat.requireViewById(this, R.id.btnCalculate);
        lblResult = ActivityCompat.requireViewById(this, R.id.lblResult);
        imgBmi = ActivityCompat.requireViewById(this, R.id.imgBmi);
    }

    /*
    * Buttons methods
    * */
    private void runResetButton(){
        txtWeight.setText(getText(R.string.empty));
        txtHeight.setText(getText(R.string.empty));
        lblResult.setText(getText(R.string.empty));
        imgBmi.setImageResource(R.drawable.bmi);
    }

    private void runCalculateButton(){
        if(!isProperWeight()){
            txtWeight.setError(getString(R.string.main_invalid_weight));
        }

        else if(!isProperHeight()){
            txtHeight.setError(getText(R.string.main_invalid_height));
        }

        else{
            weight = Float.parseFloat(txtWeight.getText().toString());
            height = Float.parseFloat(txtHeight.getText().toString());
            bmi = bmiCalculator.calculateBmi(weight, height);

            lblResult.setText(getString(R.string.main_bmi, bmi, bmiCategoryText(bmi)));

            setBmiImage(bmi);

            SoftInputUtils.hideKeyboard(btnCalculate);
        }
    }

    /*
    * Methods to check the input fields have proper values
    * */
    private boolean isProperWeight(){
        if(txtWeight.getText().toString().equals("0") || txtWeight.getText().toString().equals("")){
            return false;
        }

        return true;
    }

    private boolean isProperHeight(){
        if(txtHeight.getText().toString().equals("0") || txtHeight.getText().toString().equals("")){
            return false;
        }

        return true;
    }

    /*
    * Method to change the image depending on the bmi
    * */
    private void setBmiImage(float bmi){
        if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.LOW_WEIGHT){
            imgBmi.setImageResource(R.drawable.underweight);
        }
        else if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.NORMAL_WEIGHT){
            imgBmi.setImageResource(R.drawable.normal_weight);
        }
        else if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.OVERWWEIGHT){
            imgBmi.setImageResource(R.drawable.overweight);
        }
        else if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.OBESITY_GRADE_1){
            imgBmi.setImageResource(R.drawable.obesity1);
        }
        else if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.OBESITY_GRADE_2){
            imgBmi.setImageResource(R.drawable.obesity2);
        }
        else if(bmiCalculator.getBmiClasification(bmi) == BmiCalculator.BmiClasification.OBESITY_GRADE_3){
            imgBmi.setImageResource(R.drawable.obesity3);
        }
    }

    private String bmiCategoryText(Float bmi){
        switch (bmiCalculator.getBmiClasification(bmi)){
            case LOW_WEIGHT:
                return getString(R.string.main_lowWeight);
            case NORMAL_WEIGHT:
                return getString(R.string.main_normalWeight);
            case OVERWWEIGHT:
                return getString(R.string.main_overweight);
            case OBESITY_GRADE_1:
                return getString(R.string.main_obesityGrade1);
            case OBESITY_GRADE_2:
                return getString(R.string.main_obesityGrade2);
            case OBESITY_GRADE_3:
                return getString(R.string.main_obesityGrade3);
        }
        return null;
    }

    // TODO

}
