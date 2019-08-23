package ru.seflaks.basejava.storage;

import ru.seflaks.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Переполнение массива!");
        } else if (searchIndex(resume.getUuid()) != -1) {
            System.out.println("Резюме уже есть в базе");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме с uuid = " + resume.getUuid() + " не существует!");
        } else storage[index] = resume;
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с uuid = " + uuid + " не существует!");
        } else {
            if (index != size - 1) {
                storage[index] = storage[size - 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
