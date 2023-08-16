package com.example.ecommerce.service;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.dto.request.SellerRequestDto;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.tranformer.CustomerTransformer;
import com.example.ecommerce.tranformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.Model.Seller;
import com.example.ecommerce.dto.response.SellerResponseDto;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){

       //dto-->entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        //save the entity
        Seller savedSeller = sellerRepository.save(seller);


        //prepare the response dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
