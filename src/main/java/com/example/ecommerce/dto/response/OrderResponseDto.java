package com.example.ecommerce.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderId;  // UUID

    Date orderDate;

    String cardUsed;

    int orderTotal;

    String customerName;

    List<ItemResponseDto> item;
}
