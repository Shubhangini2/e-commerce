package com.example.ecommerce.tranformer;

import com.example.ecommerce.dto.request.CardRequestDto;
import com.example.ecommerce.Model.Card;
import com.example.ecommerce.dto.response.CardResponseDto;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){

      return   CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                /*.cardNo(card.getCardNo())*/ //masked it will set in service
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();
    }
}
