package securitycam;

public class NotificationSystem {
	
	public String to;
	public String subject;
	public String body;
	
	public NotificationSystem(String to, String subject, String body){
		this.to= to;
		this.subject = subject;
		this.body = body;
		
	}
	
	public NotificationSystem(){

		
	}
	public String getSubject(){
		return subject;
}
	public void setSubject(String s){
		subject = s;
}
	public String getBody(){
		return body;
}
	public void setBody(String b){
		body = b;
}
	public String getTo(){
		return to;
}
	public void setTo(String t){
		to = t;;
}
}

