package com.encora.test.service;

import com.encora.test.Config.Constants;
import com.encora.test.dto.AccountPageResponseDTO;
import com.encora.test.dto.AccountRequestDTO;
import com.encora.test.dto.AccountResponseDTO;
import com.encora.test.entity.Account;
import com.encora.test.exception.ResourceNotFoundException;
import com.encora.test.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        AccountRequestDTO dto = new AccountRequestDTO("Testing Name", "Savings", 1000.0);
        Account saved = new Account();
        saved.setAccountID(1L);
        saved.setAccountName("Test Name");
        saved.setAccountType("Savings");
        saved.setAccountBalance(1000.0);

        when(accountRepository.save(any(Account.class))).thenReturn(saved);

        AccountResponseDTO response = accountService.createAccount(dto);

        assertEquals(1L, response.getAccountID());
        assertEquals(Constants.ACC_SAVE_SUCCESS, response.getMessage());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testUpdateAccount_Success() {
        Long id = 1L;
        AccountRequestDTO dto = new AccountRequestDTO("Updated", "Current", 2000.0);

        Account existing = new Account();
        existing.setAccountID(id);
        existing.setAccountName("Old");
        existing.setAccountType("Savings");
        existing.setAccountBalance(1000.0);

        when(accountRepository.findById(id)).thenReturn(Optional.of(existing));
        when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

        AccountResponseDTO response = accountService.updateAccount(id, dto);

        assertEquals(id, response.getAccountID());
        assertEquals(Constants.ACC_UPDATE_SUCCESS, response.getMessage());
    }

    @Test
    void testUpdateAccount_NotFound() {
        Long id = 1L;
        AccountRequestDTO dto = new AccountRequestDTO("Updated", "Current", 2000.0);

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            accountService.updateAccount(id, dto);
        });
    }

    @Test
    void testGetAllwithPagination() {
        Account acc1 = new Account();
        acc1.setAccountID(1L);
        acc1.setAccountName("A1");
        acc1.setAccountType("Savings");
        acc1.setAccountBalance(5000.0);

        List<Account> accountList = Collections.singletonList(acc1);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Account> page = new PageImpl<>(accountList, pageable, 1);

        when(accountRepository.findAll(pageable)).thenReturn(page);

        Page<AccountPageResponseDTO> result = accountService.getAllwithPagination(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("A1", result.getContent().get(0).getAccountName());
    }
}
