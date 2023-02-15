package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if(index == -1) {
            System.out.println("ERROR: Resume with " + resume.getUuid() +  " not found.");
            return;
        }
        storage[index] = resume;
    }

    public int searchIndex(String uuid){
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        if(size < storage.length) {
            storage[size] = r;
            size++;
            System.out.println("Resume add in database.");
        } else if(searchIndex(r.getUuid()) != -1){
            System.out.println("Resume this " + r.getUuid() + " exists.");
        } else {
            System.out.println("Resume database is full.");
        }
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
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
}