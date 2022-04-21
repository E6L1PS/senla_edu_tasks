package ru.mirea.senla.bookstore.model.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;

import java.io.*;

public class JsonReader {
    public IBookRepository readBookRepository(String fileName) {

        IBookRepository repository = new BookRepository();

        try {
            Reader reader = new FileReader(fileName);

            ObjectMapper mapper = new ObjectMapper();
            repository = mapper.readValue(reader, BookRepository.class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository;
    }

    public IOrderRepository readOrderRepository(String fileName) {
        IOrderRepository repository = new OrderRepository();

        try {
            Reader reader = new FileReader(fileName);

            ObjectMapper mapper = new ObjectMapper();
            repository = mapper.readValue(reader, OrderRepository.class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository;
    }

    public IRequestRepository readRequestRepository(String fileName) {
        IRequestRepository repository = new RequestRepository();

        try {
            Reader reader = new FileReader(fileName);

            ObjectMapper mapper = new ObjectMapper();
            repository = mapper.readValue(reader, RequestRepository.class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository;
    }
}
