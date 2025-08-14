package com.Daejeon.Daejeon.repository;

import com.Daejeon.Daejeon.domain.entity.InternshipLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 스프링 빈으로 등록
public interface InternshipLocationRepository extends JpaRepository<InternshipLocation, String> {

}
