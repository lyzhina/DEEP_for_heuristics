import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import initialsoln.InitialSoln;

public class DeepSolution extends InitialSoln{

    private String heuristicCombination;
    private double fitness;
    public String solution;


    @Override
    public int fitter(InitialSoln arg0) {
        if (arg0.getFitness() >= fitness)
            return 1;
        else if (arg0.getFitness() < fitness)
            return -1;
        else
            return 0;
    }
    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public String getHeuCom() {
        return heuristicCombination;
    }

    @Override
    public Object getSoln() {
        return solution;
    }

    @Override
    public void setHeuCom(String arg0) {
        this.heuristicCombination = arg0;

    }

    @Override
    public String solnToString() {
        //TODO make solution to string
        return "deep string";
    }
    public void print() {
        System.out.println(solution);
    }


    public void createSolution(String filename) {
        DEEP sol = new DEEP(new File(filename));
        try {
            String fitline = sol.createSolution(heuristicCombination);
            String part2[] = fitline.split(" ");
            fitness = Double.parseDouble(part2[0]);
            String part3[] = fitline.split(" ");

            solution = fitline;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
