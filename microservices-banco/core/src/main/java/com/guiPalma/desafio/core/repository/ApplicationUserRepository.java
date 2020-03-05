package com.guiPalma.desafio.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.guiPalma.desafio.core.model.ApplicationUser;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

}