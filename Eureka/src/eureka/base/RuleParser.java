package eureka.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eureka.engine.Sensor;
import eureka.view.Trace;

public class RuleParser {
	private static final String END_OF_STATEMENT = ";";
	private static final String LABEL_RULE_DIVISOR = ":";
	private static final String THEN = "ENTAO";
	private static final String IF = "SE";
	private static final String AND = "E";
	private static final String OR = "OU";
	private static final String SENSOR = "SENSOR";
	private static final String EFFECTOR = "ATUADOR";
	
	/**Carrega as regras na base de regras baseado na definio de um arquivo de texto*/
	public static List<Rule> loadRules(String rulePath) {
		List<Rule> rules = new ArrayList<Rule>();
		if(rulePath != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(rulePath)));
				String allRules = "";
				while(reader.ready()) {
					
					String line = reader.readLine();
					allRules += line;
				}
				
				String[] ruleDescriptions = allRules.split(END_OF_STATEMENT);
				for(String line : ruleDescriptions) {
					
					line = line.trim();
					if(!line.isEmpty()) {
						String[] ruleString = line.split(LABEL_RULE_DIVISOR);
						String label = ruleString[0].trim();
						String body = ruleString[1].trim();
						
						List<String> orRules = breakOrRules(label, body);
						
						for(String s : orRules) {
							String rulelabel = s.split(LABEL_RULE_DIVISOR)[0].trim();
							String ruleBody = s.split(LABEL_RULE_DIVISOR)[1].trim();
							String predicate = ruleBody.split("\\s" + THEN + "\\s")[0].trim();
							String consequent = ruleBody.split("\\s" + THEN + "\\s")[1].trim();
							predicate = predicate.split(IF + "\\s")[1];
							
							String[] clauses = predicate.split("\\s" + AND + "\\s");
							
							Clause[] predicateClauses = new Clause[clauses.length];
		
							for(int i = 0; i < clauses.length; i++) {
								String clause = clauses[i].trim();
								if(clause.contains(SENSOR + "(")) {
									predicateClauses[i] = new Sensor(clauses[i].trim());
								}else {
									predicateClauses[i] = new BooleanClause(clauses[i].trim());
								}
							}
							BooleanClause consequentClause = new BooleanClause(consequent.trim());
							if(consequentClause.getOperator().equals(EOperator.RECEIVE)) {
								Rule rule = new Rule(rulelabel, predicateClauses, consequentClause);
								rules.add(rule);
							}else {
								Trace.appendMessage("Erro", " cl�usula consequente n�o � de atribui��o: " + consequentClause);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rules;
	}
	
	private static List<String> breakOrRules(String label, String rule) {

		List<String> orRules = new ArrayList<String>();
        String[] separacaoENTAO = rule.split("\\s" + THEN + "\\s");

        String[] separacaoOU = separacaoENTAO[0].split("\\s" + OR + "\\s");
        

        for(int i = 0; i< separacaoOU.length;i++){
           String newRule = label + i + " " + LABEL_RULE_DIVISOR + " ";
           if(i > 0) {
				newRule += IF + " ";
		   }
           newRule += separacaoOU[i] + " " + THEN + " " + separacaoENTAO[separacaoENTAO.length-1];
           orRules.add(newRule);
        }
        return orRules;
	}
}
