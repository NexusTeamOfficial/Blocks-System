package com.eaglehoster.dragdrop;

import android.os.Environment;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * StorageUtils - Utility class for saving and loading block data in a secure format.
 * 
 * - Data is stored in Base64-encoded JSON format for added security.
 * - File is stored in the internal storage at `.blacklogic/blocks_data.json`.
 * - Uses Gson for JSON serialization and deserialization.
 * - Ensures complete reading of the file to prevent corruption.
 * - Includes JSON validation before parsing to avoid errors.
 *
 * @author NexusTeam & SmartIndiaGaming
 * @date 26-02-2025
 * @license Copyright-free project
 */
public class StorageUtils {
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/blocks_data.json";
    private static final Gson gson = new Gson();

    /**
     * Saves the given data as a Base64-encoded JSON file.
     * 
     * @param data The data to save, stored as a Map.
     */
    public static void saveData(Map<String, Object> data) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure parent directories exist

            // Convert data to JSON and encode with Base64
            String json = gson.toJson(data);
            String encodedData = Base64.encodeToString(json.getBytes(), Base64.DEFAULT);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(encodedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and decodes block data from the Base64-encoded JSON file.
     * 
     * @return The decoded Map containing block data.
     */
    public static Map<String, Object> loadData() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new HashMap<>();

            // Read the entire file content
            StringBuilder encodedData = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    encodedData.append(line);
                }
            }

            // Decode Base64 string to JSON format
            String json = new String(Base64.decode(encodedData.toString(), Base64.DEFAULT));

            // Validate JSON before parsing
            if (!isValidJson(json)) {
                throw new IOException("Invalid JSON data detected!");
            }

            // Parse JSON into a Map
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            return gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * Validates whether a given string is a valid JSON format.
     * 
     * @param json The JSON string to validate.
     * @return True if valid, false otherwise.
     */
    private static boolean isValidJson(String json) {
        try {
            new Gson().fromJson(json, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
