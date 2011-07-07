package eureka.util;



import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DBGenerate {

    public static void main(String[] args) {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure();

        SchemaExport shemaExport = new SchemaExport(cfg);
        shemaExport.create(true, true);

    }
}
