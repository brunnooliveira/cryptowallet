package bg.com.bgdo.cryptowallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class Wallet {
    private List<Asset> assets;

    public List<Asset> getAssets() {
        return assets;
    }
}
