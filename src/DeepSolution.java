import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import initialsoln.InitialSoln;

public class DeepSolution extends InitialSoln{

    private String heuristicCombination;
    private double fitness;
    public double solution[] = new double[64];


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

    public void createSolution(String filename) {
        DEEP sol = new DEEP(new File(filename));
        try {
            sol.createSolution(heuristicCombination);
            fitness = sol.parse_fitness(new File("deep_rastr.ini.log"));
            solution = sol.getSolution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public int getObjectiveValue() {
//        return solution.size();
//    }

}
