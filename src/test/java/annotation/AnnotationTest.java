package annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class AnnotationTest {

    public static void main(String[] args) throws NoSuchMethodException {

        //AnnotationTest.class.isAnnotationPresent()

        Method method = AnnotationTest.class.getMethod("login") ;
        if(method.isAnnotationPresent(Login.class)) {
            Login  as = method.getAnnotation(Login.class);
            System.out.println(as.password() +"    " + as.username());
            //select FLOOR(MOD(crc32(lower('YJ4558271498384769969')),1024)/32) db_num ,
            // MOD(MOD(crc32(lower('YJ4558271498384769969')),1024),32) tab_num;
        }
    }

    @Login
    @Deprecated
    public void login(){

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Login{

    String username() default "hah" ;
    String password() default "hah";
}