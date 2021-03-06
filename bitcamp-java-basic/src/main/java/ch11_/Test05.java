// String 클래스 사용법 - immutable(값을 바꿀 수 없는) 객체이다.
package ch11_;

public class Test05 {
  public static void main(String[] args) {
    
    String s1 = new String("Hello");
    
    // String 인스턴스가 한 번 생성되면 절대로 값을 바꿀 수 없다.
    // => 이런 것을 "immutable 객체"라 부른다.
    // s2, s3와 s1의 주소값은 다르다.
    String s2 = s1.replace('l', 'x');
    
    String s3 = s1.replace("ll", "dd");

    System.out.println(s1);
    System.out.println(s2);
    System.out.println(s3);
    System.out.println(s1 == s2);
    System.out.println(s2 == s3);
    System.out.println(s1 == s3);
  }
}
















