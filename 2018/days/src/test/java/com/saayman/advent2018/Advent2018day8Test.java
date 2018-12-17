package com.saayman.advent2018;

import com.saayman.advent2018.day8.NodeTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day8Test {

    private String input;

    @Before
    public void setUp() throws Exception {
        //2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
        //A----------------------------------
        //    B----------- C-----------
        //                     D-----
        input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
    }

    @Test
    public void day8Tests() {
        NodeTree empty = new NodeTree("0 0");
        assertThat(empty.root.numChildren()).isEqualTo(0);
        assertThat(empty.root.numMetaEntries()).isEqualTo(0);
        assertThat(empty.root.sumMetaEntries()).isEqualTo(0);
        assertThat(empty.sumMetaEntries()).isEqualTo(0);

        NodeTree one = new NodeTree("0 1 0");
        assertThat(one.root.numChildren()).isEqualTo(0);
        assertThat(one.root.numMetaEntries()).isEqualTo(1);
        assertThat(one.root.sumMetaEntries()).isEqualTo(0);

        NodeTree five = new NodeTree("0 5 1 2 3 4 5");
        assertThat(five.root.numChildren()).isEqualTo(0);
        assertThat(five.root.numMetaEntries()).isEqualTo(5);
        assertThat(five.root.sumMetaEntries()).isEqualTo(15);

        NodeTree fiveEmpty = new NodeTree("1 0 0 5 1 2 3 4 5");
        assertThat(fiveEmpty.root.numChildren()).isEqualTo(1);
        assertThat(fiveEmpty.root.numMetaEntries()).isEqualTo(0);
        assertThat(fiveEmpty.root.sumMetaEntries()).isEqualTo(0);

        NodeTree fiveOne = new NodeTree("2 5 0 1 11 0 0 1 2 3 4 5");
        assertThat(fiveOne.root.numChildren()).isEqualTo(2);
        assertThat(fiveOne.root.numMetaEntries()).isEqualTo(5);
        assertThat(fiveOne.root.sumMetaEntries()).isEqualTo(15);

        NodeTree simple4 = new NodeTree("3 1 "
                                                      +"0 1 " +"2 "
                                                      +"3 1 "
                                                        +"0 1 " +"4 "
                                                        +"1 1 "
                                                           +"0 1 " +"6 "
                                                        +"5 "
                                                        +"0 1 " +"7 "
                                                      +"3 "
                                                      +"0 1 " +"8 "
                                                   +"1");
        assertThat(simple4.sumMetaEntries()).isEqualTo(36);

        NodeTree example = new NodeTree(input);
        assertThat(example.sumMetaEntries()).isEqualTo(138);
        assertThat(example.root.value()).isEqualTo(66);
    }

}