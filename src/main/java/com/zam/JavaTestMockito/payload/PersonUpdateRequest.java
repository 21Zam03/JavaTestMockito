package com.zam.JavaTestMockito.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonUpdateRequest {

    private Integer personId;
    private String firstName;
    private String lastName;

}
