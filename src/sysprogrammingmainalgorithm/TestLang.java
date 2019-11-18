package sysprogrammingmainalgorithm;

import JavaTeacherLib.LlkContext;
import JavaTeacherLib.MyLang;
import JavaTeacherLib.Node;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestLang extends MyLang {
    TestLang(String fileName, int llk1) {
        super(fileName, llk1);
    }


    public void removeFromGrammar() {
        if (this.getLanguarge().isEmpty()) return;
        ArrayList<Integer> accessible = new ArrayList<>();
        accessible.add(this.getLanguarge().getFirst().getRoole()[0]);
        boolean found = true;
        while (found) {
            found = false;

            for (Node tmp : this.getLanguarge()) {
                int[] rule = tmp.getRoole();

                if (accessible.contains(rule[0])) {
                    for (int i = 1; i < rule.length; ++i) {
                        if (rule[i] < 0) {
                            if (!accessible.contains(rule[i])) {
                                accessible.add(rule[i]);
                                found = true;
                            }
                        }
                    }
                }
            }
        }
        int[] nonTerminals = this.getNonTerminals();
        if (nonTerminals.length == accessible.size()) {
            System.out.println("В граматиці недосяжних нетерміналів немає");
        } else {
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < nonTerminals.length; ++i) {
                if (!accessible.contains(nonTerminals[i])) {
                    res.add(nonTerminals[i]);
                }
            }
            System.out.println("Недосяжні нетермінали:");
            for (Integer t : res) {
                System.out.println(this.getLexemaText(t));
            }
            LinkedList<Node> languarge = this.getLanguarge();
            for (Integer t : res) {
                ArrayList<Integer> positions = new ArrayList<>();
                for (int i = 0; i < languarge.size(); ++i) {
                    if (languarge.get(i).getRoole()[0] == t) {
                        positions.add(i);
                    }
                }
                for (Integer pos : positions) {
                    languarge.remove(languarge.get(pos));
                }
            }
            System.out.println("Граматика без правил які починаються з недосяжних нетерміналів");
            this.printGramma();
        }
    }

    public void followSearchAndSet() {
        PrintStream stdout = System.out;
        try (PrintStream stream = new PrintStream("tmp")) {
            System.setOut(stream);
            LlkContext[] followContext = this.firstK();
            System.setOut(stdout);
            this.setFirstK(followContext);
            this.printFirstkContext();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
