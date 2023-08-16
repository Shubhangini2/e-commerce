package com.example.ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import com.example.ecommerce.Enum.CardType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String customerName;

    String cardNo; //masked

    Date validTill;

    CardType cardType;
}
