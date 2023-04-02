
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import distrgenalg.DistrGenAlg;

public class Runnable {

    public static void main(String[] args) {
        long seed = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);

        System.out.println("Constructive Hyper-Heuristics using EvoHyp for the One-Dimensional Bin-Packing Problem");
        System.out.println(
                "Do you wish to enter a Seed? (Number Only) (Default is System Current Time in Milliseconds)\n1 - Yes\n2 - No");
        int choice = sc.nextInt();

        while (choice < 1 || choice > 2) {
            System.out.println(
                    "\nUnavailable Option\nDo you wish to enter a Seed? (Number Only) (Default is System Current Time in Milliseconds)\n1 - Yes\n2 - No");
            choice = sc.nextInt();
        }

        if (choice == 1) {
            System.out.println("\nEnter Seed");
            seed = sc.nextLong();
        }


        DeepProblem bpp = new DeepProblem();
        bpp.setFilename("deep_rastr.ini");
        String heuristics = "AB";
        int cores = 4;

        DistrGenAlg dga = new DistrGenAlg(seed, heuristics, cores);
        dga.setParameters("Parameters.txt");
        dga.setProblem(bpp);
        dga.setAllowDuplicates(false);

        long start = System.currentTimeMillis();
        DeepSolution solution = (DeepSolution) dga.evolve();
        long end = System.currentTimeMillis();

        System.out.println("\nBest Solution");
        System.out.println("--------------");
        System.out.println("Seed: " + seed);
        System.out.println("Fitness: " + solution.getFitness());
        System.out.println("Time Taken: " + (end - start) + "ms");
        System.out.println("Heuristic combination: " + solution.getHeuCom());
       // System.out.println("Solution: ");
       // solution.print();
        ///System.out.println("Objective Value: " + solution.getObjectiveValue());


        System.out.println("\nPush Enter to Continue");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

}

