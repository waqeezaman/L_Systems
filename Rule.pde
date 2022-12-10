public class Rule{

  
 private char Letter;
 private String Output;
private DrawAction Action;

  public Rule(char letter, String output,DrawAction action ){
    Letter=letter;
    Output=output;
    Action=action;
  }

}

  public enum DrawAction{
    DoNothing,
    DrawLineForwards,
    DrawLineBackwards,
    TurnLeft,
    TurnRight,
    Move,
    Branch,
    Return
  }
