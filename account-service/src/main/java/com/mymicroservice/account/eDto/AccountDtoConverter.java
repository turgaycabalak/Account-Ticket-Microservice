package com.mymicroservice.account.eDto;

import com.mymicroservice.clients.account.AccountResponse;
import com.mymicroservice.account.entities.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccountDtoConverter {

    public AccountResponse convertToAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getCreatedAt(),
                account.getIsActive()
        );
    }

    public Account convertToAccount(AccountCreateRequest request) {
        return new Account(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                LocalDateTime.now(),
                true
        );
    }

    public List<AccountResponse> convertToListAccountResponse(List<Account> accountList) {
        return accountList.stream()
                .map(this::convertToAccountResponse)
                .toList();
    }
}
