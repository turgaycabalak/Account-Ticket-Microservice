package com.mymicroservice.clients.account;

import java.time.LocalDateTime;

public record AccountResponse(String id,
                              String firstName,
                              String lastName,
                              String email,
                              LocalDateTime createdAt,
                              Boolean isActive) {

}
