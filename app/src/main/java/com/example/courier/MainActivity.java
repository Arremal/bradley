package com.example.courier;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private List<String> productos = new ArrayList<>();

    private String producto [] = {"botella", "chettos"};
    private String destinos [] = {"lima", "pozuzo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_views);
        tv1 = (TextView)findViewById(R.id.tv1);
        lv1 = (ListView)findViewById(R.id.lv1);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("producto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot producto: task.getResult()){

                            }
                        }else{
                            Log.w("APP", "error al traer documentos. ", task.getException());
                        }
                    }
                });

        db.collection("producto").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                productos.clear();

                for(DocumentSnapshot snapshot: queryDocumentSnapshots){
                    productos.add(snapshot.getString("nombre"));
                }
                ArrayAdapter<String>adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item_pedidos, productos);
                adapter.notifyDataSetChanged();
                lv1.setAdapter(adapter);
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        tv1.setText("el producto seleccionado es:" + lv1.getItemAtPosition(i) + "su destino es " + destinos[i]);
                    }
                });
            }
        });

      /*  ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_pedidos, producto);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("el producto seleccionado es:" + lv1.getItemAtPosition(i) + "su destino es " + destino[i]);
            }
        });*/
    }


    public void eventoboton(View view) {
    }
}