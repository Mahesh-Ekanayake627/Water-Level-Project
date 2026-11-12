import java.util.*;
class Alarm{
	private String id;
	Alarm(String id){
		this.id=id;
	}
	public void OperateAlarm(int waterLevel){
		System.out.println(id + "-->" +" Alarm : "+(waterLevel > 50 ? "Alarm ON" : "Alarm OFF"));
	}
}
class Display{
	private String id;
	Display(String id){
		this.id=id;
	}
	public void WaterlevelDisplay(int waterLevel){
		System.out.println(id + "-->" +" WaterLevel : "+waterLevel);
	}
}
class SmsSender{
	private String id;
	SmsSender(String id){
		this.id=id;
	}
	public void PrintSms(int waterLevel){
		System.out.println(id + "-->" +" WaterLevel : "+waterLevel);
	}
}
class Spliter{
	private String id;
	Spliter(String id){
		this.id=id;
	}
	public void SpliterON(int waterLevel){
		System.out.println(id + "-->" +" Spliter : "+(waterLevel > 70 ? "Spliter ON" : "Spliter OFF"));
	}
}

class Controllroom{
	private Alarm alarm;
	private Display display;
	private SmsSender smsSender;
	private Spliter spliter;
	
	private int waterLevel;
	
	public void setAlarm(Alarm alarm){
		this.alarm=alarm;
	}
	public void setDisplay(Display display){
		this.display=display;
	}
	public void setsmsSender(SmsSender smsSender){
		this.smsSender=smsSender;
	}
	public void Setspliter(Spliter spliter){
		this.spliter=spliter;
	}
	public void updatewaterLevel(int waterLevel){
		if(this.waterLevel != waterLevel){
			this.waterLevel=waterLevel;
			notifyDevices();
		}
	}
	private void notifyDevices(){
		alarm.OperateAlarm(waterLevel);
		display.WaterlevelDisplay(waterLevel);
		smsSender.PrintSms(waterLevel);
		spliter.SpliterON(waterLevel);
	}
	
}
class WaterLevelProject{
	public static void main(String args[]){
		Controllroom controllroom=new Controllroom();
		controllroom.setAlarm(new Alarm("A0001"));
		controllroom.setDisplay(new Display("D0001"));
		controllroom.setsmsSender(new SmsSender("SM001"));
		controllroom.Setspliter(new Spliter("SP001"));
		
		//controllroom.updatewaterLevel(60);
		
		Random r=new Random();
		while(true){
			int waterLevel=r.nextInt(101);
			controllroom.updatewaterLevel(waterLevel);
			System.out.println();
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
