package com.Zyara.Repository;

import com.Zyara.Model.SpecialityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityCategoryRepository extends JpaRepository<SpecialityCategory, Long> {
        List<SpecialityCategory> findAllByCategoryId(String categoryId);
        @Query(value = "SELECT * FROM speciality_category WHERE rating=:rating",nativeQuery = true)
        List<SpecialityCategory> findAllByRatingEqual(@Param("rating") int rating);
}
