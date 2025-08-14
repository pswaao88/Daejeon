package com.Daejeon.Daejeon.repository;

import com.Daejeon.Daejeon.domain.entity.InternshipLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 스프링 빈으로 등록
public interface InternshipLocationRepository extends JpaRepository<InternshipLocation, String> {
    // JpaRepository를 상속받아 기본적인 CRUD 메서드를 자동으로 제공받음
    // 예: findById(), findAll(), save(), deleteById() 등
}
