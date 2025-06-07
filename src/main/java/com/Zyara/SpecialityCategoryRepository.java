package com.Zyara;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityCategoryRepository extends JpaRepository<SpecialityCategory, Long> {
        List<SpecialityCategory> findAllByCategoryId(String categoryId);
}
