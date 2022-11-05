// package edu.depaul.cdm.se452.group2.inventory.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import edu.depaul.cdm.se452.group2.inventory.Product;
// import edu.depaul.cdm.se452.group2.inventory.ProductRepository;

// @SpringBootTest
// public class ProductRepoTest {
    
//     @Autowired
//     private ProductRepository repo;

//     @Test
//     public void testCrud() {
//         var product = new Product();
//         product.setName("Box");
//         product.setDescription("High quality cardboard box.");
//         product.setPrice(230.25);
//         var initialProductId = product.getId();  
//         var initialRepoCount = repo.count();

//         repo.save(product);
//         var endProductId = product.getId();
//         var endRepoCount = repo.count();

//         assertNotEquals(initialProductId, endProductId);
//         assertEquals(initialRepoCount + 1, endRepoCount);

//         var repoProduct = repo.findById(endProductId).orElse(new Product());
//         var updatedPrice = 220.45;
//         repoProduct.setPrice(updatedPrice);

//         repo.save(repoProduct);
//         var updatedProduct = repo.findById(endProductId).orElse(new Product());
        
//         assertNotEquals(product, updatedProduct);
//         assertEquals(updatedProduct.getPrice(), updatedPrice);

//         initialRepoCount = repo.count();
//         repo.delete(updatedProduct);
//         endRepoCount = repo.count();
//         assertEquals(initialRepoCount - 1, endRepoCount);
//     }
// }
