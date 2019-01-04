import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class gamecube extends PApplet {

 //import the Serial library

int end = 10;    // the number 10 is ASCII for linefeed (end of serial.println), later we will look for this to break up individual messages
String serial;   // declare a new string called 'serial' . A string is a sequence of characters (data type know as "char")
Serial port;  // The serial port, this is a new instance of the Serial class (an Object)

PImage[] buttons = { null, null, null, null, null, null, null };
PImage[] pressed_buttons = { null, null, null, null, null, null, null };
PImage[] stick_bases = { null, null };
PImage[] sticks = { null, null };
PImage gcc;

int WIDTH = 500;
int HEIGHT = 500;

public void settings() {
  size(WIDTH, HEIGHT);
}

public void setup() {
  port = new Serial(this, Serial.list()[1], 115200); // initializing the object by assigning a port and baud rate (must match that of Arduino)
  port.clear();  // function from serial library that throws out the first reading, in case we started reading in the middle of a string from Arduino

  buttons[0] = loadImage("a.png");
  pressed_buttons[0] = loadImage("a_press.png");
  
  buttons[1] = loadImage("b.png");
  pressed_buttons[1] = loadImage("b_press.png");
  
  buttons[2] = loadImage("x.png");
  pressed_buttons[2] = loadImage("x_press.png");
  
  buttons[3] = loadImage("y.png");
  pressed_buttons[3] = loadImage("y_press.png");
  
  buttons[4] = loadImage("z.png");
  pressed_buttons[4] = loadImage("z_press.png");
  
  buttons[5] = loadImage("l.png");
  pressed_buttons[5] = loadImage("l_press.png");
  
  buttons[6] = loadImage("r.png");
  pressed_buttons[6] = loadImage("r_press.png");
  
  stick_bases[0] = loadImage("a_stick_base.png");
  sticks[0] = loadImage("a_stick.png");
  
  stick_bases[1] = loadImage("c_stick_base.png");
  sticks[1] = loadImage("c_stick.png");
  
  background(0,0,0);
}

String raw;

public void draw() {
  raw = port.readString();
  
 
  if (raw != null) {  //if the string is not empty, print the following
    String[] blah = split(raw, '\r');

    if (blah.length < 2) {
      return;
    }
    serial = blah[blah.length - 2];
    if (serial.length() == 0) {
      return;
    }
    serial = serial.substring(1);
  
    String[] input = split(serial, ',');  //a new array (called 'a') that stores values into separate cells (separated by commas specified in your Arduino program)
    if (input.length != 11) {
      return;
    }
    
    draw_stick(input, 7, 0, 100, 170, 90, 2.844f); // a stick
    draw_stick(input, 9, 1, 240, 300, 80, 3.2f); // c stick
    
    draw_button(input, 0, 300, 130); // a
    draw_button(input, 1, 245, 190); // b
    draw_button(input, 2, 385, 120); // x
    draw_button(input, 3, 290, 70); // y
    
    draw_button(input, 5, 20, 0); // l
    draw_button(input, 6, 330, 0); // r
    
    draw_button(input, 4, 315, 30); // z

  }
}

public void draw_stick(String[] input, int input_index, int index, int x, int y, int distance, float scale) {
  imageMode(CENTER);
  image(stick_bases[index], x, y);
  int ax = Integer.parseInt(input[input_index]);

  int ay = Integer.parseInt(input[input_index + 1]);

  image(sticks[index], x + (ax / scale) - (distance/2), y - (ay / scale) + (distance/2));
  imageMode(CORNERS);
}

public void draw_button(String[] input, int index, int x, int y) {
  if (input[index].equals("0")) {
    image(buttons[index], x, y);
  } else {
    image(pressed_buttons[index], x, y);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "gamecube" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
