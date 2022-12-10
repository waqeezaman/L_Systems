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
  
  void MovePosition(float d){
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
  
  void AddPoint(float x,float y){
    CurrentMatrix.AddColumn(new Float[]{x,y});
  }
  
  void AddMatrix(){
    if(Drawing.size()==0 || CurrentMatrix.ColNum>0){
    Drawing.add(new Matrix());
    CurrentMatrix=Drawing.get(Drawing.size()-1);}
  }
  
  void Scale(Float scale){
     for(Matrix m: Drawing){
        m.Scale(scale);
    }
  }
  
  
  void Translate(float value,int row){
    for(Matrix m: Drawing){
        m.AddValueToRow(value,row);
    }
  }
}
