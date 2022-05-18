package com.example.multipledatasources.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multipledatasources.model.card.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
