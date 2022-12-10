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
                                                   
                                                   
                                                   
                                                   
Ruleset Peano=new Ruleset("++FA",22.5,new Rule[]{new Rule('A',"A-BA+CA+CA+CA-BA-BA-BA+CA",DrawAction.DoNothing),
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
                                                   
                                                   
                                

                                                   
                                                   
                                                   
