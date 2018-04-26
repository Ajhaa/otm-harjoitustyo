package pietari;

 

import java.io.File;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.chart.LineChart;

import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;

import javafx.stage.Stage;

 

public class Pietari extends Application {

    private Map<Integer, Double> kannatusVuosittain;

    private Map<String, Map<Integer, Double>> puolueidenKannatus;

 

    public Pietari() {

        this.kannatusVuosittain = new HashMap<>();

        this.puolueidenKannatus = new HashMap<>();

    }

    

    

    public static void main(String[] args) {

        launch(Pietari.class);

    }

 

    @Override

    public void start(Stage ikkuna) {

        

        NumberAxis xAkseli = new NumberAxis(1968, 2008, 4);

        NumberAxis yAkseli = new NumberAxis(0, 30, 5);

        List<Double> lisattavaKannatus = new ArrayList<>();

        List<Integer> lisattavaVuosi = new ArrayList<>();

        LineChart<Number, Number> viivakaavio = new LineChart<>(xAkseli, yAkseli);

        viivakaavio.setTitle("Puolueiden suhteellinen kannatus");

        

        try (Scanner tiedostonLukija = new Scanner(new File("puoluedata.tsv"))) {

                
            String rivi1 = tiedostonLukija.nextLine();

            String[] puolueVuodet = rivi1.split("\t");

            while (tiedostonLukija.hasNextLine()) {
                lisattavaKannatus = new ArrayList<>();
                lisattavaVuosi = new ArrayList<>();
                
                String kannatusluvut = tiedostonLukija.nextLine();
                System.out.println("Luvut " + kannatusluvut);
                String[] lukutaulukko = kannatusluvut.split("\t");

                for (int i = 1; i < lukutaulukko.length; i++) {

                    if (!lukutaulukko[i].equals("-")) {

                        lisattavaKannatus.add(Double.parseDouble(lukutaulukko[i]));

                    }

                }

                for(int i = 1; i < puolueVuodet.length; i++) {

                    lisattavaVuosi.add(Integer.parseInt(puolueVuodet[i]));

                }

 

                for (int i = 0; i < lukutaulukko.length - 1; i++) {

                    kannatusVuosittain.put(lisattavaVuosi.get(i), lisattavaKannatus.get(i));

                }

                puolueidenKannatus.put(lukutaulukko[0], kannatusVuosittain);

            }

        } catch (Exception e) {

            System.out.println("Virhe: " + e.getMessage());

        }
        
        System.out.println(puolueidenKannatus);

        

        puolueidenKannatus.keySet().stream().forEach(puolue -> {

            XYChart.Series data = new XYChart.Series();

            data.setName(puolue);

 

            puolueidenKannatus.get(puolue).entrySet().stream().forEach(pari -> {

            data.getData().add(new XYChart.Data(pari.getKey(), pari.getValue()));

            });

 

            viivakaavio.getData().add(data);

        });

        

        Scene nakyma = new Scene(viivakaavio, 640, 480);

        ikkuna.setScene(nakyma);

        ikkuna.show();

    }

 

}