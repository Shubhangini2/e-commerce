package com.example.ecommerce.service;

import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.dto.request.OrderRequestDto;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.exception.CustomerNotFoundException;
import com.example.ecommerce.exception.InsufficientQuantityException;
import com.example.ecommerce.exception.InvalidCardException;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.Model.*;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.tranformer.ItemTransformer;
import com.example.ecommerce.tranformer.OrderTransformer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    CardService cardService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;


    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't exisit");
        }

        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || todayDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card");
        }

        Product product = productOptional.get();
        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Insufficient QUantity available");
        }

        int newQuantity = product.getAvailableQuantity()- orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        // prepare Order entity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskedCard(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDto.getRequiredQuantity()*product.getPrice());

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        orderEntity.setCustomer(customer);
        orderEntity.getItems().add(item);

        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);  // save order and item

        product.getItems().add(savedOrder.getItems().get(0));
        customer.getOrders().add(savedOrder);

        // send email
        sendEmail(savedOrder);

        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }

    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));// UUID
        //MaskedCard
        order.setCardUsed(cardService.generateMaskedCard(card.getCardNo()));

        int orderTotal = 0;
        //Iterating Over Item
        for(Item item: cart.getItems()){

            //We are getting product from item
            Product product = item.getProduct();
            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new InsufficientQuantityException("Sorry! Insufficient quatity available for: "+product.getProductName());
            }

            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setStatus(ProductStatus.OUT_OF_STOCK);
            }

            orderTotal += product.getPrice()*item.getRequiredQuantity();
            item.setOrderEntity(order);
        }

        order.setOrderTotal(orderTotal);
        order.setItems(cart.getItems());
        order.setCustomer(card.getCustomer());

        return order;
    }

    public void sendEmail(OrderEntity order){

        String text = "Congrats! Your order has been placed. Following are the details: '\n' " +
                "Order id = "+ order.getOrderId() + "\n"
                + "Order total = " + order.getOrderTotal()
                + "Order Date = " + order.getOrderDate();


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(order.getCustomer().getEmailId());
//        mail.setFrom("acciojobspring@gmail.com");
        mail.setFrom("gryffindor894@gmail.com");
        mail.setSubject("Order Placed");
        mail.setText(text);

        javaMailSender.send(mail);
    }
}
