package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, int index) {
        index = -index - 1;
        if(index < size) {
            System.arraycopy(storage, index, storage,index + 1, size - index);
        }
        storage[index] = r;
    }

    @Override
    protected void doDelete(int index) {
        int numMuved = size - index - 1;
        if(numMuved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMuved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}