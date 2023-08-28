package gui.Algorithms_Controllers;
public class ControllerAlgorithms {
    public ControllerAlgorithms(){}
    public static void main(String[] args) {
   //     ControllerAlgorithms control = new ControllerAlgorithms();
    }
    public boolean batteryChecker(int batteryLevel){
        if(batteryLevel<25){return true;}
        else{return false;}
    }

    public boolean InsulinChecker(int insulinLevel){
        if(insulinLevel<50){return true;}
        else{return false;}
    }
    public String AvgMaker(int last1_bl, int last2_bl,int last3_bl){
        int avg = (last1_bl+last2_bl+last3_bl)/3;
        return AvgChecker(avg);
    }
    public String AvgChecker(int avg){
        if(avg<120&&avg>70){return "false";}
        else if(avg>120){return "high";}
        else{return "low";}
    }
}
