package calculator;

public class StringAdditionCalculator {
    private final int[] regexMetaCharset = new int[]{
            36,39,40,41,42,43,46,63,91,92,93,94,123,124,125
    };

    // 문자열을 분할하는 함수
    // 커스텀 구분자 패턴이 있다면, 커스텀 구분자도 분할 기준에 포함시킨다.
    private String[] separateString(String input){
        StringBuilder regexStr = new StringBuilder("[,:"); // 기본 구분자

        if(input.length() >= 5 && input.matches("^/{2}.\\\\n.*")){
            if(input.length() == 5){
                return null;
            }
            char customSeparator = input.charAt(2);
            if((customSeparator <= 32 || customSeparator == 127)
                    || (customSeparator >= 48 && customSeparator <= 57)){ // 커스텀 문자가 0~9 사이의 숫자일 경우
                throw new IllegalArgumentException("커스텀 구분자에는 숫자 또는 공백,제어 문자가 올 수 없습니다! : " + customSeparator);
            }
            for(int meta : regexMetaCharset){
                if(customSeparator == meta){
                    regexStr.append("\\");
                    break;
                }
            }
            regexStr.append(customSeparator);
            input =  input.substring(5);
        }

        regexStr.append("]");

        return input.split(regexStr.toString());
    }

    public int calculate(String input) throws IllegalArgumentException{
        if(input == null || input.isEmpty()){
            return 0;
        }

        String[] items = separateString(input);
        if(items == null){
            return 0;
        }else if(items.length == 0){
            throw new IllegalArgumentException("피연산자가 존재하지 않습니다!");
        }

        try{
            int sum=0, num;
            for(String item : items){
                num = Integer.parseInt(item);
                if(num <= 0){
                    throw new IllegalArgumentException("피연산자는 양수여야 합니다! : " + num);
                }
                sum += num;
            }
            if(sum < 0){
                throw new IllegalArgumentException("숫자 합이 정수의 최댓값을 초과했습니다! : " + sum);
            }
            return sum;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("피연산자가 숫자 형식이 아닙니다!");
        }
    }

}
