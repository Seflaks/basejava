package ru.seflaks.basejava.storage;

import ru.seflaks.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с uuid = " + uuid + " не существует!");
            return null;
        } else {
            return storage[index];
        }
    }

    public int size() {
        return size;
    }

    protected abstract int searchIndex(String uuid);

}
