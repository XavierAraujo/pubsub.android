package hypelabs.com.hypepubsub;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * This class represents a service subscription. Each object of this class contains the name and the
 * key of the subscribed service (which is generated by hashing the service name using the SHA-1
 * hashing algorithm) and the ID of the Hype clients responsible for managing that service.
 */
public class Subscription {

    String serviceName;
    byte serviceKey[];
    byte managerId[];

    public Subscription(String serviceName, byte managerId[]) throws NoSuchAlgorithmException
    {
        this.serviceName = serviceName;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        this.serviceKey = md.digest(serviceName.getBytes());
        this.managerId = managerId;
    }
}
