package eureka.environment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Effector {

    @Id
    @GeneratedValue
    private Long id;
    
    private String label;

    protected Effector() {
        //para o hibernate
    }

    public Effector(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public abstract void fire();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
