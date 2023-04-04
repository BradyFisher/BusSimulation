/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class Stop {
    private Q<Passenger> eastbound = new Q(); //Queue for the Passengers going East.
    private Q<Passenger> westbound = new Q(); //Queue for the Passengers going West.
    private String name; //Name of the stop.

    public Stop(int stopNum){
        switch(stopNum){
            case 1: this.name = "University Ave and 27th Street SE";
                    break;
            case 2: this.name = "Raymond Ave Station";
                    break;
            case 3:this.name = "University Ave and Fairview Ave";
                    break;
            case 4:this.name = "University Ave and Snelling Ave";
                    break;
            case 5:this.name = "University Ave and Lexington Parkway";
                    break;
            case 6:this.name = "University Ave and Dale Street";
                    break;
            case 7:this.name = "University Ave and Marion Street";
                    break;
            case 8:this.name = "Cedar Street and 5th Street";
                    break;
            case 9:this.name = "Minnesota Street and 4th Street";
                    break;
            case 10:this.name = "Union Depot";
                    break;
            default:System.out.println("Should never reach here: Stop Number is not valid");
                    break;
        }
    }
    public Q<Passenger> getEastbound(){
        return eastbound;
    }

    public Q<Passenger> getWestbound(){
        return westbound;
    }

}
