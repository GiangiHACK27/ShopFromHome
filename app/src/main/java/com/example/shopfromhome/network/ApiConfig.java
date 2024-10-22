package com.example.shopfromhome.network;

public class ApiConfig {
    // Cambia l'URL qui
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // per emulatori
    // private static final String BASE_URL = "http://172.19.129.46:8080/"; // per dispositivi fisici

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
