package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyInsertRespDto;
import com.study.mvc.dto.DBStudySelectRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBStudyService {
    @Autowired
    private DBStudyRepository dbStudyRepository;

    public DBStudyInsertRespDto createStudy(DBStudyReqDto dbStudyReqDto) {
        // dto -> entity 바꾸는 과정 (Service->Repository)
        //AllArgs
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);
        //NoArgs
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());
        //Builder
        //중복체크
        Study findStudy = dbStudyRepository.findStudyByName(dbStudyReqDto.getName());
        if(findStudy != null) {
            return DBStudyInsertRespDto.builder()
                    .successStatus(false)
                    .build();
        }
        //
        Study study = Study.builder()
                .name(dbStudyReqDto.getName())
                .age(dbStudyReqDto.getAge())
                .build();

        int successCount = dbStudyRepository.save(study);   //DB insert

        DBStudyInsertRespDto dbStudyInsertRespDto = DBStudyInsertRespDto.builder()
                .id(study.getId())
                .name(study.getName())
                .age((study.getAge()))
                .successStatus(successCount > 0)
                .successCount(successCount)
                .build();

        return dbStudyInsertRespDto;
    }

    public DBStudySelectRespDto findStudyById(int id) {
        Study study = dbStudyRepository.findStudyById(id);
        System.out.println(study);

        DBStudySelectRespDto dbStudySelectRespDto = DBStudySelectRespDto.builder()
                .id(study.getId())
                .name(study.getName())
                .age((study.getAge()))
                .build();

        return dbStudySelectRespDto;
    }

    public DBStudySelectRespDto findStudyByName(String name) {
        Study study = dbStudyRepository.findStudyByName(name);

        return study == null ? null : study.toDto();
    }

    public List<DBStudySelectRespDto> findAll() {
        List<DBStudySelectRespDto> respDtoList = new ArrayList<>();
        List<Study> studyList = dbStudyRepository.findAll();
        for (Study study : studyList) {
            respDtoList.add(study.toDto());
        }

        return respDtoList;
    }
    //stream 으로 사용
    public List<DBStudySelectRespDto> findAll2() {

        return dbStudyRepository.findAll().stream().map(Study::toDto).collect(Collectors.toList());
    }

}