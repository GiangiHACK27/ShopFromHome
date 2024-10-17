package com.example.shopfromhome;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList; // Lista di prodotti

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inizializza la lista dei prodotti
        initializeProductList();

        // Configura il RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    // Metodo per inizializzare la lista dei prodotti
    private void initializeProductList() {
        productList = new ArrayList<>();

        // Aggiungi prodotti fittizi alla lista con immagini reali
        productList.add(new Product("Apple", 0.99, "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg"));
        productList.add(new Product("Banana", 0.59, "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg"));
        productList.add(new Product("Orange", 0.79, "https://upload.wikimedia.org/wikipedia/commons/c/c8/Orange_icon.png"));
        productList.add(new Product("Strawberry", 2.99, "https://upload.wikimedia.org/wikipedia/commons/0/0b/Strawberry.jpg"));
        productList.add(new Product("Blueberry", 3.49, "https://upload.wikimedia.org/wikipedia/commons/6/6e/Blueberry.jpg"));
        productList.add(new Product("Pineapple", 1.49, "https://upload.wikimedia.org/wikipedia/commons/5/5d/Pineapple_1.jpg"));
        productList.add(new Product("Grapes", 2.99, "https://upload.wikimedia.org/wikipedia/commons/1/13/Red_Grapes.jpg"));
        productList.add(new Product("Watermelon", 0.89, "https://upload.wikimedia.org/wikipedia/commons/5/5c/Watermelon.jpg"));
        productList.add(new Product("Mango", 1.99, "https://upload.wikimedia.org/wikipedia/commons/1/15/Mango.jpg"));
        productList.add(new Product("Peach", 1.49, "https://upload.wikimedia.org/wikipedia/commons/0/06/Peach.jpg"));
    }

    public void cliccato(View v) {
        // Funzione per gestire il clic (da implementare)
    }
}
