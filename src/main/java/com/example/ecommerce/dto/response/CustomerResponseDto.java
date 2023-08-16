package com.example.ecommerce.dto.response;

import com.example.ecommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {

    String name;

    String emailId;

    String mobNo;

    Gender gender;
}
