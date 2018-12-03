package com.saayman.advent2018.day3;

public class ClaimParser {
    public static Claim parse(String claim) {
        //#<id> @ <fromLeft>,<fromTop>: <width>x<height>
        Claim claimObj = new Claim(
            claim.substring(claim.indexOf("#")+1,claim.indexOf(" @")),
                Integer.parseInt(claim.substring(claim.indexOf(" @ ")+3,claim.indexOf(","))),
                Integer.parseInt(claim.substring(claim.indexOf(",")+1,claim.indexOf(": "))),
                Integer.parseInt(claim.substring(claim.indexOf(": ")+2,claim.indexOf("x"))),
                Integer.parseInt(claim.substring(claim.indexOf("x")+1))
                );
        return claimObj;
    }
}
