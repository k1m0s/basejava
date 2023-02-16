package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final int STORAGE_LIMIT = 10000;
    private int size = 0;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

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

    public void save(Resume r) {
        if(size > STORAGE_LIMIT) {
            System.out.println("Resume database is full.");
        } else if(getIndex(r.getUuid()) != -1) {
            System.out.println("Resume this " + r.getUuid() + " exists.");
        } else {
            storage[size] = r;
            size++;
            System.out.println("Resume add in database.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid){
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}