package abhi64p.fuego;

import java.net.HttpURLConnection;
import java.net.URL;

public class SendData
{
    private String Address;
    private SendListener Listener;

    public SendData(String Address)
    {
        this.Address = Address;
        Listener = null;
    }

    public interface SendListener
    {
        void onComplete(final int Response);
        void onError(final String err);
    }

    public void setListener(SendListener listener)
    {
        Listener = listener;
    }

    public void Send()
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection) new URL("http://"+Address).openConnection();
            conn.setDoInput(false);
            conn.setDoOutput(false);
            Listener.onComplete(conn.getResponseCode());
            conn.disconnect();
        }
        catch (Exception ex)
        {
            Listener.onError(ex.getMessage());
        }
    }
}