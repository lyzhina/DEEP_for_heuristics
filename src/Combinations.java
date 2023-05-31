import java.io.*;
import java.util.ArrayList;

public class Combinations {
    public String recombination_gamma;
    public String recombination_strategy;
    public String gamma_init;

    public Combinations(String recombination_gamma, String recombination_strategy, String gamma_init) {
        this.recombination_gamma = recombination_gamma;
        this.recombination_strategy = recombination_strategy;
        this.gamma_init = gamma_init;
    }
    public Combinations() {
    }
    public static Combinations[]  comb_set() throws IOException {
        File file = new File("comb_parametrs.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String header1 = "[recombination_gamma]";
        String header2 = "[recombination_strategy]";
        String header3 = "[gamma_init]";

        ArrayList<String> recombination_gammas = new ArrayList<>();
        ArrayList<String> recombination_strategies = new ArrayList<>();
        ArrayList<String> gamma_inits = new ArrayList<>();


        String line = reader.readLine();
        if (line.equals(header1)) {
            while (!(line = reader.readLine()).equals(header2)) {
                recombination_gammas.add(line);
            }
        }
        while (!(line = reader.readLine()).equals(header3)) {
            recombination_strategies.add(line);
        }

        while ((line = reader.readLine()) != null) {
            gamma_inits.add(line);
        }

        int i;
        Combinations[] comb = new Combinations[recombination_gammas.size() * recombination_strategies.size() * gamma_inits.size()];

        for (i = 0; i < comb.length; i++) {
            comb[i] = new Combinations("0", "0", "0");
        }

        i = 0;
        for (int i1 = 0; i1 < recombination_strategies.size(); i1++) {
            for (int i2 = 0; i2 < gamma_inits.size(); i2++) {
                for (int i3 = 0; i3 < recombination_gammas.size(); i3++) {
                    comb[i].recombination_strategy = recombination_strategies.get(i1);
                    comb[i].gamma_init = gamma_inits.get(i2);
                    comb[i].recombination_gamma = recombination_gammas.get(i3);
                    i++;
                }
            }
        }

        for (i = 0; i < comb.length; i++) {
            System.out.print(comb[i].recombination_strategy + " ");
            System.out.print(comb[i].gamma_init + " ");
            System.out.print(comb[i].recombination_gamma + " ");
            System.out.print("\n");
        }

        return comb;
    }

}

