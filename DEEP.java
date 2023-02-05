package packing;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class DEEP {
    private File filename;

    public DEEP(File filename) {
        this.filename = filename;
    }


    public void createSolution(String heuristics) throws IOException {
        Combinations[] comb = new Combinations[32];
        int c = 0;
        char heuristic = heuristics.charAt(c);
        switch (heuristic) {
            case 'A':
                File ini_file0 = new File("ini_file0.ini");
                copyFileUsingStream(filename, ini_file0);
                Heuristics heur0 = new Heuristics();
                heur0.make_heuristic(comb[0].recombination_gamma, comb[0].recombination_strategy, comb[0].gamma_init, ini_file0);
                heur0.run_heuristic(ini_file0);
                break;

            case 'B':
                File ini_file1 = new File("ini_file1.ini");
                copyFileUsingStream(filename, ini_file1);
                Heuristics heur1 = new Heuristics();
                heur1.make_heuristic(comb[1].recombination_gamma, comb[1].recombination_strategy, comb[1].gamma_init, ini_file1);
                heur1.run_heuristic(ini_file1);
                break;
        }

        c++;
        if (c == heuristics.length())
            c = 0;

    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private static double rastrign_func(ArrayList<Double> xx) {
        double rastrign = 0;
        double sum = 0;

        for (int i = 0; i < xx.size(); i++) {
            sum += pow(xx.get(i), 2) - 10 * cos(2 * 3.14 * xx.get(i));
        }
        return rastrign = xx.size() + 10 * sum;
    }

    private static double calculateFitness(ArrayList<Double> xx) {
        double fitness = rastrign_func(xx);
        return  fitness;
    }
}
