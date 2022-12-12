package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.model.trade.TradeComicDto;
import com.techelevator.model.trade.TradeDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/trades")
public class TradeController {


    private TradeDao tradeDao;
    private CollectionDao collectionDao;

    public TradeController(TradeDao tradeDao, CollectionDao collectionDao) {
        this.tradeDao = tradeDao;
        this.collectionDao = collectionDao;
    }

    @GetMapping("/{userId}")
    public List<TradeDto> getTradesByUserId(@PathVariable Integer userId) {
        return tradeDao.getTradesByUserId(userId);
    }

    @PostMapping("/create")
    public Boolean createTrade(@RequestBody TradeDto trade) {
        return tradeDao.createTrade(trade);
    }

    @PutMapping("/accept/{tradeId}")
    public Boolean acceptTrade(@PathVariable Integer tradeId) {
        if (!tradeDao.tradeExists(tradeId)) {
            throw new IllegalArgumentException("Trade does not exist");
        }
        else if (!tradeDao.tradeIsPending(tradeId)) {
            throw new IllegalArgumentException("Trade must be pending to accept");
        }
        TradeDto trade = tradeDao.getTradeById(tradeId);
        for (TradeComicDto comic : trade.getComics()) {
            Integer newBaseId = collectionDao.getBaseCollectionIdByUserId(comic.getTo().getId());
            if (!tradeDao.transferComic(comic, newBaseId)) {
                return false;
            }
        }
        return tradeDao.acceptTrade(tradeId);
    }

    @PutMapping("/reject/{tradeId}")
    public Boolean rejectTrade(@PathVariable Integer tradeId) {
        if (!tradeDao.tradeIsPending(tradeId)) {
            throw new IllegalArgumentException("Trade must be pending to reject");
        }
        return tradeDao.rejectTrade(tradeId);
    }

    @PutMapping("/cancel/{tradeId}")
    public Boolean cancelTrade(@PathVariable Integer tradeId) {
        if (!tradeDao.tradeIsPending(tradeId)) {
            throw new IllegalArgumentException("Trade must be pending to cancel");
        }
        return tradeDao.rejectTrade(tradeId);
    }

    @GetMapping("/pending/{userId}")
    public Map<String, List<TradeDto>> getPendingByUserId(@PathVariable Integer userId) {
        Map<String, List<TradeDto>> out = new HashMap<>();
        out.put("pending", tradeDao.getPendingByUserId(userId));
        out.put("requests", tradeDao.getRequestsByUserId(userId));
        return out;
    }
}