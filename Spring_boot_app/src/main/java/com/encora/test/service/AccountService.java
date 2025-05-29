package com.encora.test.service;

import com.encora.test.Config.Constants;
import com.encora.test.dto.AccountPageResponseDTO;
import com.encora.test.dto.AccountRequestDTO;
import com.encora.test.dto.AccountResponseDTO;
import com.encora.test.entity.Account;
import com.encora.test.exception.ResourceNotFoundException;
import com.encora.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO dto) {

        Account entity = new Account();

        entity.setAccountName(dto.getAccountName());
        entity.setAccountType(dto.getAccountType());
        entity.setAccountBalance(dto.getAccountBalance());

        Account saved = accountRepository.save(entity);

        //saved account ID and success message
        return new AccountResponseDTO(saved.getAccountID(), Constants.ACC_SAVE_SUCCESS);
    }

    @Transactional(rollbackFor = ResourceNotFoundException.class) // Rolls back if account not found
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO dto) {
        Account existing = accountRepository.findById(id) // Find existing account or throw an exception
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ACC_NOT_FOUND));

        existing.setAccountName(dto.getAccountName());
        existing.setAccountType(dto.getAccountType());
        existing.setAccountBalance(dto.getAccountBalance());

        Account updated = accountRepository.save(existing);
        return new AccountResponseDTO(updated.getAccountID(),Constants.ACC_UPDATE_SUCCESS);
    }

    @Transactional(readOnly = true ) // This is a read-only transaction
    public Page<AccountPageResponseDTO> getAllwithPagination(Pageable pageable) {
        return accountRepository.findAll(pageable) //pagination and map each entity to a DTO
                .map(acc -> new AccountPageResponseDTO(
                        acc.getAccountID(),
                        acc.getAccountName(),
                        acc.getAccountType(),
                        acc.getAccountBalance()));
    }

}
