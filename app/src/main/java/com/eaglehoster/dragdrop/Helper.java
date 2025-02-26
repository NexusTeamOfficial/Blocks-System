/**
 * Project: Helper  
 *  
 * Description:  
 * This class is responsible for storing and managing block-related data efficiently.  
 *  
 * Features:  
 * - Generic type-safe storage (supports multiple data types)  
 * - Auto-removal of null values  
 * - Check if a key exists before retrieving data  
 * - Clear all stored data  
 * - Retrieve all stored keys  
 * - Thread-safe operations  
 *  
 * @author NexusTeam & SmartIndiaGaming  
 * @created 26-02-2025  
 * @license Open Source - Free to Modify & Distribute  
 * @origin Made in India 🇮🇳 | Empowering Developers Globally  
 * @note Designed to provide a flexible key-value storage solution.  
 */

package com.eaglehoster.dragdrop;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class Helper<T> {  
    private final SparseArray<T> keyStore = new SparseArray<>();
    
    /**
     * Stores a value with a specific key.
     * If the value is null, the key will be removed to save memory.
     * 
     * @param key Unique key identifier.
     * @param value The value to store.
     */
    public synchronized void setKey(int key, T value) {
        if (value != null) {
            keyStore.put(key, value);
        } else {
            keyStore.remove(key);
        }
    }

    /**
     * Retrieves a value based on the key.
     * 
     * @param key Unique key identifier.
     * @return Stored value or null if the key does not exist.
     */
    public synchronized T getKey(int key) {
        return keyStore.get(key);
    }

    /**
     * Checks if a key exists in storage.
     * 
     * @param key Unique key identifier.
     * @return True if the key exists, otherwise false.
     */
    public synchronized boolean containsKey(int key) {
        return keyStore.get(key) != null;
    }

    /**
     * Retrieves all stored keys.
     * 
     * @return List of keys currently in storage.
     */
    public synchronized List<Integer> getAllKeys() {
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < keyStore.size(); i++) {
            keys.add(keyStore.keyAt(i));
        }
        return keys;
    }

    /**
     * Clears all stored values.
     */
    public synchronized void clearAll() {
        keyStore.clear();
    }
}