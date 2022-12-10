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
