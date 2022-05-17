package com.mymicroservice.account.api.controller;

import com.mymicroservice.account.business.AccountService;
import com.mymicroservice.account.eDto.AccountCreateRequest;
import com.mymicroservice.account.eDto.AccountUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public record AccountController(AccountService accountService) {

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id") String accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAccount(@RequestBody AccountCreateRequest request) {
        accountService.saveAccount(request);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestParam String accountId,
                                           @RequestBody AccountUpdateRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(accountId, request));
    }

    @DeleteMapping
    public void deleteAccountById(@RequestParam("id") String accountId) {
        accountService.deleteAccountById(accountId);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllAccounts(Pageable pageable) {
        return ResponseEntity.ok(accountService.getAllAccounts(pageable));
    }

}
