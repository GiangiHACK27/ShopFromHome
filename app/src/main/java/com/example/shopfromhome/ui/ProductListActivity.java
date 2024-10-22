package com.example.shopfromhome.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopfromhome.R;
import com.example.shopfromhome.model.Product;
import com.example.shopfromhome.network.ProductApi;
import com.example.shopfromhome.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        textView = findViewById(R.id.textView);
        fetchProducts();
    }

    private void fetchProducts() {
        ProductApi productApi = RetrofitClient.getRetrofitInstance().create(ProductApi.class);
        Call<List<Product>> call = productApi.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    displayProducts(products);
                } else {
                    Toast.makeText(ProductListActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Failed to retrieve products: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayProducts(List<Product> products) {
        StringBuilder productList = new StringBuilder();
        for (Product product : products) {
            productList.append("Nome: ").append(product.getName()).append("\n")
                    .append("Descrizione: ").append(product.getDescription()).append("\n")
                    .append("Prezzo: ").append(product.getPrice()).append("\n")
                    .append("Quantità in stock: ").append(product.getStockQuantity()).append("\n\n");
        }
        textView.setText(productList.toString());
    }
}
