package com.study.mvc.repository;

import com.study.mvc.entity.Study;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DBStudyRepository {
    // tb마다 repository 인터페이스 하나라고 생각
    public int save(Study study);
}
