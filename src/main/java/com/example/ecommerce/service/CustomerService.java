package com.example.ecommerce.service;

import com.example.ecommerce.Model.Cart;
import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.dto.request.CustomerRequestDto;
import com.example.ecommerce.dto.response.CustomerResponseDto;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.tranformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //Created cart
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

       Customer savedCustomer = customerRepository.save(customer); //Saves both customer and cart

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}




//Convert Request DTO-----> entity

//        Customer customer= new Customer();
//        customer.setName(customerRequestDto.getName());
//        customer.setEmailId(customerRequestDto.getEmailId());
//        customer.setMobNo(customerRequestDto.getMobNo());
//        customer.setGender(customerRequestDto.getGender());


//By using builder
//        Customer customer= Customer.builder()
//                .name(customerRequestDto.getName())
//                .gender(customerRequestDto.getGender())
//                .mobNo(customerRequestDto.getMobNo())
//                .emailId(customerRequestDto.getEmailId())
//                .build();





//Prepare the response dto
//        CustomerResponseDto customerResponseDto= CustomerResponseDto.builder()
//                .name(savedCustomer.getName())
//                .emailId(savedCustomer.getEmailId())
//                .mobNo(savedCustomer.getEmailId())
//                .gender(savedCustomer.getGender())
//                .build();