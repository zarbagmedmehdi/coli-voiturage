package service;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.application.zarbagaskazay.colivoiturage.InscriptionActivity;
import com.application.zarbagaskazay.colivoiturage.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.User;

public class UserService {


    public int setParams( String telephone, String password1,String password2,String email) {

        int result = passwordRepeat(password1, password2);
        int result2 = telephoneVerification(telephone);


            if(result2==-1 && (result==-2 || result==-3))
                return -4 ;
             else if (result2 == -1)
                return result2;
             else if(result==-2 || result==-3)
                return result;
              else return 1;
    }
        public Object[] message(String telephone, String password1, String password2,String email){
            int  result =setParams(telephone,password1,password2,email);
            String message="";
            switch(result){
                case -4: message= " données incorrectes dans le formulaire";break;
                case  -1:message=" fomat telephone erronée";break;
                case -2: message="les mots de passe ne sont pas similaires";break;
                case -3: message=  " taille minimum du mot de passe est 5";break;
                case 1 :message= " vos donnnées sont enregistrés";break ;
            }

         //   String myString = myBundle.getString("myS");
           // String myInt = myBundle.getInt("myI");
            return new Object[]{result,message};
        }

    public int passwordRepeat(String mdp1, String mdp2) {
       if( mdp1.length()>5){
           if(mdp1.compareTo(mdp2)==0) return 1;
           else return -2;
       }
       else
           return -3;

    }

    public int telephoneVerification(String tel) {
        if(tel.isEmpty()==true || tel.substring(0)=="0"){
             return -1 ;
        }


            return 1;

    }

}