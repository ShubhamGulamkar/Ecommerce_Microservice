package com.shubham.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(

        String id,

        String firstname,

        String lastname,

        String email,

        Address address
) {

}
