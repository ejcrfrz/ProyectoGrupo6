package com.pucp.proyectogrupo6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pucp.proyectogrupo6.Entidades.Cursos;
import com.pucp.proyectogrupo6.Entidades.Usuario;

import java.util.Arrays;
import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
    public void ingresarOnClick(View view) {
        List<AuthUI.IdpConfig> listaProveeedores = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.logo_app)
                        .setTheme(R.style.AppTheme)
                        .setAvailableProviders(listaProveeedores)
                        .build(),1);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Log.d("infoApp","inicio de sesión exitoso!");
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.setLanguageCode("es-419");
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser!=null){

                    final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                    final String uid = currentUser.getUid();
                    final String name =currentUser.getDisplayName();
                    final String email=currentUser.getEmail();
                    Log.d("infoApp",databaseReference.child("usuario/"+uid).getDatabase().toString());


                  databaseReference.child("usuarios").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {

                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                          if(dataSnapshot.getValue()==null){
                              Log.d("infoApp2","inicio de sesión exitoso!");
                              Usuario usuario=new Usuario();
                              usuario.setNombre(name);
                              usuario.setCorreo(email);
                              databaseReference.child("usuarios").child(uid).setValue(usuario);

                          }
                          if (dataSnapshot.getValue()!=null){
                              int inf=dataSnapshot.child("infraestructura").getValue(Integer.class);
                              if (inf ==1){
                                  Log.d("infraestructura","mensage");
                              }
                          }



                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });


                    if(!currentUser.isEmailVerified()){
                        currentUser.sendEmailVerification()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(ActivityLogin.this,
                                                "Se le ha enviado un correo para validar su cuenta",
                                                Toast.LENGTH_SHORT).show();
                                        Log.d("infoApp","Envío de correo exitoso");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("infoApp","error al enviar el correo");
                                    }
                                });
                        this.finish();
                    }
                    startActivity(new Intent(ActivityLogin.this,ListaUsuarioActivity.class));
                }else{


                }

            }else{
                Log.d("infoApp","Inicio erroneo");
            }
        }

    }




}
