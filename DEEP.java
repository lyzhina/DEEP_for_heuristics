package packing;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Math.*;

public class DEEP {
    private File filename;

    public DEEP(File filename) {
        this.filename = filename;
    }


    public void createSolution(String heuristics) {
        Combinations[] comb = new Combinations[32];
        int c = 0;
        char heuristic = heuristics.charAt(c);
        switch (heuristic) {
            case 'A':
                Heuristics heur0 = new Heuristics();
                heur0.heuristic(comb[0].recombination_gamma, comb[0].recombination_strategy, comb[0].gamma_init, filename);
                break;

            case 'B':
                Heuristics heur1 = new Heuristics();
                heur1.heuristic(comb[1].recombination_gamma, comb[1].recombination_strategy, comb[1].gamma_init, filename);
                break;
        }

        c++;
        if (c == heuristics.length())
            c = 0;

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
