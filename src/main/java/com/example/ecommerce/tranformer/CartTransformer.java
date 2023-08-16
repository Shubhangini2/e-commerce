package com.example.ecommerce.tranformer;

import com.example.ecommerce.dto.response.CartResponseDto;
import com.example.ecommerce.dto.response.ItemResponseDto;
import com.example.ecommerce.Model.Cart;
import com.example.ecommerce.Model.Item;
import java.util.ArrayList;
import java.util.List;
public class CartTransformer {

    public static CartResponseDto CartToCartReponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
