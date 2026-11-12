import java.util.*;
interface WaterLevelobserver{
	void update(int waterLevel);
}
class Alarm implements WaterLevelobserver{
	private String id;
	Alarm(String id){
		this.id=id;
	}
	public void update(int waterLevel){
		System.out.println(id + "-->" +" Alarm : "+(waterLevel > 50 ? "Alarm ON" : "Alarm OFF"));
	}
}
class Display implements WaterLevelobserver{
	private String id;
	Display(String id){
		this.id=id;
	}
	public void update(int waterLevel){
		System.out.println(id + "-->" +" WaterLevel : "+waterLevel);
	}
}
class SmsSender implements WaterLevelobserver{
	private String id;
	SmsSender(String id){
		this.id=id;
	}
	public void update(int waterLevel){
		System.out.println(id + "-->" +" WaterLevel : "+waterLevel);
	}
}
class Spliter implements WaterLevelobserver{
	private String id;
	Spliter(String id){
		this.id=id;
	}
	public void update(int waterLevel){
		System.out.println(id + "-->" +" Spliter : "+(waterLevel > 70 ? "Spliter ON" : "Spliter OFF"));
	}
}

class Controllroom{
	private WaterLevelobserver[] waterLevelobserverArray=new WaterLevelobserver[0];
	private int waterLevel;
	
	public void addWaterLevelobserver(WaterLevelobserver addArray){
		extendArray();
		waterLevelobserverArray[waterLevelobserverArray.length-1]=addArray;
	}
	public void extendArray(){
		WaterLevelobserver[] teampwaterLevelobserverArray=new WaterLevelobserver[waterLevelobserverArray.length+1];
		for(int i=0 ; i<waterLevelobserverArray.length ; i++){
			teampwaterLevelobserverArray[i]=waterLevelobserverArray[i];
		}
		waterLevelobserverArray=teampwaterLevelobserverArray;
	}
	
	public void updatewaterLevel(int waterLevel){
		if(this.waterLevel != waterLevel){
			this.waterLevel=waterLevel;
			notifyDevices();
		}
	}
	private void notifyDevices(){
		for(WaterLevelobserver ob : waterLevelobserverArray){
			ob.update(waterLevel);
		}
	}
	
}
class WaterLevelProject{
	public static void main(String args[]){
		Controllroom controllroom=new Controllroom();
		controllroom.addWaterLevelobserver(new Alarm("A0001"));
		controllroom.addWaterLevelobserver(new Display("D0001"));
		controllroom.addWaterLevelobserver(new SmsSender("SM001"));
		controllroom.addWaterLevelobserver(new Spliter("SP001"));
		
		controllroom.updatewaterLevel(60);
		
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
