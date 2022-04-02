package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.service.WarehouseService;

public class WarehouseController {
    private WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }
}
