package com.example.ecommerce.tranformer;

import com.example.ecommerce.dto.request.ItemRequestDto;
import com.example.ecommerce.dto.response.ItemResponseDto;
import com.example.ecommerce.Model.Item;
public class ItemTransformer {

    public static Item ItemRequestDtoToItem(int requiredQuantity){

        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }
}
