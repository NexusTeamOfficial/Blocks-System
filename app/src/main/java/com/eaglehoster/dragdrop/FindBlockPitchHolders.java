package com.eaglehoster.dragdrop;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class FindBlockPitchHolders {
    public static List<String> extractValues(View block) {
        List<String> inputValues = new ArrayList<>();

        if (block instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) block;
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                if (child instanceof EditText) {
                    inputValues.add(((EditText) child).getText().toString()); // ✅ Extract values
                }
            }
        }
        return inputValues;
    }

    public static String formatCode(String code, List<String> values) {
        try {
            if (!values.isEmpty() && code.contains("%1$s")) {
                int placeholderCount = code.split("%\\d+\\$s").length - 1;
                while (values.size() < placeholderCount) {
                    values.add(""); // Fill missing values
                }
                return String.format(code, values.toArray());
            }
        } catch (Exception e) {
            Log.e("FindBlockPitchHolders", "Error formatting placeholders: " + e.getMessage());
        }
        return code;
    }
}