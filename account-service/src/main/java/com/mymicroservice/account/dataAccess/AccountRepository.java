package com.mymicroservice.account.dataAccess;

import com.mymicroservice.account.entities.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account, String> {


}
