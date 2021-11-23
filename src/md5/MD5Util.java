package md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Util {

    public static void main(String[] args) {

        String s = UUID.randomUUID().toString();
        String rand = s.substring(0,10);
        System.out.println(s);

//        URestSigner uRestSigner = new URestSigner("5413d191a49b4fcd9d257426a68df951");
//        String sign = uRestSigner.sign("海信电视", System.currentTimeMillis(), "73992821");
//        System.out.println(sign);
    }

    static class URestSigner{
        private static final String SIGN_PART_SEPARATOR = " ";
        private static final String SIGN_PROTOCOL_VERSION = "v2";
        private final String mAppKey;
        private boolean mTimeSynced = false;

        public URestSigner(String appKey) {
            this.mAppKey = appKey;
        }

        public String sign(String deviceId, long timestamp, String randStr) {
            return this.md5(timestamp + this.mAppKey + randStr + deviceId) + " " + timestamp + " " + randStr + " " + "v2";
        }

        private String md5(String srcStr) {
            try {
                String result;
                for(result = (new BigInteger(1, MessageDigest.getInstance("MD5").digest(srcStr.getBytes()))).toString(16); result.length() < 32; result = "0" + result) {
                }

                return result;
            } catch (NoSuchAlgorithmException var3) {
                throw new IllegalStateException(var3);
            }
        }
    }

}
