package com.senla.bookstore.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.bookstore.model.IEntity;
import com.senla.bookstore.repository.interfaces.IRepository;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class JsonWriter {

    private static final String filePath = "bookstore\\src\\main\\jsonfiles\\";

    public<T> void write(List<T> repository, String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (Writer writer = new FileWriter(filePath + fileName)) {
            objectMapper.writeValue(writer, repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
