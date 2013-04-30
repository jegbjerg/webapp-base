package dk.freecode.web.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.flyway.core.Flyway;

@Controller
public class MigrationsController {

    @Inject
    public Flyway flyway;

    @RequestMapping("/admin/migrations")
    public void migrations(final Model model) {
        model.addAttribute("migrations", flyway.info().all());
    }
}
