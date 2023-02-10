import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if(size < storage.length) {
            storage[size] = r;
            size++;
            System.out.println("Resume save in database.");
        } else {
            System.out.println("Resume database is full.");
        }
    }

    Resume get(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                for(int j = i; j < size - 1; j++) {
                    storage[i] = storage[j + 1];
                }
                storage[size - 1] = null;
                size--;
                break;
            }
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
}
