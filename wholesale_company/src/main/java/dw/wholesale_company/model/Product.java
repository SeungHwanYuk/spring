package dw.wholesale_company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "제품")
public class Product {
    @Id
    @Column(name = "제품번호")
    private long productId;
    @Column(name = "제품명")
    private String productName;
    @Column(name = "포장단위")
    private String pkgUnit;
    @Column(name = "단가")
    private long unitPrice;
    @Column(name = "재고")
    private long inventory;
}
