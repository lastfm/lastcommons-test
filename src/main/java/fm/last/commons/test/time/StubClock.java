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

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import fm.last.commons.lang.time.Clock;

public class StubClock extends Clock {

  private long currentTimeMillis;
  private Calendar calendar;
  private long nanoTime;
  private Date date;
  private DateTime dateTime;

  @Override
  public long currentTimeMillis() {
    return currentTimeMillis;
  }

  @Override
  public Calendar getCalendarInstance() {
    return calendar;
  }

  @Override
  public long nanoTime() {
    return nanoTime;
  }

  @Override
  public Date newDate() {
    return date;
  }

  @Override
  public DateTime newDateTime() {
    return dateTime;
  }

  public void setCurrentTimeMillis(long currentTimeMillis) {
    this.currentTimeMillis = currentTimeMillis;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public void setNanoTime(long nanoTime) {
    this.nanoTime = nanoTime;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setDateTime(DateTime dateTime) {
    this.dateTime = dateTime;
  }

}
