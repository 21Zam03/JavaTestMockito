package com.zam.JavaTestMockito.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequest {

    private String firstName;
    private String lastName;

}
