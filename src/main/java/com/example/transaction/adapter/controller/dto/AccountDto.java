package com.example.transaction.adapter.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDto {

  Integer id;
  String number;
  String customerName;

}