package bg.com.bgdo.cryptowallet.controller;

import bg.com.bgdo.cryptowallet.model.Wallet;
import bg.com.bgdo.cryptowallet.service.WalletService;
import bg.com.bgdo.cryptowallet.shared.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Constants.URL_API_BASE_PRIVATE + "/wallets")
public class WalletController {

    private WalletService walletService;

    @GetMapping
    public Wallet getWallet(){
        Wallet wallet = walletService.getWallet();
        return wallet;
    }
}
