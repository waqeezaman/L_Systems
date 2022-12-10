class RulesetButton{
  Button button;
  Ruleset ruleset;
  
  public RulesetButton(Button b, Ruleset r ){
    button=b;
    ruleset=r;
  }
}

RulesetButton[] CreateRulesetButtons(int windowwidth,int windowheight,int x, int y){
  color red=  color(255,0,0);
  color green =color(0,255,0);
  color amber =color(255,255,0);
  ArrayList<RulesetButton> buttons= new ArrayList<RulesetButton>();
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,amber,color(200),"Flower Dragon"),FlowerDragon));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,amber,color(200),"Forest C Curve 1"),ForestCCurve1));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,amber,color(200),"Forest C Curve 2"),ForestCCurve2));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,amber,color(200),"Thorn Bush Diamond"),ThornBushDiamond));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,green,color(200),"DigitalVine"),DigitalVine));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Barnsley"),Barnsley));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Penrose"),Penrose));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Gosper"),Gosper));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Peano"),Peano));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Moore"),Moore));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,amber,color(200),"Ice"),Ice));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,green,color(200),"RoundStar"),RoundStar));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Pentadendrite"),Pentadendrite));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,red,color(200),"Island"),Island));
  buttons.add(new RulesetButton(new Button(x,0,windowwidth,30,green,color(200),"Heighway Dragon"),HeighwayDragon));
  
  int buttonheight= windowheight/buttons.size();
  for (int b=0;b< buttons.size();b++){
      buttons.get(b).button.h=buttonheight;
      buttons.get(b).button.y=y+b*buttonheight;   
  }
 return buttons.toArray(new RulesetButton[0]);
}  

void CreateUI(){
  GenTextBox=new TEXTBOX(GraphWidth+10,90,35,35);
  RulesetButtons=CreateRulesetButtons(UIWidth,GraphHeight-110,GraphWidth,130);
}

void DrawUI(){
  fill(35);
  stroke(100);
  strokeWeight(3);
  rect(GraphWidth,0,UIWidth,GraphHeight);
  textSize(19);
  fill(255);
  text("Press 'W' and 'S' to scale", GraphWidth, 20);
  text("Press 'Q' and 'A' to move left and right", GraphWidth, 40);
  text("Press 'E' and 'D' to move up or down", GraphWidth, 60);
  text("Number of Generations:(must be below 10)", GraphWidth, 80);
  for(RulesetButton b : RulesetButtons){
    b.button.  display();
  }
  GenTextBox.DRAW();
}
