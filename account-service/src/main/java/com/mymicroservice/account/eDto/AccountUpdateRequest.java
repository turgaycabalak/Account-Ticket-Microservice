package com.mymicroservice.account.eDto;

public record AccountUpdateRequest(String firstName,
                                   String lastName,
                                   String password) {

}
