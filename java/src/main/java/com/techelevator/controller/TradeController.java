package com.techelevator.controller;

import com.techelevator.dao.TradeDao;
import com.techelevator.model.trade.TradeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trades")
public class TradeController {
    private TradeDao tradeDao;

    public TradeController(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    @GetMapping("/{userId}")
    public List<TradeDto> getTradesByUserId(@PathVariable Integer userId) {
        return tradeDao.getTradesByUserId(userId);
    }

    @PostMapping("/trades")
    public Boolean createTrade(@RequestBody TradeDto trade) {

        return true;
    }
}
