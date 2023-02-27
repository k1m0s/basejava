package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if(size > STORAGE_LIMIT) {
            System.out.println("Storage overflow.");
        } else if(getIndex(r.getUuid()) != -1) {
            System.out.println("Resume this " + r.getUuid() + " exists.");
        } else {
            storage[size] = r;
            size++;
            System.out.println("Resume add in database.");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid){
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}