package eureka.base;

/**
 * Enumerao que especifica os operadores que podem ser usados pelas clusulas 
 * nas regras
 */
public enum EOperator {

    EQUALS("="), NOT_EQUALS("!="), GREATHER_THAN(">"), LESS_THAN("<"),
    GREATER_OR_EQUAL(">="), LESS_OR_EQUAL("<="), RECEIVE("<-");
    
    private static EOperator[] operators = {EQUALS, NOT_EQUALS,
        GREATHER_THAN, LESS_THAN, GREATER_OR_EQUAL, LESS_OR_EQUAL, RECEIVE};
    private String symbol;

    private EOperator(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Retorna a string que representa o operador
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Retorna o operador que  representando pela string <i>symbol</i>
     */
    public static EOperator getOperator(String symbol) {
        for (EOperator cond : operators) {
            if (cond.getSymbol().equals(symbol)) {
                return cond;
            }
        }
        return null;
    }

    /**
     * Retorna o conjunto de todos os operadores permitidos
     */
    public static EOperator[] getOperators() {
        return operators;
    }
}
