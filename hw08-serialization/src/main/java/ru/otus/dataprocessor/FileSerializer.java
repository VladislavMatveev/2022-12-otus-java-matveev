package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        var gson = new Gson();
        try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(gson.toJson(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
