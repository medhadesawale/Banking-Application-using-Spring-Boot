package net.javaguides.Banking.service;

import net.javaguides.Banking.Dto.AccountDto;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    List<AccountDto> getAllAccounts();

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    void deleteAccount(Long id);

    AccountDto transfer(Long fromAccountId, Long toAccountId, double amount);
}