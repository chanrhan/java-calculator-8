package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static int calculate(String input) throws IllegalArgumentException{
        if(input.equals("")){
            return 0;
        }

        StringBuilder regexStr = new StringBuilder("[,:");
        if(input.length() >= 5 && input.matches("^/{2}.\\\\n.*")){
            regexStr.append(input.charAt(2));
            input = input.substring(5);
        }

        regexStr.append("]");
        String[] items = input.split(regexStr.toString());
        if(items.length == 0){
           throw new IllegalArgumentException("피연산자가 존재하지 않습니다!");
        }

        try{
            int sum=0;
            for(String item : items){
                sum += Integer.parseInt(item);
            }
            return sum;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("피연산자가 숫자 형식이 아닙니다!");
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println("결과 : "+result);

        // 스캐너 닫기
        Console.close();
    }
}
