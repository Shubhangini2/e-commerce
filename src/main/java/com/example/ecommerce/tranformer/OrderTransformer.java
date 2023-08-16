package com.example.ecommerce.tranformer;

import com.example.ecommerce.dto.response.ItemResponseDto;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.Model.Item;
import com.example.ecommerce.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity order){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: order.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .cardUsed(order.getCardUsed())
                .orderTotal(order.getOrderTotal())
                .customerName(order.getCustomer().getName())
                .item(itemResponseDtoList)
                .build();

    }
}
