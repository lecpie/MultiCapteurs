package fr.polytech.pfe.multicapteurs.model.samples;

import java.util.Arrays;

/**
 * Created by Louis on 21/02/2016.
 */
public class Example {


    PinnedActuator led = new PinnedActuator();
    led.setName("LED");
    led.setPin(12);

    // Declaring states
    State on = new State();
    on.setName("on");

    State off = new State();
    off.setName("off");

    // Creating actions
    Action switchTheLightOn = new Action();
    switchTheLightOn.setActuator(led);
    switchTheLightOn.setSignalExpression(new DigitalExpression(true));
    //switchTheLightOn.setValue(SIGNAL.HIGH);

    Action switchTheLightOff = new Action();
    switchTheLightOff.setActuator(led);
    switchTheLightOff.setSignalExpression(new DigitalExpression(false));
    //switchTheLightOff.setValue(SIGNAL.LOW);

    Sleep sleep = new Sleep();
    sleep.setDelay(1000);

    // Binding actions to states
    on.setActions(Arrays.asList(switchTheLightOn, sleep));
    off.setActions(Arrays.asList(switchTheLightOff));

    // Creating transitions
    Condition sensorlow = new Condition();
    sensorlow.setLeft(button);
    sensorlow.setOperator(Operator.EQ);
    sensorlow.setRight(new DigitalExpression(true));

    Transition on2off = new Transition();
    on2off.setNext(off);
    on2off.setCondition(sensorlow);
    //on2off.setSensor(button);
    //on2off.setValue(SIGNAL.HIGH);

    Condition sensorhigh = new Condition();
    sensorhigh.setLeft(button);
    sensorhigh.setOperator(Operator.EQ);
    sensorhigh.setRight(new DigitalExpression(false));

    Transition off2on = new Transition();
    off2on.setNext(on);
    off2on.setCondition(sensorhigh);

    //off2on.setSensor(button);
    //off2on.setValue(SIGNAL.HIGH);

    // Binding transitions to states
    on.setTransition(on2off);
    off.setTransition(off2on);

    // Building the App
    App theSwitch = new App();
    theSwitch.setName("Switch!");
    theSwitch.setBricks(Arrays.asList(button, led));
    theSwitch.setStates(Arrays.asList(on, off));
    theSwitch.setInitial(off);

    // Generating Code
    Visitor codeGenerator = new ToWiring();
    theSwitch.accept(codeGenerator);

    // Printing the generated code on the console
    System.out.println(codeGenerator.getResult());
}
}
