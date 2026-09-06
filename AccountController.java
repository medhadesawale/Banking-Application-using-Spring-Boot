package net.javaguides.Banking.controller;

import net.javaguides.Banking.Dto.AccountDto;
import net.javaguides.Banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 1. Add Account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // 2. Get Account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // 3. Get All Accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // 4. Deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(accountService.deposit(id, request.get("amount")));
    }

    // 5. Withdraw
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                                @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(accountService.withdraw(id, request.get("amount")));
    }

    // 6. Delete Account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully!");
    }

    // 7. Transfer Amount
    @PostMapping("/transfer")
    public ResponseEntity<AccountDto> transfer(@RequestBody Map<String, Object> request) {
        Long fromId = Long.valueOf(request.get("fromAccountId").toString());
        Long toId = Long.valueOf(request.get("toAccountId").toString());
        double amount = Double.parseDouble(request.get("amount").toString());
        return ResponseEntity.ok(accountService.transfer(fromId, toId, amount));
    }
}