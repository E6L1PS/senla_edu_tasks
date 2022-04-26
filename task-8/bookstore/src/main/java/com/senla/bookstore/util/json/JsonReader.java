package com.senla.bookstore.util.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private static final String filePath = "bookstore\\src\\main\\jsonfiles\\";

    public static <T> List<T> readRepository(String fileName, Class<T> t) {

        try (Reader reader = new FileReader(filePath + fileName)) {
            ObjectMapper mapper = new ObjectMapper();

            JavaType type = mapper.getTypeFactory().
                    constructCollectionType(ArrayList.class, t);

            return mapper.readValue(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
