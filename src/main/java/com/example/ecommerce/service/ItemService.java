package com.example.ecommerce.service;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.Item;
import com.example.ecommerce.dto.request.ItemRequestDto;
import com.example.ecommerce.exception.CustomerNotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.Model.Product;
import java.util.Optional;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.exception.InsufficientQuantityException;
import com.example.ecommerce.tranformer.ItemTransformer;
@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;


    public Item createItem(ItemRequestDto itemRequestDto) {
        //Customer exist or not
        Customer customer= customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
    if(customer==null){
        throw new CustomerNotFoundException("Customer doesn't exist");
    }

    //product id is correct or not
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }
        Product product = productOptional.get();

        // check for required quantity
        if(product.getAvailableQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! Required quantity not avaiable");
        }

        // create item
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
