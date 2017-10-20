package khan.InternetShop.Server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "ID", length = 100)
    private long id;
    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "CATEGORY_ID", insertable = false, updatable = false)
    private Long productCategoryId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_PRODUCT_TO_CATEGORY"))
    private ProductCategory productCategory;

    @Column(name = "QUANTITY", length = 100)
    private Integer quantity;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    public Product(String name, Long productCategoryId, ProductCategory productCategory, Integer quantity, String description) {
        this.name = name;
        this.productCategoryId = productCategoryId;
        this.productCategory = productCategory;
        this.quantity = quantity;
        this.description = description;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
