package lab4.servlet;

import com.alibaba.fastjson.JSON;
import lab4.utils.CurrencyListener;
import lab4.utils.CurrencyObserver;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class EventSocket extends WebSocketAdapter implements CurrencyListener {

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        CurrencyObserver.getInstance().addListener(this);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        CurrencyObserver.getInstance().removeListener(this);
    }

    @Override
    public void onChangeCurrencies(Map<String, Double> map) {
        try {
            this.getSession().getRemote().sendString(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
