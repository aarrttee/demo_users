package com.users.demo_users;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.load(); // Load .env file from default location

        String dbUrl = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");

        System.setProperty("spring.datasource.url", dbUrl);
        System.setProperty("spring.datasource.username", dbUsername);
        System.setProperty("spring.datasource.password", dbPassword);
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

    }
}
