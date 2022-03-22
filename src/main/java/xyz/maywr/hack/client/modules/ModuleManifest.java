package xyz.maywr.hack.client.modules;

import org.lwjgl.input.Keyboard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleManifest {
    String name() default "";
    Module.Category category();
    String description() default "No Description";
    int key() default Keyboard.KEY_NONE;
    boolean persistent() default false;
    boolean listen() default true;
    int color() default -1;
}
