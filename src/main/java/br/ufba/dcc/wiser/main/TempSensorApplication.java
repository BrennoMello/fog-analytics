package br.ufba.dcc.wiser.main;

import org.apache.edgent.connectors.mqtt.MqttStreams;
import org.apache.edgent.providers.direct.DirectProvider;
import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.Topology;


public class TempSensorApplication {

    public static void main(String[] args) throws Exception {
        //TempSensor sensor = new TempSensor();

        DirectProvider dp = new DirectProvider();
        Topology topology = dp.newTopology();

        String url = "tcp://localhost:1883";
        MqttStreams mqtt = new MqttStreams(topology, url, null);
        TStream<String> rcvd = mqtt.subscribe("dev/tempSensor", 0);
        TStream<Double> streamDouble = rcvd.map(s -> Double.valueOf(s));
        TStream<Double> streamDoubleFilter = streamDouble.filter(aDouble -> aDouble >= 20);

        streamDoubleFilter.print();

        /*
        TStream<Double> tempReadings = topology.poll(sensor, 1, TimeUnit.MILLISECONDS);
        TStream<Double> filteredReadings = tempReadings.filter(reading -> reading < 30 && reading > 0);
        filteredReadings.print();
        */

        dp.submit(topology);
    }

}
