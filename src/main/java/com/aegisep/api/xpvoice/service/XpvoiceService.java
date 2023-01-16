package com.aegisep.api.xpvoice.service;


import com.aegisep.api.xpvoice.dto.XpvoiceVo;
import com.aegisep.api.xpvoice.mapper.XpvoiceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class XpvoiceService {
    @Autowired
    private XpvoiceMapper mapper;

    public Collection<XpvoiceVo> selectXpvoice(XpvoiceVo vo) {
        return mapper.selectXpvoice(vo);
    }
}
