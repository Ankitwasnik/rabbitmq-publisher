package com.ankitwasnik.rabbitmq.publisher.service;

import com.ankitwasnik.rabbitmq.publisher.dto.TxnRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TxnService {

  @Autowired
  private StreamBridge streamBridge;

  public void addTxnDetails(TxnRequest txnRequest) {
    log.info("Publishing message: {}", txnRequest);
    final MessageBuilder<TxnRequest> messageBuilder = MessageBuilder.withPayload(txnRequest);
    final Message<TxnRequest> txnRequestMessage = messageBuilder.build();
    final boolean isPublished = streamBridge.send("processTxnRequest-out-0", txnRequestMessage);
    if (!isPublished) {
      // TODO: Need to implement a retry, fallback & notification
      log.error("Failed to publish message: {}", txnRequestMessage);
    }
  }
}
