package com.example.projectcss3211;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONFood {
    private static Context context;
    ArrayList<String> ingredientTags = new ArrayList<String>();
    ArrayList<String> cuisineTags = new ArrayList<String>();
    public static int dishes = 27638;

    public JSONFood() {
    }

}

