package hypelabs.com.hypepubsub;

import android.content.Context;

import com.hypelabs.hype.Instance;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.ListIterator;


public class ClientsList
{
    // Used composition instead of inheritance to hide the methods that shouldn't be called in
    // a ClientsList.
    final private LinkedList<Client> clients = new LinkedList<>();


    private ClientsAdapter clientsAdapter = null;

    public synchronized int add(Instance instance) throws NoSuchAlgorithmException
    {
        if(isClientAlreadyAdded(instance))
            return -1;

        clients.add(new Client(instance));
        return 0;
    }

    public synchronized int remove(Instance instance)
    {
        Client client = find(instance);
        if(client == null)
            return -1;

        clients.remove(client);
        return 0;
    }


    public synchronized Client find(Instance instance)
    {
        ListIterator<Client> it = listIterator();
        while(it.hasNext())
        {
            Client currentClient = it.next();
            if(HpsGenericUtils.areInstancesEqual(currentClient.instance, instance)) {
                return currentClient;
            }
        }
        return null;
    }

    public synchronized boolean isClientAlreadyAdded(Instance instance)
    {
        if(find(instance) == null)
            return false;

        return true;
    }

    public synchronized ClientsAdapter getClientsAdapter(Context context)
    {
        if(clientsAdapter == null){
            clientsAdapter = new ClientsAdapter(context, clients);
        }
        return clientsAdapter;
    }

    //////////////////////////////////////////////////////////////////////////////
    //  LinkedList methods to enable
    //////////////////////////////////////////////////////////////////////////////

    public synchronized ListIterator<Client> listIterator()
    {
        return clients.listIterator();
    }

    public synchronized int size()
    {
        return clients.size();
    }

    public synchronized Client get(int index)
    {
        return clients.get(index);
    }
}
