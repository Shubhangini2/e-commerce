package com.example.ecommerce.service;

import com.example.ecommerce.Model.Card;
import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.dto.request.CardRequestDto;
import com.example.ecommerce.dto.response.CardResponseDto;
import com.example.ecommerce.exception.CustomerNotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.tranformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobile());

        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        //create card entity

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        //List of card add a new card
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);
        //latest card
        List<Card> cards = savedCustomer.getCards();
        Card latestCard = cards.get(cards.size() - 1); ////////////////////////

        //Prepare card response DTO

        CardResponseDto cardResponseDto = CardTransformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setCardNo(generateMaskedCard(latestCard.getCardNo()));

        return cardResponseDto;
    }

    public String generateMaskedCard(String cardNo){
        int cardLength= cardNo.length();
        String maskedCard = "";
        for(int i=0; i<cardLength-4; i++){
            maskedCard += 'X';
        }

        maskedCard+= cardNo.substring(cardLength-4); //last 4 digits will not be masked
        return maskedCard;
    }

}
