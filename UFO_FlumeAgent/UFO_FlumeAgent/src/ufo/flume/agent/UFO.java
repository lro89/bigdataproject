package ufo.flume.agent;


import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Flume:
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.PollableSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;

//Zur HTML-Verarbeitung:
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class UFO extends AbstractSource implements Configurable, PollableSource{

	private Document doc;
	private Elements elements;
	private String tmpLine;
	private int tmpCounter;
	private boolean finished;
	
	private int currentIndex = 0;
	
	private EventBuilder eventBuilder;
	
	@Override
	public void configure(Context context) {
		try{
			String website = context.getString("website");
			doc = Jsoup.connect(website)
					.maxBodySize(0)
					.timeout(600000)
					.get();
			elements = doc.select("td");
		}
		catch(Exception ex){}
		
		eventBuilder = new EventBuilder();
		
		tmpLine = "";
		tmpCounter = 0;
		finished = false;
		
	}

	@Override
	public void stop(){
		super.stop();
	}
	
	private Event getNextEvent(){
		try {
			Event result = null;
			String tmp = null;
			String eventLine = "";
			
			for(int i=0;i<7;i++){
				
				tmp = elements.get(currentIndex + i).text();
				tmp = tmp.replace(';', ':');
				tmp = tmp.replace('\"', '\'');
				
				
				if(i == 0){
					// Es wird aktuell das Datumsfeld durchlaufen. Dieses muss umformatiert werden:
					try{
						DateFormat oldFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
						Date date = oldFormat.parse(tmp);
						
						DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
						tmp = newFormat.format(date);
						
					}
					catch(Exception ex){
						tmp = ""; 
					}
				}
				
				eventLine += "\"" + tmp + "\"";
				
				if(i < 6)
					eventLine += ";";
			}
			
			currentIndex += 7;
			
			result = eventBuilder.withBody(eventLine, Charset.defaultCharset());
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Status process() throws EventDeliveryException {
		
		try
		{
			Event e = getNextEvent();
			
			if(e != null){
				getChannelProcessor().processEvent(e);
			
				return Status.READY;
			}
			else
				return Status.BACKOFF;
			
			
		}
		catch(Exception ex){
			return Status.BACKOFF;
		}
	}
	
//	@Override
//	public Status process() throws EventDeliveryException {
//		
//		if(finished)
//			return Status.BACKOFF;
//		
//		Status result = null;
//		
//		try
//		{
//			for(int i = 0; i<elements.size(); i++){
//				tmpLine += "\"" + elements.get(i).text() + "\"";
//				
//				tmpCounter++;
//				
//				if(tmpCounter > 6)
//				{
//					tmpCounter = 0;
//					
//					Event e = eventBuilder.withBody(tmpLine, Charset.defaultCharset());
//					getChannelProcessor().processEvent(e);
//					
//					tmpLine = "";
//				}
//				else
//				{
//					tmpLine += ";";
//				}
//				
//			}
//			finished = true;
//			result = Status.READY;
//			return result;
//			
//			
//		}
//		catch(Exception ex){
//			result = Status.BACKOFF;
//			return result;
//		}
//	}
//	

}
