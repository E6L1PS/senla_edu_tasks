package ru.mirea.senla.bookstore.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    public void write(IRepository repository, String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Writer writer = new FileWriter(fileName);
            objectMapper.writeValue(writer, repository);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
