package com.ankitwasnik.rabbitmq.publisher.controller;

import com.ankitwasnik.rabbitmq.publisher.dto.TxnRequest;
import com.ankitwasnik.rabbitmq.publisher.service.TxnService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
public class TxnController {

  @Autowired
  private TxnService txnService;

  @PostMapping
  public void addTxnDetails(@RequestBody @Valid final TxnRequest txnRequest) {
    txnService.addTxnDetails(txnRequest);
  }
}
