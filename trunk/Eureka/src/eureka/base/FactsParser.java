package eureka.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FactsParser {

    private static final String END_OF_STATEMENT = ";";

    /**
     * Carrega os fatos contidos no arquivo de factsPath na Working Memory
     */
    public static List<RuleVariable> loadFacts(String factsPath) {
        List<RuleVariable> variables = new ArrayList<RuleVariable>();
        if (factsPath != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(factsPath)));
                String allFacts = "";
                while (reader.ready()) {

                    String line = reader.readLine();
                    allFacts += line;
                }
                String[] facts = allFacts.split(END_OF_STATEMENT);
                for (String fact : facts) {
                    BooleanClause c = new BooleanClause(fact);
                    if (c.getOperator().equals(EOperator.RECEIVE)) {
                        variables.add(new RuleVariable(c.getVariableLabel(),
                                c.getTargetValue()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return variables;
    }
}
