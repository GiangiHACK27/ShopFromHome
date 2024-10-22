package com.example.shopfromhome.network;

import com.example.shopfromhome.model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("/api/products")
    Call<List<Product>> getProducts();
}
