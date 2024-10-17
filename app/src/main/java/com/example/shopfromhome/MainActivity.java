package com.example.shopfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Product> productList; // Lista di prodotti
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura la Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura il DrawerLayout e il toggle
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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

    // Gestione della selezione degli elementi nel NavigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_register) {
            // Apri l'activity di registrazione (implementa LoginActivity)
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            // Apri l'activity di account (implementa AccountActivity)
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            // Apri l'activity di "Chi siamo"
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawers();
        return true;
    }

    // Override per gestire il back button quando il drawer è aperto
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
