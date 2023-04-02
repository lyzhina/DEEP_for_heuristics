//package packing;

import java.io.*;


import org.ini4j.Ini;

public class Heuristics {
    public void make_heuristic(String recombination_gamma, String recombination_strategy, String gamma_init, File ini_file, String section){
        try {
            Ini ini = new Ini(ini_file);
            ini.put(section, "recombination_gamma", recombination_gamma);
            ini.put(section, "recombination_strategy", recombination_strategy);
            ini.put(section, "gamma_init", gamma_init);
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void run_heuristic(File ini_file, String section){
        try {
            String file_name = ini_file.getName();
            // TODO сделать путь параметром
            Process p = Runtime.getRuntime().exec(String.format("/home/maria_lyzhina/bin/deepmethod --default-name=/home/maria_lyzhina/hd/%s --settings-group=%s --settings-file=/home/maria_lyzhina/hd/%s", file_name,section,file_name));
            // TODO перенаправить ввод вывод stdout
            int exitCode = p.waitFor();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}