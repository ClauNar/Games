package main.java.com.tuttogame.dice;

public enum DieSide {
    One(1,100,1000,
            "┌─────────┐",
            "│         │",
            "│    ●    │",
            "│         │",
            "└─────────┘"
    ),
    Two(2,0,200,
            "┌─────────┐",
            "│    ●    │",
            "│         │",
            "│    ●    │",
            "└─────────┘"
    ),
    Three(3,0,300,
            "┌─────────┐",
            "│ ●       │",
            "│    ●    │",
            "│       ● │",
            "└─────────┘"
    ),
    Four(4,0,400,
            "┌─────────┐",
            "│ ●     ● │",
            "│         │",
            "│ ●     ● │",
            "└─────────┘"
    ),
    Five(5,50,500,
            "┌─────────┐",
            "│ ●     ● │",
            "│    ●    │",
            "│ ●     ● │",
            "└─────────┘"
    ),
    Six(6,0,600,
            "┌─────────┐",
            "│ ●     ● │",
            "│ ●     ● │",
            "│ ●     ● │",
            "└─────────┘"
    );
    public final int value;
    private final int singlePoints;
    private final int tripletPoints;
    public final String line1;
    public final String line2;
    public final String line3;
    public final String line4;
    public final String line5;

    DieSide(int value, int singlePoints, int tripletPoints, String s0, String s1, String s2, String s3, String s4) {
        this.value = value;
        this.singlePoints = singlePoints;
        this.tripletPoints = tripletPoints;
        this.line1 = s0;
        this.line2 = s1;
        this.line3 = s2;
        this.line4 = s3;
        this.line5 = s4;
    }
    public int getValue(){
        return value;
    }

    public int getTripletPoints() {
        return tripletPoints;
    }

    public int getSinglePoints() {
        return singlePoints;
    }
}