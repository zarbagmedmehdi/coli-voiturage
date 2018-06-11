/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import android.widget.EditText;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import WebService.util.RESTDateParam;

/**
 * @author
 */
public class DateUtil {

    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
    //   Date reference = new Date(2018+1970, 6, 31);
//        java.util.Date utilDate = new java.util.Date();
//        java.sql.Date jsqlD = java.sql.Date.valueOf( "2018-5-31" );
//        System.out.println(jsqlD+"hahyaaaaaaaaaaaaaaa");
        //System.out.println(cheakDate(reference, date));
//String reference="15:03"; String time="16:03";
//        System.out.println(cheakTime(reference,time));
//String cc= "May 30 16:40:38 GMT+00:00 2018" ;
//        System.out.println(jsqlD.getTime());
     //   System.out.println(reference.getTime());

//        int month=0;
//        if(cc.substring(0,3).equals("May")) {
//        }




    }
    public int collectDoubleData(EditText editText, Double attr){
        if ( !editText.getText().toString().isEmpty()   & editText!=null ){
            attr= new Double(editText.getText().toString());
            return 1;
        }else return -1;

    }
    public  static Double geth(String key,JSONObject json) {

        Double value=0.0;

        try {
            value=Double.parseDouble(json.get(key).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value ;
    }
    public  static Double getDouble(Object number) {
        Double doub=Double.parseDouble(number.toString());
        return doub ;
    }
    public static Date   convertToDate(String receivedDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(receivedDate);
        System.out.println(date);
        return date;
    }

    public static int checkDate(RESTDateParam reference,RESTDateParam  date) {
Date reference2=reference.getDate();
Date date2=date.getDate();
        if (date2.getYear() != reference2.getYear()) return -1;
        if (date2.getMonth() != reference2.getMonth()) return -1;
        if (date2.getDate() < reference2.getDate()) return -1;
        if (date2.getDate() != reference2.getDate() & reference2.getDate() < date2.getDate() + 1 & date2.getYear() == reference2.getYear() & date2.getMonth() == reference2.getMonth())
            return 2;
        if (date2.getYear() == reference2.getYear() & date2.getMonth() == reference2.getMonth() & date2.getDate() == reference2.getDate())
            return 1;
        else return -1;

    }

    public static int checkTime(String reference, String time, int checkDate) {
        int ref =0;
        int tim=0;
        if(reference.substring(1, 2).equals(":")) {
             ref = Integer.parseInt(reference.substring(0, 1));
        }
        if(!reference.substring(1, 2).equals(":")) {
            ref = Integer.parseInt(reference.substring(0, 2));
        }
        if(time.substring(1, 2).equals(":")) {
            tim = Integer.parseInt(time.substring(0, 1));
        }
        if(!time.substring(1, 2).equals(":")) {
            tim = Integer.parseInt(time.substring(0, 2));
        }
        System.out.println(""+ref+"cc"+tim);

//        if (cheakDate == 1) {
//            System.out.println("cc rah cheakDate=1"+ref+"cc"+tim);
//            if (ref < tim) return 1;
//            if (cheakDate==2){
//                return 1;
//            }
//            else return -1;
//        } else return -1;
        if (checkDate == 1) {
            if (ref < tim)
                return 1;
            else
                return  -1;}
        if (checkDate==2){
            return 1;
        }
        else return -1;

    }




    public static String formateDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return "";
        }
    }

    public static Date parse(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date parseTime(String date) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            return simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static java.sql.Date convertFormUtilToSql(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public static Date getDebutAnnee(int annee) {
        Date debAnnee = parse(annee + "-01-01");
        return debAnnee;
    }

    public static Date getDateLimite(int annee) {
        Date dateLimite = parse(annee + "-04-01");
        return dateLimite;
    }

    public static int getNombreMoisRetard(int annee) {
        Long dateLimite = getDateLimite(annee).getTime();
        Long dateActuelle = new Date().getTime();
        return ((Number) ((dateActuelle - dateLimite) / (1000L * 60L * 60L * 24L * 30L))).intValue();
    }
}
