package ru.otus.dataprocessor;

import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        var gson = new Gson();
        List<Measurement> result;

        try (var reader = new FileReader(getClass().getClassLoader().getResource(fileName).getFile())) {
            var jsonFile = gson.fromJson(reader, Measurement[].class);
            result = Arrays.stream(jsonFile).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
