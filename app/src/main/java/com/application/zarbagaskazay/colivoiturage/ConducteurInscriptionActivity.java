package com.application.zarbagaskazay.colivoiturage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import WebService.util.UrlClass;
import bean.Conducteur;

public class ConducteurInscriptionActivity extends AppCompatActivity {
    TextView nameText;
    EditText matriculeInput1;
    EditText matriculeInput2;
    EditText matriculeInput3;
    Spinner marque;
    Spinner couleur;
    RadioGroup typeVehicule;
    String matricule;
    String marqueText;
    String couleurText;
    String typeVehiculeText;
    int typeVehiculeId;

    ////////
    private ImageButton btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    private Bitmap my_image;
    /////////////////////

    int id;
    UrlClass url = new UrlClass();
    ActivityHelper activityHelper = new ActivityHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_voiture);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);//if it's a string you stored.
        matriculeInput1 = findViewById(R.id.matriculeInput1);
        matriculeInput2 = findViewById(R.id.matriculeInput2);
        matriculeInput3 = findViewById(R.id.matriculeInput3);
        marque = (Spinner) findViewById(R.id.marqueSpinner);
        couleur = (Spinner) findViewById(R.id.couleurSpinner);
        typeVehicule = (RadioGroup) findViewById(R.id.radioGroup);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        btnChoose = (ImageButton) findViewById(R.id.btnChoose);
        btnUpload = (ImageButton) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}
        private void uploadImage(int id) {

            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child("images/" + "id="+id);
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(ConducteurInscriptionActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(ConducteurInscriptionActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });
            }
        }

    public void collectData() {
        matricule = activityHelper.matriculeGeneration(matriculeInput1, matriculeInput2, matriculeInput3);
        marqueText = activityHelper.spinnerText(marque);
        couleurText = activityHelper.spinnerText(couleur);
        typeVehiculeId = activityHelper.idOfRadio(typeVehicule);
        System.out.println(matricule + "" + marqueText + "" + couleurText + "" + id + "" + typeVehiculeText);


    }

    public void addCar1(View view) {
        if(id!=0)
            uploadImage(id);
        collectData();
        JSONObject params = new JSONObject();

        try {params.put("image","images/" + "id="+id);
            params.put("matricule", matricule);
            params.put("marque", marqueText);
            params.put("couleur", couleurText);
            params.put("id", id);
            params.put("idType", typeVehiculeId);
            params.put("image", "test");
            System.out.println(matricule + "" + marqueText + "" + couleurText + "" + id + "" + typeVehiculeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url.urlAddVoiture
                , params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int result = response.getInt("result");
                            String message = response.getString("message");
                            System.out.println(message + "" + result);
                            Toast.makeText(ConducteurInscriptionActivity.this, "cc ha lmessage" + message, Toast.LENGTH_SHORT).show();
                            if (result == 1) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", id);
                                activityHelper.startActiv(ConducteurInscriptionActivity.this, MenuConducteurActivity.class, bundle);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConducteurInscriptionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage");
                    }
                });

        VolleySingleton.getInstance(ConducteurInscriptionActivity.this).addToRequestQueue(jsonObjReq);


    }


}
