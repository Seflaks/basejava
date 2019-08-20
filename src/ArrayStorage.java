import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10_000];
    int size;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Переполнение массива!");
        } else if (searchIndex(resume.getUuid()) == -1) {
                    storage[size] = resume;
                    size++;
                } else System.out.println("Резюме уже есть в базе");
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме с uuid = " + uuid + " не существует!");
        return null;
    }

    void delete(String uuid) {
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
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index != -1) {
            storage[index].setUuid(resume.getUuid());
        } else System.out.println("Резюме с uuid = " + resume.getUuid() + " не существует!");
    }

    int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
