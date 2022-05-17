package com.mymicroservice.clients.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("account-service")
public interface AccountFeignClient {

    @GetMapping("api/v1/accounts/{id}")
    ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") String accountId);


}
