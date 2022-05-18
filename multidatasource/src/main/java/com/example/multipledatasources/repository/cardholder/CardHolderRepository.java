package com.example.multipledatasources.repository.cardholder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multipledatasources.model.cardholder.CardHolder;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
