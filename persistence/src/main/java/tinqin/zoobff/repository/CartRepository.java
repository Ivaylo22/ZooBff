package tinqin.zoobff.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinqin.zoobff.data.Cart;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> getAllByUserId(Integer userId);
    Cart getByItemId(UUID itemId);
    void deleteById(UUID cartId);
}
