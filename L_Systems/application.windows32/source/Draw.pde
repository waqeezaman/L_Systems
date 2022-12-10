void DrawPoint(float x, float y, float radius, color colour) {
  stroke(colour);
  fill(colour);
  circle(x, y, radius);
}

void DrawLine(float x1, float y1, float x2, float y2, color colour, float thickness) {

  if ( ( (x1<width && x1>0) && (y1<height && y1>0)) ||  (  (x2<width && x2>0) && (y2<height && y2>0)  ) ) {
    stroke(colour);
    strokeWeight(thickness);
    line(x1, y1, x2, y2);
  }
}


void DrawPointMatrix(Matrix points, color pointcolour, color linecolour, float linethickness, boolean drawline, boolean drawpoints, float PointRadius, boolean cyclical) {
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




Matrix MapToCanvas(Matrix points,int rownum,int colnum,int graphwidth,int graphheight){
  Matrix transformedmatrix= new Matrix();
  
   
  
  transformedmatrix=points.ScaleRow(1,-1f);

  transformedmatrix=transformedmatrix.AddValueToRow(0,float(colnum/2));
  transformedmatrix=transformedmatrix.AddValueToRow(1,float(rownum/2));
 
  
  float horizontalscale = (float(graphwidth)/float(colnum));
  float verticalscale = (float(graphheight)/float(rownum));
  
  transformedmatrix=transformedmatrix.ScaleRow(0,horizontalscale);
  transformedmatrix=transformedmatrix.ScaleRow(1,verticalscale);
  
  return transformedmatrix;
}

Matrix MapToCartesian(Matrix points,int colnum,int rownum,int graphwidth,int graphheight){
  Matrix transformedmatrix= new Matrix();
  
  float horizontalscale = 1/(float(graphwidth)/float(colnum));
  float verticalscale = 1/(float(graphheight)/float(rownum));

  transformedmatrix=points.ScaleRow(0,horizontalscale);
  transformedmatrix=transformedmatrix.ScaleRow(1,verticalscale);
  
     // flip row
  transformedmatrix=transformedmatrix.ScaleRow(1,-1f);
  
  transformedmatrix=transformedmatrix.AddValueToRow(0,-float(colnum/2));
  transformedmatrix=transformedmatrix.AddValueToRow(1,float(rownum/2));
 

  
  return transformedmatrix;
}
