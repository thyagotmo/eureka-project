package eureka.util;

import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DBGenerate {

    public static void main(String[] args) {
        SchemaExport shemaExport = new SchemaExport(HibernateUtil.getConfiguration());
        shemaExport.drop(true, true);
        shemaExport.create(true, true);

    }
}
