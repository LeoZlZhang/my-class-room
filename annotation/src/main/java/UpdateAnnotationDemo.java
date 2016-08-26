import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;

public class UpdateAnnotationDemo {

    @AutoWired(value = true, isMandatory = true, allowUpdate = true)
    private String name;

    @Test
    public void executable() throws Exception {
        UpdateAnnotationDemo showAnnotationDemo = new UpdateAnnotationDemo();
        Field field = UpdateAnnotationDemo.class.getDeclaredField("name");
        AutoWired autoWired = field.getAnnotation(AutoWired.class);
        System.out.println(showAnnotationValue(autoWired, "value"));
        System.out.println(showAnnotationValue(autoWired, "isMandatory"));
        System.out.println(showAnnotationValue(autoWired, "allowUpdate"));
        assert (Boolean)showAnnotationValue(autoWired, "value") == true;

        changeAnnotationValue(autoWired, "value", false);

        System.out.println(showAnnotationValue(autoWired, "value"));
        assert (Boolean)showAnnotationValue(autoWired, "value") == false;

    }




    public static Object showAnnotationValue(Annotation annotation, String key) throws NoSuchFieldException, IllegalAccessException {
        Object handler = Proxy.getInvocationHandler(annotation);
        Field f = handler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) f.get(handler);
        return memberValues.get(key);
    }


    public static Object changeAnnotationValue(Annotation annotation, String key, Object newValue) throws Exception {
        Object handler = Proxy.getInvocationHandler(annotation);
        Field f = handler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) f.get(handler);
        Object oldValue = memberValues.get(key);
        if (oldValue == null || oldValue.getClass() != newValue.getClass()) {
            throw new IllegalArgumentException();
        }
        memberValues.put(key, newValue);
        return oldValue;
    }
}
