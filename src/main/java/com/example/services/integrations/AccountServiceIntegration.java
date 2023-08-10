package com.example.services.integrations;


import com.example.services.dto.SubscriptionToProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class AccountServiceIntegration {

    private final WebClient accountServiceWebClient;

    public void makeABuy(SubscriptionToProductRequest subscription){
        accountServiceWebClient.post()
                .uri("/api/v1/clients/subscriptions/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subscription)
                .retrieve()
                .toBodilessEntity()
                .block();

    }


}
