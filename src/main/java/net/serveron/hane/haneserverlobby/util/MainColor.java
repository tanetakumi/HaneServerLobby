package net.serveron.hane.haneserverlobby.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MainColor {
    public static TextColor textColor(String text){
        if(text.equals("Black")){
            return TextColor.color(0,0,0);
        }
        else if(text.equals("DarkBlue")){
            return TextColor.color(0,0,170);
        }
        else if(text.equals("DarkGreen")){
            return TextColor.color(0,170,0);
        }
        else if(text.equals("DarkAqua")){
            return TextColor.color(0,170,170);
        }
        else if(text.equals("DarkRed")){
            return TextColor.color(170,0,0);
        }
        else if(text.equals("DarkPurple")){
            return TextColor.color(170,0,170);
        }
        else if(text.equals("Gold")){
            return TextColor.color(255,170,0);
        }
        else if(text.equals("Gray")){
            return TextColor.color(170,170,170);
        }
        else if(text.equals("DarkGray")){
            return TextColor.color(85,85,85);
        }
        else if(text.equals("Blue")){
            return TextColor.color(85,85,255);
        }
        else if(text.equals("Green")){
            return TextColor.color(85,255,85);
        }
        else if(text.equals("Aqua")){
            return TextColor.color(85,255,255);
        }
        else if(text.equals("Red")){
            return TextColor.color(255,85,85);
        }
        else if(text.equals("LightPurple")){
            return TextColor.color(255,85,255);
        }
        else if(text.equals("Yellow")){
            return TextColor.color(255,255,85);
        }
        else {
            return TextColor.color(255,255,255);
        }
    }
}
