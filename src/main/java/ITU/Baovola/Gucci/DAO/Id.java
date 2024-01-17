package ITU.Baovola.Gucci.DAO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    public String name() default "";
    public int idDim() default 4;
    public Generation idtype() default Generation.DEFAULT;
}
