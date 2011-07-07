package eureka.base;

/**Vari�vel presente em uma regra*/
public class RuleVariable {

    private String label;
    private String value;

    /**Uma variavel possui um nome e um valor*/
    public RuleVariable(String label) {
        this.label = label;
    }

    /**Uma variavel possui um nome e um valor*/
    public RuleVariable(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**Retorna o nome da vari�vel*/
    public String getLabel() {
        return this.label;
    }

    /**Atribui um valor � vari�vel*/
    public void setValue(String value) {
        this.value = value;
    }

    /**Retorna o valor da vari�vel*/
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return label + " = " + value;
    }
}
