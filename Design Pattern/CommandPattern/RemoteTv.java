

class TV{
    public void turnOnTV(){
        System.out.println("TV is turned ON");
    }

    public void turnOffTV(){
        System.out.println("TV is turned OFF");
    }
}

@FunctionalInterface 
interface Command{
    void execute();
}

class TurnOnTVCommand implements Command{

    private TV tv;

    public TurnOnTVCommand(TV tv){
        this.tv = tv;
    }

    @Override 
    public void execute(){
        tv.turnOnTV();
    }
}

class TurnOffTVCommand implements Command{

    private TV tv;

    public TurnOffTVCommand(TV tv){
        this.tv = tv;
    }

    @Override 
    public void execute(){
        tv.turnOffTV();
    }
}

class RemoteControl{

    private Command command;

    void setCommand(Command command){
        this.command = command;
    }

    void pressButton(){
        command.execute();
    }
}

public class RemoteTv {
    public static void main(String[] args){
        //create receiver -- who are going to receive the command from invoker
        TV tv = new TV();

        //create concrete command -- what command are the receiver going to receive
        Command turnOnCommand = new TurnOnTVCommand(tv);
        Command turnOffCommand = new TurnOffTVCommand(tv);

        //create invoker -- who are going to perform the operation on receiver
        RemoteControl remote = new RemoteControl();
        // before the exectution we need to set what operation are going to perform by the invoker
        remote.setCommand(turnOnCommand);
        remote.pressButton();

        remote.setCommand(turnOffCommand);
        remote.pressButton();

    }
}

