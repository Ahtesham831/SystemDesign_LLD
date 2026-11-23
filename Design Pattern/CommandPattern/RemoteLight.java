import java.util.*;


//command
interface Command {
    void execute();

    void undo();
}

class Light {

    public void on() {
        System.out.println("Light in ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is On");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class LightCommand implements Command {

    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class FanCommand implements Command {

    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}


class Remote {

    private Command command;

    private Stack<Command> history = new Stack<>();

    public void setCommand(Command command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
        history.push(command);
    }

    public void pressUndo(){
        if(!history.isEmpty()){
            Command lastCommand = history.pop();
            lastCommand.undo();
        }
        else{
            System.out.println("Nothing to UNDO");
        }
    }

}

public class RemoteLight{
    public static void main(String[] args){

        Fan fan = new Fan();
        Light light = new Light();

        FanCommand fanCommand = new FanCommand(fan);
        LightCommand lightCommand = new LightCommand(light);

        Remote remote = new Remote();
        remote.setCommand(fanCommand);
        remote.pressButton();
        remote.pressUndo();

        remote.setCommand(lightCommand);
        remote.pressButton();
        remote.pressUndo();
    }
}
