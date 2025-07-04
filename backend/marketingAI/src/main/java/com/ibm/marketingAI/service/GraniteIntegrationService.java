package com.ibm.marketingAI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.marketingAI.dto.CampaignRequest;
import com.ibm.marketingAI.model.CampaignResponse;



@Service
public class GraniteIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${nodejs.url}")
    private String url;

    public GraniteIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CampaignResponse callGranite(CampaignRequest input) {
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CampaignRequest> request = new HttpEntity<>(input, headers);

        ResponseEntity<CampaignResponse> response = restTemplate.postForEntity(
            url,
            request,
            CampaignResponse.class
        );

        return response.getBody();
    }
}
