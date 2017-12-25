package pl.mpankows.blockchain.sha256hash;

import com.google.common.hash.Hashing;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class ShaHash {

    private String originalString;
    private String hashedString;

    public ShaHash(String originalString) {
        this.originalString = originalString;
        this.hashedString = hashingFunction(originalString);
    }

    private String hashingFunction(String originalString) {
        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }

}
