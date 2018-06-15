package task_3;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestAnnotationAnalyzer {

    private Entity entity = new Entity();

    public void analyze() {

        Method[] methods = entity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + Arrays.toString(method.getDeclaredAnnotations()));
        }
    }
}



