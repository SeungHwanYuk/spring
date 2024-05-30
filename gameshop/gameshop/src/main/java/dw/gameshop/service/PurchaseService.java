package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    public Purchase savePurchase(Purchase purchase) {
        // 구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        purchaseRepository.save(purchase);

        return purchase;
    }
    public List<Purchase> savePurchaseList(List<Purchase> purchaseList) {
        // 구매확정 바로 직전, 현재시간을 저장함
        for (int i = 0; i < purchaseList.size(); i++) {
            purchaseList.get(i).setPurchaseTime(LocalDateTime.now());
            purchaseRepository.save(purchaseList.get(i));
        }
        return purchaseList;
    }

//    public List<Purchase> savePurchaseList(List<Purchase> purchaseList) {
//        // 구매확정 바로 직전, 현재시간을 저장함
//        List<Purchase> savePurchaseList = purchaseList.stream()
//                .map((purchase)-> {
//                    purchase.setPurchaseTime(LocalDateTime.now());
//                    purchaseRepository.save(purchase);
//                })
//                .collect(Collectors.toList());
//        return savePurchaseList;
//    }




    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchaseListByUser(String userId) {
        // 유저 아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "ID", userId);
        } else {
            return purchaseRepository.findByUser(userOptional.get());
        }

    }

    // 유저 이름으로 구매한 게임 찾기

    public List<Purchase> getUserIdByUserName(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "Name", userName);
        } else {
            return purchaseRepository.findByUser(userOptional.get());
        }
    }
}
