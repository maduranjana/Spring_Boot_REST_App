package com.encora.test.controller;

import com.encora.test.dto.AccountPageResponseDTO;
import com.encora.test.dto.AccountRequestDTO;
import com.encora.test.dto.AccountResponseDTO;
import com.encora.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping //create a new account
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO dto) {

        return new ResponseEntity<>(accountService.createAccount(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")  //update an existing account by ID
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO dto) {

        return new ResponseEntity<>(accountService.updateAccount(id, dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AccountPageResponseDTO>> getAll( // get a paginated list of accounts
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(accountService.getAllwithPagination(PageRequest.of(page, size)), HttpStatus.OK);
    }

}
