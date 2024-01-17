package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestController {
    @GetMapping("/")
    public String getMethodName() {
        return "MILAY";
    }
        
}
