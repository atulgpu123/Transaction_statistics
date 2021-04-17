package com.n26.util;

import org.junit.Assert;
import org.junit.Test;

public class TimeUtilTest {

    @Test
    public void shouldBeAbleToConvertMillisecondsToSeconds1() {
        long actualResult = new TimeUtil().convertMillisecondsToSeconds(1000);
        Assert.assertEquals(1, actualResult);
    }

    @Test
    
    public void shouldBeAbleToConvertMillisecondsToSeconds2() {
        long actualResult = new TimeUtil().convertMillisecondsToSeconds(500);
        Assert.assertEquals(0, actualResult);
    }

    @Test
    public void shouldBeAbleToConvertMillisecondsToSeconds3() {
        long actualResult = new TimeUtil().convertMillisecondsToSeconds(3500);
        Assert.assertEquals(3, actualResult);
    }
}
