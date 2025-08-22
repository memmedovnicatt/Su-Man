package com.nicat.suman.controller;

import com.nicat.suman.model.dto.request.OrderAddRequest;
import com.nicat.suman.model.dto.request.OrderUpdateRequest;
import com.nicat.suman.model.dto.response.OrderAddResponse;
import com.nicat.suman.model.dto.response.OrderResponse;
import com.nicat.suman.model.dto.response.OrderUpdateResponse;
import com.nicat.suman.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order Controller",
        description = "Handles order creation, update, deletion and retrieval")
@RequestMapping("/orders")
@MyRestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @Operation(summary = "Create a new order", description = "Adds a new order with given request data")
    @PostMapping("/add")
    public ResponseEntity<OrderAddResponse> add(@RequestBody OrderAddRequest orderAddRequest) {
        OrderAddResponse orderAddResponse = orderService.add(orderAddRequest);
        return ResponseEntity.ok(orderAddResponse);
    }


    @Operation(summary = "Delete an order", description = "Deletes the order by ID")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }


    @Operation(summary = "Update an order", description = "Updates an existing order by ID")
    @PatchMapping("/update/{id}")
    public ResponseEntity<OrderUpdateResponse> update(@PathVariable Long id,
                                                      @RequestBody OrderUpdateRequest orderUpdateRequest) {
        OrderUpdateResponse orderUpdateResponse = orderService.update(id, orderUpdateRequest);
        return ResponseEntity.ok(orderUpdateResponse);
    }

    @Operation(summary = "Get order by ID", description = "Retrieves order details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getById(id);
        return ResponseEntity.ok(orderResponse);
    }
}