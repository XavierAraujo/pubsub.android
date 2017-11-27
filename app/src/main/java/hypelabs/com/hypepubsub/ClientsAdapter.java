package hypelabs.com.hypepubsub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class ClientsAdapter extends ArrayAdapter<Client>
{
    public ClientsAdapter(Context context, LinkedList<Client> clients)
    {
        super(context, 0, clients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        Client client = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_client, parent, false);
        }

        // Lookup view for data population
        TextView clientName = convertView.findViewById(R.id.item_client_name);
        TextView clientId = convertView.findViewById(R.id.item_client_id);
        TextView clientKey = convertView.findViewById(R.id.item_client_key);

        // Populate the data into the template view using the data object
        try
        {
            clientName.setText(GenericUtils.getInstanceAnnouncementStr(client.instance));
            clientId.setText(BinaryUtils.byteArrayToHexString(client.instance.getIdentifier()));
            clientKey.setText(BinaryUtils.byteArrayToHexString(client.key));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        // Return the completed view to render on screen
        return convertView;
    }

}