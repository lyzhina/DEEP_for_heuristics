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
    public String run_heuristic(File ini_file, String section){
        double fit = 100;
        String part = "100.000000000000 ";
        try
        {
            String file_name = ini_file.getName();

            Process p = Runtime.getRuntime().exec(String.format("deepmethod --default-name=%s --settings-group=%s --settings-file=%s", file_name,section,file_name));


            String line ;
            String log_line = "wtime:-3.313857e+01 tau:14 freeze:0 score:100.000000000000 ";
            String log_line1 = "wtime:-3.313857e+01 tau:14 freeze:0 score:100.000000000000 ";
            final BufferedReader ir = new BufferedReader ( new InputStreamReader ( p.getInputStream () ) ) ;
            while ( null != (line = ir.readLine ()) )
            {

                log_line = line;
            }

            String part1[] = log_line.split("cost:");
            System.out.println(part1[1]);

            p.waitFor () ;

            part = part1[1];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return part;
    }

}