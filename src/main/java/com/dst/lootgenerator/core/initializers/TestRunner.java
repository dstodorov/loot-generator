package com.dst.lootgenerator.core.initializers;

import com.dst.lootgenerator.items.services.AttributeService;
import com.dst.lootgenerator.items.services.InitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

    @Value("${application.configuration.init-test-data}")
    private boolean initTestData;

    private final InitializerService initializerService;

    @Override
    public void run(String... args) throws Exception {
        if (initTestData) {
            this.initializerService.loadInitialData();
        }
    }
}
