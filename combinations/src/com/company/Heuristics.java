package com.company;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class Heuristics {
    public int fec;
    public void heuristic0(String recombination_gamma, String recombination_strategy, String gamma_init, File ini_file){
        try {
            Ini ini = new Ini(ini_file);
            ini.put("default_settings", "recombination_gamma", recombination_gamma);
            ini.put("default_settings", "recombination_strategy", recombination_strategy);
            ini.put("default_settings", "gamma_init", gamma_init);
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
