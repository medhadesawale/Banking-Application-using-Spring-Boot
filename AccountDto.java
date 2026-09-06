package net.javaguides.Banking.Dto;

public record AccountDto(
        Long id,
        String accountHolderName,
        double balance
) {
}