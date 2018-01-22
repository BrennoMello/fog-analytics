package br.ufba.dcc.wiser.main;

import br.ufba.dcc.wiser.simulated.TempSensor;
import org.apache.edgent.providers.direct.DirectProvider;
import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.Topology;

import java.util.concurrent.TimeUnit;

public class TempSensorApplication {

    public static void main(String[] args) throws Exception {
        TempSensor sensor = new TempSensor();
        DirectProvider dp = new DirectProvider();
        Topology topology = dp.newTopology();

        TStream<Double> tempReadings = topology.poll(sensor, 1, TimeUnit.MILLISECONDS);
        TStream<Double> filteredReadings = tempReadings.filter(reading -> reading < 30 || reading > 10);
        filteredReadings.print();

        dp.submit(topology);
    }

}
