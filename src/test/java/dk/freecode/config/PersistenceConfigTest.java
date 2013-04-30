package dk.freecode.config;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.inject.Inject;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Type;
import org.hibernate.tool.hbm2ddl.Target;
import org.junit.After;
import org.junit.Test;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import dk.freecode.test.IntegrationTest;

public class PersistenceConfigTest extends IntegrationTest {

    private static final String OUTPUT_FILE_NAME = "tables.sql";

    @Inject
    private LocalSessionFactoryBuilder localSessionFactoryBuilder;

    @Test
    public void exportSchemaShouldCreateSqlFile() {
        final SchemaExport export = new SchemaExport(localSessionFactoryBuilder);
        export.setOutputFile(OUTPUT_FILE_NAME);
        export.execute(Target.SCRIPT, Type.CREATE);

        final File f = new File(OUTPUT_FILE_NAME);
        assertTrue(f.exists());
    }

    @After
    public void removeFile() {
        final File f = new File(OUTPUT_FILE_NAME);
        f.delete();
    }
}
