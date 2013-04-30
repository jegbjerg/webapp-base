package dk.freecode.web.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Type;
import org.hibernate.tool.hbm2ddl.Target;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchemaController {

    @Inject
    private LocalSessionFactoryBuilder localSessionFactoryBuilder;

    @RequestMapping("/admin/schema")
    public void exportSchema(final Model model) throws IOException {

        final SchemaExport export = new SchemaExport(localSessionFactoryBuilder);
        final File f = Files.createTempFile(null, null).toFile();
        export.setOutputFile(f.getCanonicalPath());
        export.execute(Target.SCRIPT, Type.CREATE);

        model.addAttribute("ddl", FileUtils.readFileToString(f));
        model.addAttribute("file", f.getCanonicalPath());
    }
}
