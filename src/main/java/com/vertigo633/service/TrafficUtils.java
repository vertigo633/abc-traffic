package com.vertigo633.service;

import java.util.Random;

/**
 * Created by Vertigo633 on 19.06.2015.
 */
public class TrafficUtils {

    public static int getLoadedBytes() {
        int loaded_forecast = (int) (10000 * Math.random());

        int[] predicted_traffic = {loaded_forecast, 0};

        Random random = new Random();
        int variant = random.nextInt(2);

        return predicted_traffic[variant];
    }
}
