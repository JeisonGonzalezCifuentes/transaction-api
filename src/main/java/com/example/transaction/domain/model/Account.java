package com.example.transaction.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Account {

  Integer id;
  String number;
  String customerName;

}
