// Object 클래스 - clone() : shallow copy
package ch15;
 

public class Test13_1 {
  
  static class Engine implements Cloneable {
    int cc;
    int value;
    
    public Engine(int cc, int value) {
      this.cc = cc;
      this.value = value;
    }
    
    @Override
    public String toString() {
      return "Engine [cc=" + cc + ", value=" + value + "]";
    }
    
    @Override
    protected Engine clone() throws CloneNotSupportedException {
      return (Engine) super.clone();
    }
  }
  
  static class Car implements Cloneable {
    String maker;
    String name;
    Engine engine;
    
    public Car(String maker, String name, Engine engine) {
      this.maker = maker;
      this.name = name;
      this.engine = engine;
    }

    @Override
    public String toString() {
      return "Car [maker=" + maker + ", name=" + name + ", engine=" + engine + "]";
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
      // 복제를 위한 코드를 따로 작성할 필요가 없다. JVM이 알아서 해준다. 
      // 그냥 상속 받은 메서드를 오버라이딩하고, 접근 권한을 public 으로 확대한다.
      // 리턴 타입은 해당 클래스 이름으로 변경한다.
      return (Car) super.clone();
    }
  }
  
  public static void main(String[] args) throws Exception {
    Engine engine = new Engine(3000, 16);
    Car car = new Car("비트자동차", "비트비트", engine);
    
    // 자동차 복제
    Car car2 = car;

    System.out.println(car == car2);
    System.out.println(car);
    System.out.println(car2);
    System.out.println(car.engine == car2.engine);
    
    // clone()은 해당 객체의 필드 값만 복제한다.
    // 그 객체가 포함하고 있는 하위 객체는 복제하지 않는다.
    // => "shallow copy"라 부른다.
    //
    // 그 객체가 포함하고 있는 하위 객체까지 복제하는 것을
    // "deep copy"라 부른다.
    // deep copy는 개발자가 직접 clone() 메서드 안에
    // deep copy를 수행하는 코드를 작성해야 한다.
    
  }
}







