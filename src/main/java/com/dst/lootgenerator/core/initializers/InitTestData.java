package com.dst.lootgenerator.core.initializers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitTestData implements CommandLineRunner {

    @Value("${application.configuration.init-test-data}")
    private boolean initTestData;

    @Override
    public void run(String... args) throws Exception {
        if (initTestData) {
            
        }
    }
}
