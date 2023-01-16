package com.aegisep.api.xpvoice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class VoiceAptiVo {
    private String subject, code, history_seq, apti;
    private int voice_idx;
}
