package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 입력
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        // 출력
        StringAdditionCalculator calculator = new StringAdditionCalculator();
        System.out.println("결과 : " + calculator.calculate(input));

        // 스캐너 닫기
        Console.close();
    }
}
