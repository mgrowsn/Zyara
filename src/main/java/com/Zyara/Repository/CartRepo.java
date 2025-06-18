package com.Zyara.Repository;

import com.Zyara.Model.CartItem;
import com.Zyara.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartItem, String> {
}
