package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {
    @GetMapping("/")
    public String getMethodName() {
        return "MILAY";
    }
        
}
