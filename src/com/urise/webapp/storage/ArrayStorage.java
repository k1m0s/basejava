package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(String uuid) {
        int index = getIndex(uuid);
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
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