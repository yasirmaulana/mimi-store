package io.github.yasirmaulana.mimi_store.service;

import io.github.yasirmaulana.mimi_store.dto.MockApiResponse;

public interface SubscriberService {
    MockApiResponse getPinByMsisdn(String msisdn);
}
