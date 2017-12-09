package hypelabs.com.hypepubsub;

import com.hypelabs.hype.Instance;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class HpsGenericUtils
{
    public static boolean areClientsEqual(Client client1, Client client2)
    {
        return Arrays.equals(client1.key, client2.key);
    }

    public static boolean areInstancesEqual(Instance instance1, Instance instance2)
    {
        return Arrays.equals(instance1.getIdentifier(), instance2.getIdentifier());
    }

    public static String buildInstanceAnnouncementStr(Instance instance) throws UnsupportedEncodingException
    {
        if(instance.getAnnouncement() == null) {
            return "---";
        }

        return new String(instance.getAnnouncement(), HpsConstants.ENCODING_STANDARD);
    }

    public static byte[] byteArrayHash(byte[] byteArray) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance(HpsConstants.HASH_ALGORITHM);
        return md.digest(byteArray);
    }

    public static byte[] stringHash(String str) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance(HpsConstants.HASH_ALGORITHM);
        return md.digest(str.getBytes());
    }

    public static String buildClientLogIdStr(Client client) throws UnsupportedEncodingException
    {
        return buildInstanceLogIdStr(client.instance);
    }

    public static String buildInstanceLogIdStr(Instance instance) throws UnsupportedEncodingException
    {
        return HpsGenericUtils.buildInstanceAnnouncementStr(instance) + " (0x" + BinaryUtils.byteArrayToHexString(instance.getIdentifier()) + ")";
    }

    public static String buildSubscriptionLogStr(Subscription subscription) throws UnsupportedEncodingException
    {
        return subscription.serviceName + " (0x" + BinaryUtils.byteArrayToHexString(subscription.serviceKey) + ")";
    }

    public static String getTimeStamp()
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("k'h'mm", Locale.getDefault());
        return sdf.format(now);
    }
}
