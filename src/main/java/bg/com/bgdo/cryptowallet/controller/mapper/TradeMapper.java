package bg.com.bgdo.cryptowallet.controller.mapper;

import bg.com.bgdo.cryptowallet.controller.request.TradeGetQuery;
import bg.com.bgdo.cryptowallet.controller.request.TradeGetResponse;
import bg.com.bgdo.cryptowallet.controller.request.TradePostRequest;
import bg.com.bgdo.cryptowallet.controller.request.TradePutRequest;
import bg.com.bgdo.cryptowallet.model.Trade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface TradeMapper {

    @Mapping(target = "id", ignore = true)
    Trade tradePostRequestToTrade(TradePostRequest tradePostRequest);

    Trade tradePutRequestToTrade(TradePutRequest tradePutRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "amount", ignore = true)
    Trade tradeGetQueryToTrade(TradeGetQuery tradeGetQuery);

    TradeGetResponse tradeToTradeGetResponse(Trade trade);

//    default LocalDate map(Long time) {
//        if(time == null)
//            return null;
//        return Instant.ofEpochMilli(time).atOffset(ZoneOffset.UTC).toLocalDate();
//    }
//
//    default Long map(LocalDate date) {
//        if(date == null)
//            return null;
//        return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
//    }
}
