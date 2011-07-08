package eureka.base;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public abstract class Clause implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String variable;
    @Transient
    private Boolean truth;
    
    protected Clause() {
        //para o hibernate
    }

    public void setVariable(String variableLabel) {
        this.variable = variableLabel;
    }

    /**
     * Configura o valor de veracidade da clusula
     */
    public void setTruth(Boolean truth) {
        this.truth = truth;
    }

    /**
     * Retorna o nome da varivel referenciada na clusula
     */
    public String getVariableLabel() {
        return variable;
    }

    /**
     * Verifica se a clusula  veradeira checando os valores na Working Memory
     */
    public abstract void check(WorkingMemory wm);

    /**
     * Retorna <i>true</i> se a clusula  verdadeira
     */
    public Boolean getTruth() {
        return truth;
    }
}
