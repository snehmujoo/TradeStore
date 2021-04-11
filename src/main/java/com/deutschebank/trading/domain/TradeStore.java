package com.deutschebank.trading.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeStore {

	@Id
	long tradeId;
	int version;
	String counterPartyId;
	String bookId;
	LocalDate maturityDate;
	LocalDate createdDate;
	String expired;

}
