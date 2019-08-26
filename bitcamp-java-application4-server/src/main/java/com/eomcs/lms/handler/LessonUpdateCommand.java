package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Date;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonUpdateCommand implements Command {

  private LessonDao lessonDao;

  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      Lesson lesson = lessonDao.findBy(no);

      if (lesson == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      String str = Input.getStringValue(in, out, "수업명(" + lesson.getTitle() + ")? ");
      if (str.length() > 0) {
        lesson.setTitle(str);
      }

      str = Input.getStringValue(in, out, "수업내용(" + lesson.getContents() +")? ");
      if (str.length() > 0) {
        lesson.setContents(str);
      }

      Date date = Input.getDateValue(in, out, "시작일(" + lesson.getStartDate() + ")? ");
      if (date != null) {
        lesson.setStartDate(date);
        date = null;
      }
      
      date = Input.getDateValue(in, out, "종료일(" + lesson.getEndDate() + ")? ");
      if (date != null) {
        lesson.setEndDate(date);
      }
      
      int hours = Input.getIntValue(in, out, "총수업시간(" + lesson.getTotalHours() + ")? ");
      if (hours != -1) {
        lesson.setTotalHours(hours);
        hours = -1;
      }
      
      hours = Input.getIntValue(in, out, "일수업시간(" + lesson.getDayHours() + ")? ");
      if (hours != -1) {
        lesson.setDayHours(hours);
        hours = -1;
      }

      lessonDao.update(lesson);
      out.println("데이터를 변경하였습니다.");

    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다.");
      e.printStackTrace();
    }

  }

}












