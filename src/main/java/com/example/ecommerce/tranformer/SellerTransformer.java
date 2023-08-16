package com.example.ecommerce.tranformer;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.Seller;
import com.example.ecommerce.dto.request.SellerRequestDto;
import com.example.ecommerce.dto.response.CustomerResponseDto;
import com.example.ecommerce.dto.response.SellerResponseDto;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return   Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){

        return   SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
