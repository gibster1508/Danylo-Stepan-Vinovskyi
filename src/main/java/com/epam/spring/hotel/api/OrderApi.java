package com.epam.spring.hotel.api;

import com.epam.spring.hotel.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Order API")
@RequestMapping("/hotel/order")
public interface OrderApi {

    @ApiOperation("get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    List<OrderDto> getAllOrders();

    @ApiOperation("create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    OrderDto createOrder(@RequestBody @Validated OrderDto orderDto);

    @ApiOperation("update order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user idOrder")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{idOrder}")
    OrderDto updateOrder(@PathVariable int idOrder, @RequestBody @Validated OrderDto orderDto);

    @ApiOperation("get all user's orders")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    List<OrderDto> getAllUserOrders(@PathVariable long id, @RequestParam int pageSize,
                                    @RequestParam int pageNumber, @RequestParam String sortType);
}