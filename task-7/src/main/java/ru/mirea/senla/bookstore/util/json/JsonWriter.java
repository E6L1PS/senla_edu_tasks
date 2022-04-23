package ru.mirea.senla.bookstore.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mirea.senla.bookstore.repository.interfaces.IRepository;

import java.io.FileWriter;
import java.io.Writer;

public class JsonWriter {

    private static final String filePath = "src\\main\\jsonfiles\\";

    public void write(IRepository repository, String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();

        try(Writer writer = new FileWriter(filePath + fileName)) {
            objectMapper.writeValue(writer, repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
