import java.util.*;

Turtle T;

float StartX=width/2;
float StartY=height/2;

float Distance =0.005;

int GraphWidth=1000;
int GraphHeight=1000;
int UIWidth=400;

TEXTBOX GenTextBox;



TurtleMatrix MatrixTurtle;

RulesetButton[] RulesetButtons;



void settings(){
  size(GraphWidth+UIWidth,GraphHeight);
}




void setup() {
  T=new Turtle(width/2, height/2, 90);
  CreateUI();
  InitialiseMatrixTurtle(RulesetButtons[0].ruleset,5);

  
}
void draw(){
  DrawUI();
}

void InitialiseMatrixTurtle(Ruleset ruleset,int generations){
  if(generations>88){
    return;
  }
  String SystemString = ruleset.Axiom;
  StringBuffer newString=new StringBuffer();

  for (int i =0; i<generations; i++) {
  
      for ( int l=0; l<SystemString.length(); l++) {
        for (int r =0; r<ruleset.Rules.length; r++) {
          if (ruleset.Rules[r].Letter==SystemString.charAt(l)) {
            newString.append(ruleset.Rules[r].Output);
          }
        }
      }
      SystemString=newString.toString();
      newString.delete(0, newString.length());
    }
    MatrixTurtle=new TurtleMatrix(SystemString,ruleset,new Transform(0,0,0),Distance);
    DrawMatrixTurtle();  

}

void DrawMatrixTurtle(){
    background(0);
    
  for(Matrix m: MatrixTurtle.Drawing){
      m=MapToCanvas(m,10,10,GraphWidth,GraphHeight);
      DrawPointMatrix(m,color(100,200,150),color(100,200,150),1,true,false,0,false);
      m=MapToCartesian(m,10,10,GraphWidth,GraphHeight);
  }
}

void keyPressed() {
  if(key=='w'){
    MatrixTurtle.Scale(2f);
    DrawMatrixTurtle();
  }
  else if (key=='s'){
    MatrixTurtle.Scale(0.5);
    DrawMatrixTurtle();  
  }
    else if (key=='q'){
    MatrixTurtle.Translate(2,0);
    DrawMatrixTurtle();

  }
    else if (key=='a'){
    MatrixTurtle.Translate(-2,0);
        DrawMatrixTurtle();
  }
    else if (key=='e'){
    MatrixTurtle.Translate(2,1);
        DrawMatrixTurtle();
  }
    else if (key=='d'){
       MatrixTurtle.Translate(-2,1);
           DrawMatrixTurtle();
  }
  
  GenTextBox.KEYPRESSED(key,keyCode);
}

void mousePressed(){
  for(RulesetButton b: RulesetButtons){
    if(b.button.  mouseOver()){
      InitialiseMatrixTurtle(b.ruleset,int(GenTextBox.Text));
      break;
    }
  }
  GenTextBox.PRESSED(mouseX,mouseY);
  
}




void DrawString_Turtle(String instructions,float startx,float starty,Ruleset ruleset) {
    background(0);

  T=new Turtle(startx,starty,90);
  for (int i =0; i<instructions.length(); i++) {
    Rule currentrule=new Rule(' ', "", DrawAction.DoNothing);
    for (int r=0; r<ruleset.Rules.length; r++) {
      if (ruleset.Rules[r].Letter==instructions.charAt(i)) {

        currentrule=ruleset.Rules[r];
        break;
      }
    }    
    switch(currentrule.Action) {
    case Move:
      T.Move(Distance);
      break;
    case DrawLineForwards:
      T.DrawLine(Distance);
      break;
    case DrawLineBackwards:
      T.DrawLine(-Distance);
    case DoNothing:
      break;

    case TurnLeft:  
      T.Rotate(ruleset.Angle);
      break;

    case TurnRight:  
      T.Rotate(-ruleset.Angle);

      break;

    case Branch:
      T.Branch();

      break;

    case Return:
      T.Return();

      break;  


    default:
      break;
    }
  }
}
