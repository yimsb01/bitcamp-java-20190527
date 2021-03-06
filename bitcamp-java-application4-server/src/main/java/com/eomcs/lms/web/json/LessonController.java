package com.eomcs.lms.web.json;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

// @RestController
// => request handler의 리턴 값이 응답 데이터임을 선언한다.
// => 리턴 값은 내부에 설정된 HttpMessageConverter에 의해 JSON 문자열로 변환하여 보낸다.
// 
@RestController("json.LessonController")
@RequestMapping("/json/lesson")
public class LessonController {

  @Resource
  private LessonService lessonService;

  @RequestMapping("form")
  public void form() {
  }

  @RequestMapping("add")
  public JsonResult add(Lesson lesson) 
      throws Exception {
    try {
      lessonService.insert(lesson);
      return new JsonResult().setState(JsonResult.SUCCESS);
      
    } catch (Exception e) {
      return new JsonResult()
          .setState(JsonResult.FAILURE)
          .setMessage(e.getMessage());
    }

  }

  @RequestMapping("delete")
  public JsonResult delete(int no) 
      throws Exception {
    try {
      lessonService.delete(no);
      return new JsonResult().setState(JsonResult.SUCCESS);

    } catch (Exception e) {
      return new JsonResult()
          .setState(JsonResult.FAILURE)
          .setMessage(e.getMessage());
    }
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) 
      throws Exception {

    try {
      Lesson lesson = lessonService.get(no);
      return new JsonResult()
          .setState(JsonResult.SUCCESS)
          .setResult(lesson);

    } catch (Exception e) {
      return new JsonResult()
          .setState(JsonResult.FAILURE)
          .setResult(e.getMessage());
    }

  }

  @RequestMapping("list")
  public JsonResult list(Model model) 
      throws Exception {
    try {
      List<Lesson> lessons = lessonService.list();
      return new JsonResult()
          .setState(JsonResult.SUCCESS)
          .setResult(lessons);
      
    } catch (Exception e) {
      return new JsonResult()
          .setState(JsonResult.FAILURE)
          .setMessage(e.getMessage());
    }
  }

  @RequestMapping("update")
  public JsonResult update(Lesson lesson) 
      throws Exception {
    try {
      lessonService.update(lesson);
      return new JsonResult().setState(JsonResult.SUCCESS);
      
    } catch (Exception e) {
      return new JsonResult()
          .setState(JsonResult.FAILURE)
          .setMessage(e.getMessage());
    }
  }
}