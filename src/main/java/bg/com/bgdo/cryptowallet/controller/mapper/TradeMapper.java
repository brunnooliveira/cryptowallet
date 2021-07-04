package bg.com.bgdo.cryptowallet.controller.mapper;

import bg.com.bgdo.cryptowallet.controller.request.TradePostRequest;
import bg.com.bgdo.cryptowallet.model.Trade;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface TradeMapper {

    Trade tradePostRequestToTrade(TradePostRequest tradePostRequest);

    default LocalDate map(Long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
