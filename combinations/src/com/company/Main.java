package com.company;

import org.ini4j.*;

import java.io.*;

public class Main {
    public String recombination_gamma;
    public String recombination_strategy;
    public String gamma_init;

    public Main(String recombination_gamma, String recombination_strategy, String gamma_init){
        this.recombination_gamma = recombination_gamma;
        this.recombination_strategy = recombination_strategy;
        this.gamma_init = gamma_init;
    }

    public static void main(String[] args) {
        File fileToParse = new File("C:\\Users\\maria\\Desktop\\deep_diplom\\deep_rastr.ini");

        String recombination_gammas [] = {"0.9", "0.3"};
        String recombination_strategies [] = {"de_3_bin", "de_3_exp", "de_3_exp_T", "de_3_bin_T", "de_3_bin_rand", "de_3_exp_rand", "de_3_bin_self", "de_3_exp_self"};
        String gamma_inits [] = {"0.9", "0.3"};

        int i;
        Main [] comb = new Main [recombination_gammas.length * recombination_strategies.length * gamma_inits.length];

        for (i = 0; i < comb.length; i++){
            comb[i] = new Main("0", "0", "0");
        }

        i = 0;
        for(int i1 = 0; i1 < recombination_strategies.length; i1 ++){
            for(int i2 = 0; i2 < gamma_inits.length; i2 ++){
                for(int i3 = 0; i3 < recombination_gammas.length; i3 ++){
                    comb[i].recombination_strategy = recombination_strategies[i1];
                    comb[i].gamma_init = gamma_inits[i2];
                    comb[i].recombination_gamma = recombination_gammas[i3];
                    i++;
                }
            }
        }

        for (i = 0; i < comb.length; i++){
            System.out.print(comb[i].recombination_strategy + " ");
            System.out.print(comb[i].gamma_init + " ");
            System.out.print(comb[i].recombination_gamma + " ");
            System.out.print("\n");
        }
        Heuristics heur0 = new Heuristics();
        heur0.heuristic0(comb[0].recombination_gamma, comb[0].recombination_strategy, comb[0].gamma_init, fileToParse);
    }

}
