package io.codercm.playground.providers;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PortMonitor {

    public int getRandomPortForCodeServer() {
        Random randomGenerator = new Random();
        int low = 8443;
        int high = 9000;
        return randomGenerator.nextInt(high-low) + low;
    }

    public int getRandomPortForPlaygroundApp() {
        Random randomGenerator = new Random();
        int low = 3000;
        int high = 3050;
        return randomGenerator.nextInt(high-low) + low;
    }
}
