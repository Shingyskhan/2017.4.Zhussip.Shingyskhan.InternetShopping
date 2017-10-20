package khan.InternetShop.Server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue
    private long id;

    @Column (name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    // @OrderBy(value = "DATE_CREATED ASC")
    private List<Product> products = new ArrayList<>();
}
