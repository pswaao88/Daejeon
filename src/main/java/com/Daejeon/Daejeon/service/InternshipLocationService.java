package com.Daejeon.Daejeon.service;

import com.Daejeon.Daejeon.domain.entity.InternshipLocation;
import com.Daejeon.Daejeon.repository.InternshipLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 스프링 빈으로 등록
public class InternshipLocationService {

    @Autowired
    private InternshipLocationRepository internshipLocationRepository;

    // Read: 모든 인턴십 정보 조회
    public List<InternshipLocation> findAll() {
        return internshipLocationRepository.findAll();
    }

    // Read: 특정 인턴십 정보 조회
    public Optional<InternshipLocation> findById(String internshipId) {
        return internshipLocationRepository.findById(internshipId);
    }

    // Create: 새로운 인턴십 정보 추가
    public InternshipLocation save(InternshipLocation internshipLocation) {
        return internshipLocationRepository.save(internshipLocation);
    }

    // Update: 인턴십 정보 수정
    public InternshipLocation update(String internshipId, InternshipLocation updatedLocation) {
        Optional<InternshipLocation> existingLocation = internshipLocationRepository.findById(internshipId);
        if (existingLocation.isPresent()) {
            InternshipLocation locationToUpdate = existingLocation.get();
            locationToUpdate.setLatitude(updatedLocation.getLatitude());
            locationToUpdate.setLongitude(updatedLocation.getLongitude());
            locationToUpdate.setLocationName(updatedLocation.getLocationName());
            return internshipLocationRepository.save(locationToUpdate);
        } else {
            // 해당 ID가 없을 경우 예외 처리
            throw new RuntimeException("InternshipLocation not found with id " + internshipId);
        }
    }

    // Delete: 인턴십 정보 삭제
    public void deleteById(String internshipId) {
        internshipLocationRepository.deleteById(internshipId);
    }
}
