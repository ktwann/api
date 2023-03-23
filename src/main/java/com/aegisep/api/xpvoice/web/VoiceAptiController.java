package com.aegisep.api.xpvoice.web;

import com.aegisep.api.xpvoice.dto.VoiceAptiVo;
import com.aegisep.api.xpvoice.service.VoiceAptiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VoiceAptiController {
    @Autowired
    VoiceAptiService voiceaptiService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/voiceApti", produces = "application/json; charset=UTF-8")
    public void getVoiceApti(
            @Parameter(description = "APTI API 호출", required = true, example = "{\"commands\":\"{\"command\":\"value\"}\"}")
            @RequestBody VoiceAptiVo vo
    ) throws Exception { //부모 Exception으로 변경
        VoiceAptiVo selectApi = (VoiceAptiVo) voiceaptiService.selectVoiceApti(vo);
        //String param = objectMapper.writeValueAsString(selectVoiceApti);
        vo.setCode(selectApi.getCode());
        vo.setSubject(selectApi.getSubject());
        vo.setVoice_idx(selectApi.getVoice_idx());

        log.info("selectData {}", vo);
        String res = voiceaptiService.getApi(vo);
        log.info("api response {}", res);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(res);
        log.info("code {}", jsonObject.get("code"));
        log.info("message {}", jsonObject.get("message"));

        //return ResponseEntity.ok(objectMapper.writeValueAsString(selectVoiceApti));
    }

}
