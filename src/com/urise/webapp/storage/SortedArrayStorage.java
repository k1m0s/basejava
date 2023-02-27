package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if(size > STORAGE_LIMIT) {
            System.out.println("Storage overflow.");
        } else if(index != -1) {
            System.out.println("Resume this " + r.getUuid() + " exists.");
        } else {
            index = 0;
            System.arraycopy(storage, index, storage,index + 1, size++ - index);
            storage[index] = r;

        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("ERROR: Resume with " + uuid +  " not found.");
        } else {
            System.arraycopy(storage, index + 1, storage, index, --size - index);
            storage[size - 1] = null;
        }
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
