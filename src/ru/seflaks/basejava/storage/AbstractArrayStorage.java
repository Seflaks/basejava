package ru.seflaks.basejava.storage;

import ru.seflaks.basejava.exception.ExistStorageException;
import ru.seflaks.basejava.exception.NotExistStorageException;
import ru.seflaks.basejava.exception.StorageException;
import ru.seflaks.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
//            System.out.println("Резюме с uuid = " + resume.getUuid() + " не существует!");
            throw new NotExistStorageException(resume.getUuid());
        } else storage[index] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
//            System.out.println("Переполнение массива!");
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
//                System.out.println("Резюме уже есть в базе");
                throw new ExistStorageException(resume.getUuid());
            } else {

                insertElement(resume, index);
                size++;
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
//            System.out.println("Резюме с uuid = " + uuid + " не существует!");
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
//            System.out.println("Резюме с uuid = " + uuid + " не существует!");
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);

}
