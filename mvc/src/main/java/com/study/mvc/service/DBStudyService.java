package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBStudyService {
    @Autowired
    private DBStudyRepository dbStudyRepository;

    public DBStudyRespDto createStudy(DBStudyReqDto dbStudyReqDto) {
        // dto -> entity 바꾸는 과정 (Service->Repository)
        //AllArgs
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);
        //NoArgs
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());
        //Builder
        Study study = Study.builder()
                .name(dbStudyReqDto.getName())
                .age(dbStudyReqDto.getAge())
                .build();

        int successCount = dbStudyRepository.save(study);   //DB insert

        DBStudyRespDto dbStudyRespDto = DBStudyRespDto.builder()
                .id(study.getId())
                .name(study.getName())
                .age((study.getAge()))
                .successStatus(successCount > 0)
                .successCount(successCount)
                .build();

        return dbStudyRespDto;
    }

}