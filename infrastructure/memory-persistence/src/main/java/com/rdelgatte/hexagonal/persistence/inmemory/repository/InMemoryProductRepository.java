package com.rdelgatte.hexagonal.persistence.inmemory.repository;

import static io.vavr.API.List;

import com.rdelgatte.hexagonal.product.domain.Product;
import com.rdelgatte.hexagonal.product.spi.ProductRepository;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class InMemoryProductRepository implements ProductRepository {

  private List<Product> inMemoryProducts = List();

  public Product addProduct(Product product) {
    this.inMemoryProducts = getInMemoryProducts().append(product);
    return product;
  }

  public void deleteProduct(UUID productId) {
    this.inMemoryProducts = getInMemoryProducts().filter(product -> !product.getId().equals(productId));
  }

  public Option<Product> findProductByCode(String code) {
    return getInMemoryProducts().find(product -> product.getCode().equals(code));
  }

  public List<Product> findAllProducts() {
    return getInMemoryProducts();
  }
}
