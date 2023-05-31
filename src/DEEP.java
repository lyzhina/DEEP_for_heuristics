//package packing;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.*;

public class DEEP {
    private static File filename;

    public DEEP(File filename) {
        this.filename = filename;
    }


    public static String createSolution(String heuristics) throws IOException {
        String fit = " ";

        int c = 0;

        while (c < heuristics.length()) {
            char heuristic = heuristics.charAt(c);
            switch (heuristic) {
                case 'A':

                    Heuristics heur1 = new Heuristics();
                    String section1 = "default_settings1";
                    fit = heur1.run_heuristic(filename, section1);

                    break;

                case 'B':
                    Heuristics heur2 = new Heuristics();
                    String section2 = "default_settings2";

                   fit = heur2.run_heuristic(filename, section2);
                    break;

                case 'C':
                    Heuristics heur3 = new Heuristics();
                    String section3 = "default_settings3";

                    fit = heur3.run_heuristic(filename, section3);
                    break;

                case 'D':
                    Heuristics heur4 = new Heuristics();
                    String section4 = "default_settings4";

                    fit = heur4.run_heuristic(filename, section4);
                    break;

                case 'E':
                    Heuristics heur5 = new Heuristics();
                    String section5 = "default_settings5";

                    fit = heur5.run_heuristic(filename, section5);
                    break;

                case 'F':
                    Heuristics heur6 = new Heuristics();
                    String section6 = "default_settings6";

                    fit = heur6.run_heuristic(filename, section6);
                    break;

                case 'G':
                    Heuristics heur7 = new Heuristics();
                    String section7 = "default_settings7";

                    fit = heur7.run_heuristic(filename, section7);
                    break;

                case 'J':
                    Heuristics heur8 = new Heuristics();
                    String section8 = "default_settings8";

                    fit = heur8.run_heuristic(filename, section8);
                    break;

                case 'H':
                    Heuristics heur9 = new Heuristics();
                    String section9 = "default_settings9";

                    fit = heur9.run_heuristic(filename, section9);
                    break;

                case 'I':
                    Heuristics heur10 = new Heuristics();
                    String section10 = "default_settings10";

                    fit = heur10.run_heuristic(filename, section10);
                    break;

                case 'K':
                    Heuristics heur11 = new Heuristics();
                    String section11 = "default_settings11";

                    fit = heur11.run_heuristic(filename, section11);
                    break;

                case 'L':
                    Heuristics heur12 = new Heuristics();
                    String section12 = "default_settings12";

                    fit = heur12.run_heuristic(filename, section12);
                    break;

                case 'M':
                    Heuristics heur13 = new Heuristics();
                    String section13 = "default_settings13";

                    fit = heur13.run_heuristic(filename, section13);
                    break;

                case 'N':
                    Heuristics heur14 = new Heuristics();
                    String section14 = "default_settings14";

                    fit = heur14.run_heuristic(filename, section14);
                    break;

                case 'O':
                    Heuristics heur15 = new Heuristics();
                    String section15 = "default_settings15";

                    fit = heur15.run_heuristic(filename, section15);
                    break;

                case 'P':
                    Heuristics heur16 = new Heuristics();
                    String section16 = "default_settings16";

                    fit = heur16.run_heuristic(filename, section16);
                    break;

                case 'Q':
                    Heuristics heur17 = new Heuristics();
                    String section17 = "default_settings17";

                    fit = heur17.run_heuristic(filename, section17);
                    break;

                case 'R':
                    Heuristics heur18 = new Heuristics();
                    String section18 = "default_settings18";

                    fit = heur18.run_heuristic(filename, section18);
                    break;

                case 'S':
                    Heuristics heur19 = new Heuristics();
                    String section19 = "default_settings19";

                    fit = heur19.run_heuristic(filename, section19);
                    break;

                case 'T':
                    Heuristics heur20 = new Heuristics();
                    String section20 = "default_settings20";

                    fit = heur20.run_heuristic(filename, section20);
                    break;

                case 'U':
                    Heuristics heur21 = new Heuristics();
                    String section21 = "default_settings21";

                    fit = heur21.run_heuristic(filename, section21);
                    break;

                case 'V':
                    Heuristics heur22 = new Heuristics();
                    String section22 = "default_settings22";

                    fit = heur22.run_heuristic(filename, section22);
                    break;

                case 'W':
                    Heuristics heur23 = new Heuristics();
                    String section23 = "default_settings23";

                    fit = heur23.run_heuristic(filename, section23);
                    break;

                case 'X':
                    Heuristics heur24 = new Heuristics();
                    String section24 = "default_settings24";

                    fit = heur24.run_heuristic(filename, section24);
                    break;

                case 'Y':
                    Heuristics heur25 = new Heuristics();
                    String section25 = "default_settings25";

                    fit = heur25.run_heuristic(filename, section25);
                    break;

                case 'Z':
                    Heuristics heur26 = new Heuristics();
                    String section26 = "default_settings26";

                    fit = heur26.run_heuristic(filename, section26);
                    break;

                case 'a':
                    Heuristics heur27 = new Heuristics();
                    String section27 = "default_settings27";

                    fit = heur27.run_heuristic(filename, section27);
                    break;

                case 'b':
                    Heuristics heur28 = new Heuristics();
                    String section28 = "default_settings28";

                    fit = heur28.run_heuristic(filename, section28);
                    break;

                case 'c':
                    Heuristics heur29 = new Heuristics();
                    String section29 = "default_settings29";

                    fit = heur29.run_heuristic(filename, section29);
                    break;

                case 'd':
                    Heuristics heur30 = new Heuristics();
                    String section30 = "default_settings30";

                    fit = heur30.run_heuristic(filename, section30);
                    break;

                case 'e':
                    Heuristics heur31 = new Heuristics();
                    String section31 = "default_settings31";

                    fit = heur31.run_heuristic(filename, section31);
                    break;

                case 'f':
                    Heuristics heur32 = new Heuristics();
                    String section32 = "default_settings32";

                    fit = heur32.run_heuristic(filename, section32);
                    break;
            }
            c++;

        }
        File chkfile = new File("deep_clust.ini.chk");
        chkfile.delete();

        return fit;
    }


    public static double parse_fitness(File log_file){
        double fit = 0;
        try(Scanner sc = new Scanner(log_file)) {
            String log_line;
            log_line = sc.nextLine();
            System.out.println(log_line);
            String part1[] = log_line.split("score:");
            String part2[] = part1[1].split(" ");
            fit = Double.parseDouble(part2[0]);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return fit;
    }


}
