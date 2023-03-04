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
    public static double x[] = {0, 0, 0};

    public static double parse_answer(File log_file){
        try(Scanner sc = new Scanner(log_file)) {
            String log_line;
            char tmp[] = new char[1024];
            log_line = sc.nextLine();
            System.out.println(log_line);
            String part1[] = log_line.split("]:");
            String part2[] = part1[1].split(" ");
            String part3[] = part1[2].split(" ");
            x[0] = Double.parseDouble(part2[0]);
            x[1] = Double.parseDouble(part3[0]);
            x[2] = Double.parseDouble(part1[3]);
            System.out.println(Arrays.toString(x));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public static void createSolution(String heuristics) throws IOException {
        Combinations[] comb = Combinations.comb_set();
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

    public static void main(String[] args) {
//        try {
//            File curr_file = new File("deep_rastr.ini");
//            DEEP solution = new DEEP(curr_file);
//            solution.createSolution("AB");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        parse_answer(new File("ini_file0.ini.log"));
    }
}
