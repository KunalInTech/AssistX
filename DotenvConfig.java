package com.assistx.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvConfig {

    @PostConstruct
    public void init() {

        // Load .env file
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String adminSecret = dotenv.get("ADMIN_SECRET");

        System.out.println("DOTENV ADMIN_SECRET = [" + adminSecret + "]");

        System.setProperty("ADMIN_SECRET", adminSecret);
        // GOOGLE OAUTH
        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));

        // DATABASE
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        // ADMIN SECRET
        System.setProperty("ADMIN_SECRET", dotenv.get("ADMIN_SECRET"));
    }
}