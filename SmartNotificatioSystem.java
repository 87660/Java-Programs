public class SmartNotificatioSystem{
    public static void main(String[] args){
        Notifier N;
        N=new EmailNotifier();
        N. send() ;
        N=new SMSNotifier();
        N. send() ;
        N=new PushNotifier();
        N. send() ;
    }
}
abstract class Notifier{
    abstract void send() ;
    
}
class EmailNotifier extends Notifier{
    void send(){
        System. out. println("Sending email notification");
    }
}
class SMSNotifier extends Notifier{
    void send() {
        System. out. println("sending sms notification");
    }
}
class PushNotifier extends Notifier{
    void send(){
        System. out. println("Sending push notification");
    }
}