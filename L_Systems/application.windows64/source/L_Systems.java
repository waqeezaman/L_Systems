import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class L_Systems extends PApplet {



Turtle T;

float StartX=width/2;
float StartY=height/2;

float Distance =0.005f;

int GraphWidth=1000;
int GraphHeight=1000;
int UIWidth=400;

TEXTBOX GenTextBox;



TurtleMatrix MatrixTurtle;

RulesetButton[] RulesetButtons;



public void settings(){
  size(GraphWidth+UIWidth,GraphHeight);
}




public void setup() {
  T=new Turtle(width/2, height/2, 90);
  CreateUI();
  InitialiseMatrixTurtle(RulesetButtons[0].ruleset,5);

  
}
public void draw(){
  DrawUI();
}

public void InitialiseMatrixTurtle(Ruleset ruleset,int generations){
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

public void DrawMatrixTurtle(){
    background(0);
    
  for(Matrix m: MatrixTurtle.Drawing){
      m=MapToCanvas(m,10,10,GraphWidth,GraphHeight);
      DrawPointMatrix(m,color(100,200,150),color(100,200,150),1,true,false,0,false);
      m=MapToCartesian(m,10,10,GraphWidth,GraphHeight);
  }
}

public void keyPressed() {
  if(key=='w'){
    MatrixTurtle.Scale(2f);
    DrawMatrixTurtle();
  }
  else if (key=='s'){
    MatrixTurtle.Scale(0.5f);
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

public void mousePressed(){
  for(RulesetButton b: RulesetButtons){
    if(b.button.  mouseOver()){
      InitialiseMatrixTurtle(b.ruleset,PApplet.parseInt(GenTextBox.Text));
      break;
    }
  }
  GenTextBox.PRESSED(mouseX,mouseY);
  
}




public void DrawString_Turtle(String instructions,float startx,float starty,Ruleset ruleset) {
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
// code for this class from http://web20bp.com/kb/processing-button-class/
/*****************************************************************************************
 * 
 *   BUTTON CLASS
 * 
 ****************************************************************************************/
class Button
{
  int x, y, w, h;
  int c;
  int cOver;
  String txt;
  int txtSize = 15;

  /****************************************************************************
   
   CONSTRUCTOR
   
   ****************************************************************************/
  Button (int _x, int _y, int _w, int _h, int _c, int _cover, String _txt)
  {
    x = _x;
    y = _y;
    w = _w;
    h = _h;
    c = _c;
    cOver = _cover;
    txt = _txt;
  }

  /****************************************************************************
   
   DISPLAY THE BUTTON
   
   ****************************************************************************/
  public void display()
  {
    pushStyle();
    textAlign(CENTER);
    if (mouseOver())
      fill(cOver);
    else
      fill(c);
    stroke(100);
    strokeWeight(2);
    rect(x, y, w, h, 10);
    fill(0);
    textSize(txtSize);
    text(txt, x+w/2, y+h/2+txtSize/2);
    popStyle();
  }


  /****************************************************************************
   
   CHANGE THE TEXT ON THE BUTTON
   
   ****************************************************************************/
  public void setText (String _txt)
  {
    txt = _txt;
    display();
  }

  /****************************************************************************
   
   IS THE MOUSE OVER THE BUTTON?
   
   ****************************************************************************/
  public boolean mouseOver()
  {
    return (mouseX >= x && mouseX <= (x + w) && mouseY >= y && mouseY <= (y + h));
  }
  
  
} // Button
public void DrawPoint(float x, float y, float radius, int colour) {
  stroke(colour);
  fill(colour);
  circle(x, y, radius);
}

public void DrawLine(float x1, float y1, float x2, float y2, int colour, float thickness) {

  if ( ( (x1<width && x1>0) && (y1<height && y1>0)) ||  (  (x2<width && x2>0) && (y2<height && y2>0)  ) ) {
    stroke(colour);
    strokeWeight(thickness);
    line(x1, y1, x2, y2);
  }
}


public void DrawPointMatrix(Matrix points, int pointcolour, int linecolour, float linethickness, boolean drawline, boolean drawpoints, float PointRadius, boolean cyclical) {
  if (points.ColNum>2 && drawline && cyclical) {
    DrawLine(points.matrix.get(0).get(0), points.matrix.get(0).get(1), points.matrix.get(points.matrix.size()-1).get(0), points.matrix.get(points.matrix.size()-1).get(1), 
      linecolour, linethickness);
  }

  for (int j =0; j<points.ColNum; j++) {

    if (j!=points.ColNum-1 && drawline) {
      DrawLine(points.matrix.get(j).get(0), points.matrix.get(j).get(1), points.matrix.get(j+1).get(0), points.matrix.get(j+1).get(1), linecolour, linethickness);
    }
    if (drawpoints) {
      DrawPoint(points.matrix.get(j).get(0), points.matrix.get(j).get(1), PointRadius, pointcolour);
    }
  }
}



//Point MapPointToCanvas(Point point,int colnum,int rownum,int graphwidth,int graphheight){
//  float x = point.x+float(colnum/2);
//  x*=float(graphheight/colnum);
//  float y = point.y*-1;
//  y+=float(rownum/2);
//  y*=float(graphheight/rownum);
//  return new Point(x,y);
//}
//Point MapPointToCartesian(Point point,int colnum,int rownum,int graphwidth,int graphheight){
//  float x = point.x/float(graphwidth/colnum);
//  x-=float(colnum/2);
  
//  float y = point.y/ float(graphheight/rownum);
//  y*=-1;
//  y+=float(rownum/2);
//  return new Point(x,y);
//}




public Matrix MapToCanvas(Matrix points,int rownum,int colnum,int graphwidth,int graphheight){
  Matrix transformedmatrix= new Matrix();
  
   
  
  transformedmatrix=points.ScaleRow(1,-1f);

  transformedmatrix=transformedmatrix.AddValueToRow(0,PApplet.parseFloat(colnum/2));
  transformedmatrix=transformedmatrix.AddValueToRow(1,PApplet.parseFloat(rownum/2));
 
  
  float horizontalscale = (PApplet.parseFloat(graphwidth)/PApplet.parseFloat(colnum));
  float verticalscale = (PApplet.parseFloat(graphheight)/PApplet.parseFloat(rownum));
  
  transformedmatrix=transformedmatrix.ScaleRow(0,horizontalscale);
  transformedmatrix=transformedmatrix.ScaleRow(1,verticalscale);
  
  return transformedmatrix;
}

public Matrix MapToCartesian(Matrix points,int colnum,int rownum,int graphwidth,int graphheight){
  Matrix transformedmatrix= new Matrix();
  
  float horizontalscale = 1/(PApplet.parseFloat(graphwidth)/PApplet.parseFloat(colnum));
  float verticalscale = 1/(PApplet.parseFloat(graphheight)/PApplet.parseFloat(rownum));

  transformedmatrix=points.ScaleRow(0,horizontalscale);
  transformedmatrix=transformedmatrix.ScaleRow(1,verticalscale);
  
     // flip row
  transformedmatrix=transformedmatrix.ScaleRow(1,-1f);
  
  transformedmatrix=transformedmatrix.AddValueToRow(0,-PApplet.parseFloat(colnum/2));
  transformedmatrix=transformedmatrix.AddValueToRow(1,PApplet.parseFloat(rownum/2));
 

  
  return transformedmatrix;
}
class Matrix{
  
  int RowNum;
  int ColNum;
  
  ArrayList<ArrayList<Float> > matrix = new ArrayList<ArrayList<Float>>();
  
  
  public Matrix(){
    // creates matrix object with nothing in it 
  }
  public Matrix(Matrix copy){
   CreateZeroMatrix(copy.RowNum,copy.ColNum);
   for (int j=0; j<ColNum;j++){
     for(int i =0 ; i<RowNum;i++){
       Set(i,j,copy.Get(i,j));
     }
   }
  }
  
  public Matrix(int rownum, int colnum){
    //creates an empty matrix
    RowNum=rownum;
    ColNum=colnum;
    CreateZeroMatrix(rownum,colnum);
    
  }
  
  public Matrix(int rownum, int colnum ,float[] values){
    // creates matrix and fills with values

    
    CreateZeroMatrix(rownum,colnum);


    for (int i=0; i<values.length;i++){
       Set(i/colnum,Math.floorMod(i,colnum),values[i]);
    }

    
    
    

  }
  
  private  void CreateZeroMatrix(int rownum,int colnum){
    
    
    matrix=new ArrayList<ArrayList<Float>>();
    RowNum=rownum;
    for (int c =0; c<colnum;c++){
     
      AddColumn();
    }
    ColNum=colnum;
  }
  
  
  
  
  
  
  public Matrix Scale(float scalar){
    // scales each element in the matrix and returns a new matrix
    Matrix scaledmatrix=new Matrix(this);
    for(int j =0; j<ColNum;j++){
      for (int i =0; i<RowNum;i++){
        scaledmatrix.Set(i,j,scalar*Get(i,j));
      }
    }
    return scaledmatrix;
  }
   public void Scale(Float scalar){
    // scales each element in the matrix 
    for(int j =0; j<ColNum;j++){
      for (int i =0; i<RowNum;i++){
        Set(i,j,scalar*Get(i,j));
      }
    }
  }
  
  public Matrix ScaleRow(int row, Float scalar){
    // scales a whole row in the matrix by a single value
    Matrix scaledmatrix=new Matrix(this);
    for(int col =0 ; col<ColNum;col++){
      scaledmatrix.Set(row,col,scalar* Get(row,col));
    }
    return scaledmatrix;
  }
  
  public Matrix AddValue( float value){
    //adds a single value to each element and returns a new matrix
     Matrix addedmatrix = new Matrix(this);
     for(int j =0; j<ColNum;j++){
      for (int i =0; i<RowNum;i++){
        addedmatrix.Set(i,j,value+Get(i,j));
      }
    }
    return addedmatrix;
    
  }
  
  public Matrix AddValueToRow(int row,float value){
    Matrix addedmatrix=new Matrix(this);
    for(int col =0; col<matrix.size();col++){
      addedmatrix.Set(row,col,value+Get(row,col));
    }
    return addedmatrix;
  }
  
  public void AddValueToRow(float value,int row){
    for(int col =0; col<matrix.size();col++){
      Set(row,col,value+Get(row,col));
    }
  }
  
  public Matrix Multiply(Matrix transform){
    if(transform.ColNum!=RowNum){
      print("Error: trying to multiply matrices where sizes don't correspond");
      return null;
    }
    else{
      Matrix transformedmatrix= new Matrix(transform.RowNum,ColNum);
      
      
      for (int row=0;row<transform.RowNum;row++){
          for (int col=0; col<ColNum; col++){
            for (int common=0; common<transform.ColNum;common++){
              float sum=transformedmatrix.matrix.get(col).get(row);
              float a = transform.matrix.get(common).get(row);
              float b = matrix.get(col).get(common);
              transformedmatrix.Set(row,col,sum+a*b);
           //   transformedmatrix.Set();
               // transformedmatrix.Set(col,row,transformedmatrix.matrix.get(col).get(row) + B.matrix.get(col ).get(row) * matrix.get(point).get(col));
             // transformedmatrix.matrix.get(col).set(row,transformedmatrix.matrix.get(col).get(row) + B.matrix.get(col ).get(row) * matrix.get(point).get(col));
            }
          }
      }
          return transformedmatrix;
    }
  }
  
  public void Set(int row, int col, Float val){
           matrix.get(col).set(row,val);
  }
  
  public Float Get(int row, int col){
            return  matrix.get(col).get(row);
  }
  

  
  public void AddColumn(){
        matrix.add(new ArrayList<Float>());
        for(int i =0 ; i<RowNum; i++){
          matrix.get(matrix.size()-1).add(0.0f);
        }
        ColNum+=1;
  }
  
  public void AddColumn(ArrayList<Float> values){
    if(ColNum==0){
       matrix.add(values);
       RowNum= values.size();
    }
    else if(RowNum!=values.size()){
      return;
    }
    else{
      matrix.add(values);
    }
    ColNum+=1;
    
  }
  public void AddColumn(Float[] values){
    
    if(ColNum==0){
        RowNum=values.length;
      }
    
    if(values.length==RowNum){
      
        
      
      AddColumn();
      for (int i=0; i<values.length;i++){
       Set(i,matrix.size()-1,values[i]);
      }
      
    }
    
  }
  
  public float[] GetRow(int rownum){
    if(rownum>ColNum || rownum<0){
      println("error tried to get row that doesnt exist");
      return null;
    }
    float[] row= new float[ColNum];
    for(int c=0;c<ColNum;c++){
      row[c]=Get(rownum,c);
    }
    
    return row;
  }
  

  
  public void OutputColumn(int col){
    println("Column: " + col);
    for(int i =0; i<matrix.get(col).size();i++){
      println(matrix.get(col).get(i));
    } 
  }
  
  public void OutputMatrix(){
    
    for(int i =0;i<RowNum;i++){
      String row="[";
      for(int j =0;j<ColNum;j++){
        row=row + matrix.get(j).get(i) +" , ";        
      }
      println(row);
    }
    
  }  

}
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
class Ruleset {

  Rule[] Rules;
  String Axiom;
  float Angle;


  public Ruleset(String axiom, float angle, Rule[] rules) {
    Axiom=axiom;
    Angle=angle;
    Rules=rules;
  }
}
Ruleset FlowerDragon=new Ruleset("X",60,new Rule[]{new Rule('X', "X-AYA-X",DrawAction.DoNothing), 
                                                   new Rule('Y', "Y+AXA+Y",DrawAction.DoNothing),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight),
                                                   new Rule('A',"A",DrawAction.DrawLineForwards)
                        }); 
                        

Ruleset ForestCCurve1=new Ruleset("X",45,new Rule[]{new Rule('X',"F-X++X-F",DrawAction.DoNothing),
                                                    new Rule('F',"F",DrawAction.DrawLineForwards),
                                                    new Rule('+',"+",DrawAction.TurnLeft),
                                                    new Rule('-',"-",DrawAction.TurnRight)});
                                                    
Ruleset ForestCCurve2=new Ruleset("X",135,new Rule[]{new Rule('X',"F-X++X-F",DrawAction.DoNothing),
                                                    new Rule('F',"F",DrawAction.DrawLineForwards),
                                                    new Rule('+',"+",DrawAction.TurnLeft),
                                                    new Rule('-',"-",DrawAction.TurnRight)});
                                
Ruleset ThornBushDiamond=new Ruleset("X",60,new Rule[]{new Rule('X',"XF-Y",DrawAction.DoNothing),
                                                       new Rule('Y',"YF+X",DrawAction.DoNothing),
                                                       new Rule('+',"+",DrawAction.TurnLeft),
                                                       new Rule('-',"-",DrawAction.TurnRight),
                                                       new Rule('G',"G-",DrawAction.DrawLineBackwards),
                                                       new Rule('F',"F+",DrawAction.DrawLineForwards)});
                                                       
                                                       
                                                       
                                                       
Ruleset DigitalVine=new Ruleset("FX",90,new Rule[]{new Rule('F',"FXY",DrawAction.DrawLineForwards),
                                                   new Rule('G',"GG",DrawAction.DrawLineBackwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight),
                                                   new Rule('X',"X-F",DrawAction.DoNothing),
                                                   new Rule('Y',"Y+G",DrawAction.DoNothing)});
                                                   
Ruleset Barnsley = new Ruleset("X",25,new Rule[]{ new Rule('X',"F+[[X]-X]-F[-FX]+X",DrawAction.DoNothing),
                                                  new Rule('F',"FF",DrawAction.DrawLineForwards),
                                                  new Rule('+',"+",DrawAction.TurnLeft),
                                                 new Rule('-',"-",DrawAction.TurnRight),
                                                new Rule('[',"[",DrawAction.Branch),
                                               new Rule(']',"]",DrawAction.Return)});
                                                   
Ruleset Random=new Ruleset("F",37,new Rule[]{new Rule('F',"G+H",DrawAction.DrawLineForwards),
                                                   new Rule('G',"FH",DrawAction.DrawLineBackwards),
                                                   new Rule('H',"G+F",DrawAction.DrawLineForwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   

Ruleset Penrose=new Ruleset("[7]++[7]++[7]++[7]++[7]",36,new Rule[]{new Rule('6',"81++91----71[-81----61]++",DrawAction.DrawLineForwards),
                                                                     new Rule('7',"+81--91[---61--71]+",DrawAction.DrawLineForwards),
                                                                     new Rule('8',"-61++71[+++81++91]-",DrawAction.DrawLineForwards),
                                                                     new Rule('9',"--81++++61[+91++++71]--71",DrawAction.DrawLineForwards),
                                                                     new Rule('1',"1",DrawAction.DoNothing),

                                                                     new Rule('+',"+",DrawAction.TurnLeft),
                                                                     new Rule('-',"-",DrawAction.TurnRight),
                                                                     new Rule('[',"[",DrawAction.Branch),
                                                                     new Rule(']',"]",DrawAction.Return)});
                                                                     
Ruleset Gosper=new Ruleset("A",60,new Rule[]{new Rule('A',"A-B--B+A++AA+B-",DrawAction.DrawLineForwards),
                                                   new Rule('B',"+A-BB--B-A++A+B",DrawAction.DrawLineForwards),
                                                   
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
                                                   
                                                   
Ruleset Peano=new Ruleset("++FA",22.5f,new Rule[]{new Rule('A',"A-BA+CA+CA+CA-BA-BA-BA+CA",DrawAction.DoNothing),
                                                   new Rule('B',"F-F-F-F",DrawAction.DoNothing),
                                                   new Rule('C',"F+F+F+F",DrawAction.DoNothing),
                                                   new Rule('F',"F",DrawAction.DrawLineForwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
                                                   
Ruleset Moore=new Ruleset("LFL+F+LFL",90,new Rule[]{new Rule('L',"-RF+LFL+FR-",DrawAction.DoNothing),
                                                   new Rule('R',"+LF-RFR-FL+",DrawAction.DoNothing),
                                                   new Rule('C',"F+F+F+F",DrawAction.DoNothing),
                                                   new Rule('F',"F",DrawAction.DrawLineForwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
Ruleset Ice=new Ruleset("F+F+F+F",90,new Rule[]{   new Rule('F',"FF+F++F+F",DrawAction.DrawLineForwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
Ruleset RoundStar=new Ruleset("F",77,new Rule[]{new Rule('F',"F++F",DrawAction.DrawLineForwards),
                                                
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
                                                   
Ruleset Pentadendrite=new Ruleset("F",72,new Rule[]{new Rule('F',"F-F-F++F+F-F",DrawAction.DrawLineForwards),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
 
Ruleset Island=new Ruleset("F-F-F-F",90,new Rule[]{new Rule('F',"F-b+FF-F-FF-Fb-FF+b-FF+F+FF+Fb+FFF",DrawAction.DrawLineForwards),
                                                   new Rule('b',"bbbbbb",DrawAction.Move),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
Ruleset HeighwayDragon=new Ruleset("FX",90,new Rule[]{new Rule('F',"F",DrawAction.DrawLineForwards),
                                                   new Rule('X',"X+YF+",DrawAction.DoNothing),
                                                   new Rule('Y',"-FX-Y",DrawAction.DoNothing),
                                                   new Rule('+',"+",DrawAction.TurnLeft),
                                                   new Rule('-',"-",DrawAction.TurnRight)});
                                                   
                                                   
                                

                                                   
                                                   
                                                   
// code for this class from https://github.com/mitkonikov/Processing/blob/master/Text_Box/TEXTBOX.pde

public class TEXTBOX {
   public int X = 0, Y = 0, H = 35, W = 200;
   public int TEXTSIZE = 24;
   
   // COLORS
   public int Background = color(140, 140, 140);
   public int Foreground = color(0, 0, 0);
   public int BackgroundSelected = color(160, 160, 160);
   public int Border = color(30, 30, 30);
   
   public boolean BorderEnable = false;
   public int BorderWeight = 1;
   
   public String Text = "";
   public int TextLength = 0;

   private boolean selected = false;
   
   TEXTBOX() {
      // CREATE OBJECT DEFAULT TEXTBOX
   }
   
   TEXTBOX(int x, int y, int w, int h) {
      X = x; Y = y; W = w; H = h;
   }
   
   public void DRAW() {
      // DRAWING THE BACKGROUND
      if (selected) {
         fill(BackgroundSelected);
      } else {
         fill(Background);
      }
      
      if (BorderEnable) {
         strokeWeight(BorderWeight);
         stroke(Border);
      } else {
         noStroke();
      }
      
      rect(X, Y, W, H);
      
      // DRAWING THE TEXT ITSELF
      fill(Foreground);
      textSize(TEXTSIZE);
      text(Text, X + (textWidth("a") / 2), Y + TEXTSIZE);
   }
   
   // IF THE KEYCODE IS ENTER RETURN 1
   // ELSE RETURN 0
   public boolean KEYPRESSED(char KEY, int KEYCODE) {
      if (selected) {
         if (KEYCODE == (int)BACKSPACE) {
            BACKSPACE();
         } else if (KEYCODE == 32) {
            // SPACE
            addText(' ');
         } else if (KEYCODE == (int)ENTER) {
            return true;
         } else {
            // CHECK IF THE KEY IS A LETTER OR A NUMBER OR COMMA
            boolean isKeyCapitalLetter = (KEY >= 'A' && KEY <= 'Z');
            boolean isKeySmallLetter = (KEY >= 'a' && KEY <= 'z');
            boolean isKeyNumber = (KEY >= '0' && KEY <= '9');
            boolean isComma = (KEY ==',');
            boolean isFullStop=(KEY=='.');
            boolean isMinus=(KEY=='-');
      
          //  if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber || isComma || isFullStop) {
            if(isKeyNumber){
               addText(KEY);
            }
         }
      }
  
      
      return false;
   }
   
   private void addText(char text) {
      // IF THE TEXT WIDHT IS IN BOUNDARIES OF THE TEXTBOX
      if (textWidth(Text + text) < W) {
         Text += text;
         TextLength++;
      }
   }
   
   private void BACKSPACE() {
      if (TextLength - 1 >= 0) {
         Text = Text.substring(0, TextLength - 1);
         TextLength--;
      }
   }
   
   // FUNCTION FOR TESTING IS THE POINT
   // OVER THE TEXTBOX
   private boolean overBox(int x, int y) {
      if (x >= X && x <= X + W) {
         if (y >= Y && y <= Y + H) {
            return true;
         }
      }
      
      return false;
   }
   
   public void PRESSED(int x, int y) {
      if (overBox(x, y)) {
         selected = true;
      } else {
         selected = false;
      }
   }
}
class Transform{
  float X;
  float Y; 
  float Rotation;
  
  public Transform(){
  
  }
  public Transform(float x, float y , float rotation){
    X=x;
    Y=y;
    Rotation=rotation;
  }
}
class Turtle {


  private Transform Position;

  private ArrayList<Transform> PastPositions=new ArrayList<Transform>();







  public Turtle(float x, float y, float rotation) {
    Position=new Transform(x, y, rotation);
  }

  public void Reset() {
    Position.X=width/2;
    Position.Y=height/2;
    Position.Rotation=90;
    PastPositions.clear();
  }


  public void Rotate(float rotation) {
    Position.Rotation+=rotation;
  }

  public void Move(float distance) {
    Position.Y-=distance*Math.sin(Math.toRadians(Position.Rotation));
    Position.X+=distance*Math.cos(Math.toRadians(Position.Rotation));
  }

  public void DrawLine(float distance) {
    float oldX=Position.X;
    float oldY=Position.Y;
    Move(distance);
    if ( (((oldX<width)&&(oldX>0)) && ((oldY<height)&&(oldY>0)))  || (((Position.X<width)&&(Position.X>0)) && ((Position.Y<height)&&(Position.Y>0)))             ) {
      line(oldX, oldY, Position.X, Position.Y);
    }
  }

  public void Branch() {
    PastPositions.add(new Transform(Position.X, Position.Y, Position.Rotation));
  }

  public void Return() {
    if (PastPositions.size()>0) {
      int lastindex=PastPositions.size()-1;
      Position.X=PastPositions.get(lastindex).X;
      Position.Y=PastPositions.get(lastindex).Y;
      Position.Rotation=PastPositions.get(lastindex).Rotation;
      PastPositions.remove(lastindex);
    }
  }
}
class TurtleMatrix {
  ArrayList<Matrix> Drawing =new ArrayList<Matrix>();
  Matrix CurrentMatrix;
  Transform Position;
  private ArrayList<Transform> PastPositions=new ArrayList<Transform>();


    public TurtleMatrix(String instructions, Ruleset ruleset, Transform startposition,float distance) {
      
    Position=new Transform ();
    Position=startposition;
    AddMatrix();
    AddPoint(Position.X,Position.Y);
        

    for (int i =0; i<instructions.length(); i++) {
      for (Rule r : ruleset.Rules) {
        if (r.Letter==instructions.charAt(i)) {


          switch(r.Action) {
          case Move:
            MovePosition(distance);
            AddMatrix();
            break;
          case DrawLineForwards:
            MovePosition(distance);
            AddPoint(Position.X,Position.Y);
            break;
          case DrawLineBackwards:
            MovePosition(-distance);
            AddPoint(Position.X,Position.Y);
            break;
          case DoNothing:
            break;

          case TurnLeft:  
            Position.Rotation+=ruleset.Angle;
            break;

          case TurnRight:  
              Position.Rotation-=ruleset.Angle;
            break;

          case Branch:
              Branch();
            break;

          case Return:
              //AddMatrix();
              
              //Matrix penultimatematrix=Drawing.get(Drawing.size()-2);
              //AddPoint(penultimatematrix.Get(0,penultimatematrix.ColNum-1),penultimatematrix.Get(1,penultimatematrix.ColNum-1));
              Return();
            break;  


          default:
            break;
          }
        }
      }
    }
  }
  
  public void MovePosition(float d){
    Position.Y-=d*Math.sin(Math.toRadians(Position.Rotation));
    Position.X+=d*Math.cos(Math.toRadians(Position.Rotation));
  }
   public void Branch() {
    PastPositions.add(new Transform(Position.X, Position.Y, Position.Rotation));
    AddMatrix();
    AddPoint(Position.X,Position.Y);
  }

  public void Return() {
    if (PastPositions.size()>0) {
      int lastindex=PastPositions.size()-1;
      Position.X=PastPositions.get(lastindex).X;
      Position.Y=PastPositions.get(lastindex).Y;
      Position.Rotation=PastPositions.get(lastindex).Rotation;
      AddMatrix();
      AddPoint(Position.X,Position.Y);
      PastPositions.remove(lastindex);
    }
  }
  
  public void AddPoint(float x,float y){
    CurrentMatrix.AddColumn(new Float[]{x,y});
  }
  
  public void AddMatrix(){
    if(Drawing.size()==0 || CurrentMatrix.ColNum>0){
    Drawing.add(new Matrix());
    CurrentMatrix=Drawing.get(Drawing.size()-1);}
  }
  
  public void Scale(Float scale){
     for(Matrix m: Drawing){
        m.Scale(scale);
    }
  }
  
  
  public void Translate(float value,int row){
    for(Matrix m: Drawing){
        m.AddValueToRow(value,row);
    }
  }
}
class RulesetButton{
  Button button;
  Ruleset ruleset;
  
  public RulesetButton(Button b, Ruleset r ){
    button=b;
    ruleset=r;
  }
}

public RulesetButton[] CreateRulesetButtons(int windowwidth,int windowheight,int x, int y){
  int red=  color(255,0,0);
  int green =color(0,255,0);
  int amber =color(255,255,0);
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

public void CreateUI(){
  GenTextBox=new TEXTBOX(GraphWidth+10,90,35,35);
  RulesetButtons=CreateRulesetButtons(UIWidth,GraphHeight-110,GraphWidth,130);
}

public void DrawUI(){
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "L_Systems" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
