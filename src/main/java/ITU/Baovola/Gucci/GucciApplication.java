package ITU.Baovola.Gucci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ITU.Baovola.Gucci.Services")
public class GucciApplication {
	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext= SpringApplication.run(GucciApplication.class, args);
		displayAllBeans();
	}
	public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

}
