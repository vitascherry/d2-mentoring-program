package com.example.training.exchangeservice.service;

import java.math.BigDecimal;
import java.util.Currency;

public interface ExchangeRateService {

    /**
     * Possible usage:
     *     private <T> Comparator<T> comparingAmount(ToPriceFunction<? super T> keyExtractor) {
     *         return (a, b) -> {
     *             Price p1 = keyExtractor.applyAsPrice(a);
     *             Price p2 = keyExtractor.applyAsPrice(b);
     *             long p1Amount = p1.getAmount();
     *             long p2Amount = p2.getAmount();
     *             if (!p1.getCurrency().equals(SYSTEM_CURRENCY) && !p2.getCurrency().equals(SYSTEM_CURRENCY)) {
     *                 p1Amount = (long) (exchangeRateService.getCurrentRate(p1.getCurrency()) * p1.getAmount());
     *                 p2Amount = (long) (exchangeRateService.getCurrentRate(p2.getCurrency()) * p2.getAmount());
     *             } else if (!p1.getCurrency().equals(SYSTEM_CURRENCY)) {
     *                 p1Amount = (long) (exchangeRateService.getCurrentRate(p1.getCurrency()) * p1.getAmount());
     *             } else if (!p2.getCurrency().equals(SYSTEM_CURRENCY)) {
     *                 p2Amount = (long) (exchangeRateService.getCurrentRate(p2.getCurrency()) * p2.getAmount());
     *             }
     *             return Long.compare(p1Amount, p2Amount);
     *         };
     *     }
     * @param currency Currency instance to look for exchange rate
     * @return BigDecimal representation of current rate of currency passed comparing to SYSTEM_CURRENCY
     */
    BigDecimal getCurrentRate(Currency currency);
}
