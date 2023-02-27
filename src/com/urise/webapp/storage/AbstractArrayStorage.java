package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index == -1) {
            System.out.println("ERROR: Resume with " + resume.getUuid() +  " not found.");
            return;
        }
        storage[index] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
