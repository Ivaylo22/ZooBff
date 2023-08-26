package tinqin.zoobff.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.redis.core.RedisHash;
import tinqin.zoostore.data.Item;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="carts")
@RedisHash("Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private Integer userId;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID itemId;
    private Double finalPrice;
    private Integer quantity;
}
