package com.mymicroservice.account.business;

import com.mymicroservice.clients.account.AccountResponse;
import com.mymicroservice.account.dataAccess.AccountRepository;
import com.mymicroservice.account.eDto.AccountCreateRequest;
import com.mymicroservice.account.eDto.AccountDtoConverter;
import com.mymicroservice.account.eDto.AccountUpdateRequest;
import com.mymicroservice.account.entities.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AccountService(AccountRepository accountRepository,
                             AccountDtoConverter accountDtoConverter) {

    public AccountResponse getAccountById(String accountId) {
        Account accountFromDb = findAccountById(accountId);
        return accountDtoConverter.convertToAccountResponse(accountFromDb);
    }

    public void saveAccount(AccountCreateRequest request) {
        accountRepository.save(accountDtoConverter.convertToAccount(request));
    }

    public AccountResponse updateAccount(String accountId, AccountUpdateRequest request) {
        Account accountFromDb = findAccountById(accountId);
        accountFromDb.setFirstName(request.firstName());
        accountFromDb.setLastName(request.lastName());
        accountFromDb.setPassword(request.password());

        return accountDtoConverter
                .convertToAccountResponse(accountRepository.save(accountFromDb));
    }

    public void deleteAccountById(String accountId) {
        accountRepository.deleteById(accountId);
    }

    private Account findAccountById(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalStateException("Account id not found: " + accountId));
    }

    public List<AccountResponse> getAllAccounts(Pageable pageable) {
        return accountDtoConverter
                .convertToListAccountResponse(accountRepository.findAll(pageable).getContent());
    }
}
