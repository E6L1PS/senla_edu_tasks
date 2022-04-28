package ru.mirea.senla.bookstore.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class JsonReader {

    private static final String filePath = "src\\main\\jsonfiles\\";

    public <T> T readRepository(String fileName, T repository) {

        T loadedRepository = repository;

        try(Reader reader = new FileReader(filePath + fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            loadedRepository = (T) mapper.readValue(reader, repository.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedRepository;
    }
}
