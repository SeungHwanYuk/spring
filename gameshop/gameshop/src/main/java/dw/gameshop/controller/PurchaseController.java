package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/api/products/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase){
        return purchaseService.savePurchase(purchase);
    }

    @GetMapping("/api/products/purchase")
    public List<Purchase> getAllPurchase(){
        return purchaseService.getAllPurchase();
    }

    @GetMapping("/api/products/purchase/{userId}")
    public List<Purchase> getPurchaseListByUser(@PathVariable String userId) {
        return purchaseService.getPurchaseListByUser(userId);
    }
}
