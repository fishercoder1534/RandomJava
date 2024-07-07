package encryption;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class SpringSecurityCryptoModule {
    /**
     * Reference: https://docs.spring.io/spring-security/site/docs/3.1.x/reference/crypto.html
     */
    public static void main(String... args) {
//        BytesEncryptor encryptor = Encryptors.standard("password", "salt");

        String salt = KeyGenerators.string().generateKey(); // generates a random 8-byte salt that is then hex-encoded
        System.out.println("salt is: " + salt);

//        TextEncryptor textEncryptor = Encryptors.text("password", "salt");

        BytesKeyGenerator generator = KeyGenerators.secureRandom();
        byte[] key = generator.generateKey();
        System.out.println("key is: " + key);

    }
}
