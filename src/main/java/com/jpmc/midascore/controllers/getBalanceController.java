package com.jpmc.midascore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;

@RestController
public class getBalanceController {

    @Autowired
    private DatabaseConduit databaseConduit;

    @GetMapping("/balance")
    public Balance bal(@RequestParam long userId){

        UserRecord foundUser = databaseConduit.getUserById(userId);
        float savedBalance = 0;
        if (foundUser != null){
            savedBalance = foundUser.getBalance();
        }

        return new Balance(savedBalance);
    }
}
