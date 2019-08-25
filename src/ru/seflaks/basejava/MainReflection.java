package ru.seflaks.basejava;

import ru.seflaks.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        //TODO : invoke resume.roString via reflection
        System.out.println(resume);
    }
}
