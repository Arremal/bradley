package com.example.courier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginAccess extends AppCompatActivity {

    private EditText usuario;
    private EditText pass;
    private Button btn_ingreso;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_access);

        firebaseAuth = FirebaseAuth.getInstance();

        usuario = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);

        btn_ingreso = (Button) findViewById(R.id.btn_ingreso);
        //btn_ingreso.setOnClickListener((View.OnClickListener) this);

    }

    private void loguearse(){
        String user = usuario.getText().toString().trim();
        String contra = pass.getText().toString().trim();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(this, "se debe ingresar el usuario",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(contra)){
            Toast.makeText(this, "se debe ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(user, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginAccess.this,"Bienvenido" + usuario.getText(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public void eventoboton(View view) {
        Intent siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }
}