package com.application.zarbagaskazay.colivoiturage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;


public class ActivityHelper {
    public String getStr(EditText editText) {
        String string = editText.getText().toString();
        return string;

    }

    public int collectStringData(EditText editText, String attr) {
        if (!editText.getText().toString().isEmpty() & editText != null) {
            attr = editText.getText().toString();
            return 1;
        } else return -1;

    }

    public void enterData(String field, JSONObject jsonObject, EditText editText) {
        if (editText.getText().toString() != "" & editText.getText().toString() != null & !editText.getText().toString().isEmpty()) {
            try {
                jsonObject.put(field, editText.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int collectDoubleData(EditText editText, double value) {
        String str = editText.getText().toString();
        if (str.trim().equals("")) {

            System.out.println("EditText is empty.");
            return -1;
        }

        if (!str.isEmpty()) {
            try {
                value = Double.parseDouble(str);

            } catch (Exception e1) {
                e1.printStackTrace();

            }
            return 1;

        } else return -1;
    }


    public void setStr(EditText editText, String string) {
        editText.setText(string);
    }

    public String matriculeGeneration(EditText editText1, EditText editText2, EditText editText3) {
        return getStr(editText1) + "" + getStr(editText2) + "" + getStr(editText3);

    }

    public String spinnerText(Spinner spinner) {
        return spinner.getSelectedItem().toString();


    }

    public int spinnerCheakNull(Spinner spinner) {

        if (spinner.getSelectedItem().toString() != null & spinner.getSelectedItem().toString() != "")
            return 1;
        else return -1;


    }

    public int jsonParserTo(JSONObject json, String name) {
        int result = 0;
        try {
            result = (int) json.get(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String onDateSetHelper(DatePicker datePicker, int year, int month, int day, Button button, String TAG) {
        month = month + 1;
        Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);
        String dateString = year + "-" + month + "-" + day;
        java.sql.Date date = java.sql.Date.valueOf(dateString);


        System.out.println("ha date a wld l3bd" + date);

        String dateString2 = day + "/" + month + "/" + year;
        button.setText(dateString2);
        return dateString;
    }

    public void cc() {
    }

    public int indexOfRadio(RadioGroup radioButtonGroup) {
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        RadioButton radioButton = radioButtonGroup.findViewById(radioButtonID);
        int idx = radioButtonGroup.indexOfChild(radioButton);
        return idx;
    }

    public int idOfRadio(RadioGroup radioButtonGroup)

    {
        int id = 0;
        String type = stringOfRadio(radioButtonGroup);
        if (type == "voiture") id = 1;
        if (type == "camionnette") id = 2;

        if (type == "camion") id = 3;

        return id;
    }

    public String stringOfRadio(RadioGroup radioButtonGroup)

    {
        int idx = indexOfRadio(radioButtonGroup);
        RadioButton r = (RadioButton) radioButtonGroup.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return selectedtext;
    }

    public void startActiv(Context context, Class distination, Bundle bundle) {

        Intent intent = new Intent(context, distination);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }
}