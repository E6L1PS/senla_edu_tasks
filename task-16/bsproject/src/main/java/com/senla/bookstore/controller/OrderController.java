package com.senla.bookstore.controller;

import com.senla.bookstore.dto.OrderDTO;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.service.interfaces.IOrderService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@NoArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/{key}")
    public List<OrderDTO> getSortedOrders(@PathVariable String key) {
        log.info("Method call 'getSortedOrders'");
        return orderService.getSortedOrders(key)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/completed/{key}")
    public List<OrderDTO> getCompletedOrders(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                             @PathVariable String key) {

        log.info("Method call 'getCompletedOrders'");
        return orderService.getCompletedOrders(startDate, endDate, key)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/fullprice")
    public int getFullPrice(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                            @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        log.info("Method call 'getFullPrice'");
        return orderService.getFullPrice(startDate, endDate);
    }

    @GetMapping("/completed/quantity")
    public Long getQuantityCompletedOrders(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        log.info("Method call 'getQuantityCompletedOrders'");
        return orderService.getQuantityCompletedOrders(startDate, endDate);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody List<Book> books) {
        log.info("Method call 'addOrder'");
        orderService.addOrder(books);
    }

    @DeleteMapping("/delete/{id}")
   // @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable int id) {
        log.info("Method call 'removeOrder'");
        orderService.removeOrder(id);
    }


    @PutMapping("/{orderId}/{orderStatus}")
    public void setStatus(@PathVariable int orderId, @PathVariable OrderStatus orderStatus) {
        log.info("Method call 'setStatus'");
        orderService.setStatus(orderId, orderStatus);
    }


    //TODO imp and export
    public void exportOrders() {
        orderService.exportOrders();
    }

    public void exportOrder(int id) {
        orderService.exportOrder(id);
    }

    public void importOrders() {
        orderService.importOrders();
    }


    private OrderDTO convertEntityToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setBooksIds(order.getBookIds());
        orderDTO.setIssueDate(order.getIssueDate());
        return orderDTO;
    }
}
