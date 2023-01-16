package com.aegisep.api.xpvoice.mapper;

import com.aegisep.api.xpvoice.dto.VoiceAptiVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface VoiceAptiMapper {

    VoiceAptiVo selectVoiceApti (VoiceAptiVo vo);
    /*
    Integer insertResidents(ResidentsVo vo);
    Integer insertHouseholdComposition(@Param("aptcode") String aptcode, @Param("orgapt")  String orgapt, @Param("house_no")  String house_no, @Param("compositions") List<Composition> compositions);
    Integer insertCars(@Param("aptcode") String aptcode, @Param("orgapt")  String orgapt, @Param("house_no")  String house_no, @Param("cars") List<Cars> cars);
    */
}


