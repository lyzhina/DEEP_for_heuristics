//package packing;

import java.io.*;


import org.ini4j.Ini;

public class Heuristics {
    public void make_heuristic(String recombination_gamma, String recombination_strategy, String gamma_init, File ini_file){
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
    public void run_heuristic(File ini_file){
        try {
            String file_name = ini_file.getName();
            // TODO сделать путь параметром
            Process p = Runtime.getRuntime().exec(String.format("/home/maria_lyzhina/bin/deepmethod --default-name=/home/maria_lyzhina/hyp_deep/%s", file_name));
            int exitCode = p.waitFor();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}