//package packing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.*;

public class DEEP {
    private static File filename;

    public DEEP(File filename) {
        this.filename = filename;
    }
    public static double x[] = new double[64];

    public static void parsed_answer(File log_file, int task_size){
        try(Scanner sc = new Scanner(log_file)) {
            int i;
            for(i = 0; i < task_size; i++){
                x[i] = sc.nextDouble();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void createSolution(String heuristics) throws IOException {
        Combinations[] comb = Combinations.comb_set();
        int c = 0;
        char heuristic = heuristics.charAt(c);
        switch (heuristic) {
            case 'A':
                Heuristics heur0 = new Heuristics();
                heur0.make_heuristic(comb[0].recombination_gamma, comb[0].recombination_strategy, comb[0].gamma_init, filename);
                heur0.run_heuristic(filename);
                break;

            case 'B':
                Heuristics heur1 = new Heuristics();
                heur1.make_heuristic(comb[1].recombination_gamma, comb[1].recombination_strategy, comb[1].gamma_init, filename);
                heur1.run_heuristic(filename);
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

    public static void main(String[] args) {
        try {
            File curr_file = new File("deep_rastr.ini");
            DEEP solution = new DEEP(curr_file);
            solution.createSolution("AB");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parsed_answer(new File("deep_rastr.ini-deep-output"), 3);
        System.out.println(Arrays.toString(x));
    }
}
