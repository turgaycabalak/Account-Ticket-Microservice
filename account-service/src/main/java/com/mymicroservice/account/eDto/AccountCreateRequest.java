package com.mymicroservice.account.eDto;

public record AccountCreateRequest(String firstName,
                                   String lastName,
                                   String email,
                                   String password) {

}
