package com.example.cmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cmc.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	Account findByUserName(String name);
}
