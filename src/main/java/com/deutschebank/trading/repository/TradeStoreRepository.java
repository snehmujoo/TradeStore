package com.deutschebank.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deutschebank.trading.domain.TradeStore;

@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore, Long> {
	TradeStore findById(long id);
}
