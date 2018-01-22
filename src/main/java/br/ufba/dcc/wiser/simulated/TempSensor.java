package br.ufba.dcc.wiser.simulated;

import org.apache.edgent.function.Supplier;

import java.util.Random;

public class TempSensor implements Supplier<Double> {
    double currentTemp = 25.0;
    Random rand;

    public TempSensor() {
        rand = new Random();
    }

    @Override
    public Double get() {
        // Change the current temperature some random amount
        double newTemp = rand.nextGaussian() + currentTemp;
        currentTemp = newTemp;
        return currentTemp;
    }
}