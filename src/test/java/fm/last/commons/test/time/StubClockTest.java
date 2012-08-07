/*
 * Copyright 2012 Last.fm
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.commons.test.time;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class StubClockTest {

  private final StubClock stubClock = new StubClock();

  @Test
  public void gettersAndSetters() {
    assertEquals(null, stubClock.getCalendarInstance());
    Calendar calendar = Calendar.getInstance();
    stubClock.setCalendar(calendar);
    assertEquals(calendar, stubClock.getCalendarInstance());

    assertEquals(0, stubClock.currentTimeMillis());
    long time = 10000000;
    stubClock.setCurrentTimeMillis(time);
    assertEquals(time, stubClock.currentTimeMillis());

    assertEquals(0, stubClock.nanoTime());
    long nanoTime = 20000000;
    stubClock.setNanoTime(nanoTime);
    assertEquals(nanoTime, stubClock.nanoTime());

    assertEquals(null, stubClock.newDate());
    Date newDate = new Date();
    stubClock.setDate(newDate);
    assertEquals(newDate, stubClock.newDate());

    assertEquals(null, stubClock.newDateTime());
    DateTime newDateTime = new DateTime();
    stubClock.setDateTime(newDateTime);
    assertEquals(newDateTime, stubClock.newDateTime());

  }

}
