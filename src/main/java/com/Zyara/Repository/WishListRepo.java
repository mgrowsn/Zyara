package com.Zyara.Repository;

import com.Zyara.Model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends JpaRepository<WishListItem,String> {
}
