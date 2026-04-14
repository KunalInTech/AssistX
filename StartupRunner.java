package com.assistx.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class StartupRunner {

    private Process fastApiProcess;

    @Bean
    public CommandLineRunner startFastAPI() {
        return args -> {
            try {

                System.out.println("Starting FastAPI server...");

                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c",
                        "python -m uvicorn app:app --reload --port 5000"
                );

                builder.directory(new File("ml-service"));
                builder.inheritIO();

                fastApiProcess = builder.start();

                // SHUTDOWN HOOK (KILL ENTIRE TREE)
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        System.out.println("Force stopping FastAPI (tree kill)...");

                        if (fastApiProcess != null && fastApiProcess.isAlive()) {

                            new ProcessBuilder(
                                    "cmd.exe", "/c",
                                    "taskkill /F /T /PID " + fastApiProcess.pid()
                            ).inheritIO().start();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));

            } catch (Exception e) {
                System.out.println("Failed to start FastAPI");
                e.printStackTrace();
            }
        };
    }
}