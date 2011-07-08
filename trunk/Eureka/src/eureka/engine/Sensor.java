package eureka.engine;

import javax.persistence.Entity;
import eureka.base.Clause;
import eureka.base.WorkingMemory;
import javax.persistence.Column;

@Entity
public class Sensor extends Clause {

    private String label;
    @Column(name="_value")
    private String value;

    protected Sensor() {
        //para o hibernate
    }

    public Sensor(String label) {
        this.label = label;
        this.value = null;
    }

    public void update() {
    }

    public String getLabel() {
        return this.label;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public Boolean check(WorkingMemory wm) {
        // TODO Auto-generated method stub
        return null;
    }
}
