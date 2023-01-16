package com.aegisep.api.xpvoice.web;

import com.aegisep.api.xpvoice.dto.XpvoiceVo;
import com.aegisep.api.xpvoice.service.XpvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
@RestController
@Slf4j
public class XpvoiceController {
    @Autowired
    XpvoiceService xpvoiceMapService;

    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping(value = "/xpvoice", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> getResidents1(
            @Parameter(description = "Xpvoice 정보조회", required = true, example = "{\"commands\":\"{\"command\":\"value\"}\"}")
            @RequestBody HashMap<String, Object> map
    ) throws Exception { //부모 Exception으로 변경
        log.info("get xpvoice {}", map);
        XpvoiceVo xpvoice = objectMapper.convertValue(map, XpvoiceVo.class);
        log.info("xpvoiceVo : {}", xpvoice);
        Collection<XpvoiceVo> selectXpvoice = xpvoiceMapService.selectXpvoice(xpvoice);
        return ResponseEntity.ok(objectMapper.writeValueAsString(selectXpvoice));
    }

}
