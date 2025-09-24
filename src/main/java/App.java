import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld hwBean1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld hwBean2 =
                (HelloWorld) applicationContext.getBean("helloworld");

        Cat cat1 =
                (Cat) applicationContext.getBean("cat");
        Cat cat2 =
                (Cat) applicationContext.getBean("cat");

        System.out.println(hwBean1 == hwBean2);
        System.out.println(cat1 == cat2);

        System.out.println(hwBean1.getMessage());
    }
}