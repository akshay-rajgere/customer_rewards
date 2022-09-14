package com.customerrewards.rewardcalculator.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerTransaction {
	
	
	private Integer customerId;
	private Long amount;
	
	private LocalDate date;

}
