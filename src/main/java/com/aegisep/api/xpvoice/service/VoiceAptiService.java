package com.aegisep.api.xpvoice.service;


import com.aegisep.api.xpvoice.dto.VoiceAptiVo;
import com.aegisep.api.xpvoice.mapper.VoiceAptiMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@AllArgsConstructor
public class VoiceAptiService {
    @Autowired
    private VoiceAptiMapper mapper;

    public VoiceAptiVo selectVoiceApti(VoiceAptiVo vo){
        return mapper.selectVoiceApti(vo);
    }

    public String getApi(VoiceAptiVo vo) throws Exception {
        // 헤더 설정
        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.setContentType(MediaType.APPLICATION_JSON);
        // 토큰 설정
        httpheaders.set("Authorization","aegsa!xzf435s3g59w55si6@n3ftss98#ngnwzo7h4j4bsxs4h");

        //HttpEntity에 헤더 및 parameter 설정
        //HttpEntity entity = new HttpEntity(vo, httpheaders);
        HttpEntity entity = new HttpEntity(httpheaders);

        // RestTemplate의 exchange 메소드를 통해 URL에 HttpEntity와 함께 요청
        RestTemplate restTemplate = new RestTemplate();
        // response
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("EUC-KR")));

        ResponseEntity<String> responseEntity = null;
        String resultApi = "";

        //해당 API의 경우 파라미터를 queryParam으로 요청
        String url = "https://apits.apti.co.kr/voice/W1/";

        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("code", vo.getCode())
                .queryParam("subject", vo.getSubject())
                .queryParam("voice_idx", vo.getVoice_idx())
                .build()
                .encode(StandardCharsets.UTF_8);

        System.out.println(uriBuilder.toString());

        //API 예외처리
        try{
            responseEntity = restTemplate.exchange(
                    uriBuilder.toString()
                    , HttpMethod.POST
                    , entity
                    , String.class);
        //4xx error
        }catch(HttpClientErrorException e) {
            log.info("HttpClientErrorException : " + e.getResponseBodyAsString());
        //5xx error
        }catch(HttpServerErrorException e) {
            log.info("HttpServerErrorException : " + e.getResponseBodyAsString());
        }catch(Exception e) {
            throw new Exception(String.valueOf(e.getStackTrace()));
        }

        if (responseEntity.getBody() != null) {
            log.info("statusCode : " + responseEntity.getStatusCode());
            resultApi = responseEntity.getBody();
        }
        return resultApi;
    }
}
