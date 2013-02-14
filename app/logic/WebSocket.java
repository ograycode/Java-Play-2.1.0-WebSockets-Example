package logic;

import play.*;
import play.libs.*;
import play.libs.F.*;
import play.mvc.*;
import java.util.*;

public class WebSocket extends play.mvc.WebSocket<String> {

	private static List<WebSocket.Out<String>> _out;

	//Called when the websocket connects
	@Override 
	public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
		
		this.addWebSocketOut(out);

		// For each event received on the socket,
		in.onMessage(new Callback<String>() {
		 public void invoke(String event) {
		   // Log events to the console
		   System.out.println(event);  
		   for (WebSocket.Out<String> o : _out)
		   	o.write(event);
		 } 
		});

		// When the socket is closed.
		in.onClose(new Callback0() {
		 public void invoke() {
		   System.out.println("Disconnected");
		 }
		});

		// Send a single 'Hello!' message
		out.write("Hello!");
	}

	private void addWebSocketOut(WebSocket.Out<String> out) {
		if (_out == null)
			_out = new ArrayList<>();

		Boolean found = false;
		for (WebSocket.Out<String> o : _out) {
			if (o == out)
				found = true;
		}

		if (!found)
			_out.add(out);
	}
}