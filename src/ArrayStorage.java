import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index;

    void clear() {
        for (int i = 0; i < index; i++) {
            storage[i].setUuid(null);
        }
        index = 0;
    }

    void save(Resume resume) {
        storage[index] = resume;
        index++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                if (i != index - 1) {
                    storage[i] = storage[index - 1];
                }
                storage[index - 1] = null;
                index--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return index;
    }
}
